/*
 * (C) Copyright Hemajoo Systems Inc.  2022 - All Rights Reserved
 * -----------------------------------------------------------------------------------------------
 * All information contained herein is, and remains the property of
 * Hemajoo Inc. and its suppliers, if any. The intellectual and technical
 * concepts contained herein are proprietary to Hemajoo Inc. and its
 * suppliers and may be covered by U.S. and Foreign Patents, patents
 * in process, and are protected by trade secret or copyright law.
 *
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained from
 * Hemajoo Systems Inc.
 * -----------------------------------------------------------------------------------------------
 */
package com.hemajoo.commerce.cherry.server.document.service;

import com.hemajoo.commerce.cherry.server.base.filter.IEntityFilter;
import com.hemajoo.commerce.cherry.server.commons.entity.query.GenericSpecification;
import com.hemajoo.commerce.cherry.server.commons.entity.query.condition.QueryConditionException;
import com.hemajoo.commerce.cherry.server.data.model.document.ServerDocument;
import com.hemajoo.commerce.cherry.server.document.converter.DocumentConverter;
import com.hemajoo.commerce.cherry.server.document.filter.DocumentFilterMetadata;
import com.hemajoo.commerce.cherry.server.document.query.DocumentQuery;
import com.hemajoo.commerce.cherry.server.document.repository.IDocumentRepository;
import com.hemajoo.commerce.cherry.server.document.store.DocumentStore;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.IEntity;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.IStatusEntity;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.exception.EntityException;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.type.EntityStatusType;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.type.EntityType;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.document.ClientDocument;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.document.IDocument;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.document.exception.DocumentException;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.document.type.DocumentType;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.changetype.ValueChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.javers.core.diff.ListCompareAlgorithm.LEVENSHTEIN_DISTANCE;

/**
 * Persistence service for server data model document entities.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Log4j2
@Service
public class DocumentService implements IDocumentService
{
    /**
     * Tika object.
     */
    private static final Tika TIKA = new Tika();

    /**
     * Document repository.
     */
    @Getter
    @Autowired
    private IDocumentRepository documentRepository;

    /**
     * Document store.
     */
    @Autowired
    private DocumentStore documentStore;

    /**
     * Object changes detector.
     */
    private final Javers javers = JaversBuilder.javers()
            .withListCompareAlgorithm(LEVENSHTEIN_DISTANCE)
            .build();

    /**
     * Document converter.
     */
    private DocumentConverter converter = new DocumentConverter();

//    /**
//     * Entity factory.
//     */
//    @Getter
//    @Autowired
//    private EntityFactory factory;

    @Override
    public IDocumentRepository getRepository()
    {
        return documentRepository;
    }

    @Override
    public Long count()
    {
        return documentRepository.count();
    }

    @Override
    public ServerDocument findById(UUID id)
    {
        ServerDocument document = documentRepository.findById(id).orElse(null);

        if (document != null)
        {
            loadContent(document);
        }

        return document;
    }

    @Transactional
    @Override
    public ServerDocument update(final @NonNull ServerDocument document) throws EntityException
    {
        return save(document);
    }

    @Override
    public ServerDocument updateMetadata(final @NonNull ClientDocument document) throws EntityException
    {
        ServerDocument serverDocument = documentRepository.findById(document.getId()).orElse(null);
        if (serverDocument == null)
        {
            throw new DocumentException(String.format("Document with id: '%s' not found!", document.getId()));
        }

        ClientDocument original = (ClientDocument) converter.fromServerToClient(serverDocument);

        LOGGER.debug(String.format("%s detecting changes on properties...", document.getIdentity()));
        List<ValueChange> changes = filterDocumentChanges(getPropertyChanges(original, document), DocumentFilterMetadata.build());
        if (!changes.isEmpty())
        {
            applyPropertyChanges(serverDocument, changes);
            serverDocument = documentRepository.save(serverDocument);
            LOGGER.debug(String.format("%s updated successfully", document.getIdentity()));
        }
        else
        {
            LOGGER.debug(String.format("%s no change detected on properties!", document.getIdentity()));
        }

        return serverDocument;
    }

    @Override
    public ServerDocument uploadContent(final @NonNull ServerDocument document, final @NonNull MultipartFile file) throws EntityException
    {
        try
        {
            InputStream stream = new ByteArrayInputStream(file.getBytes());

            document.setContent(stream);
            document.setContentLength(file.getBytes().length);
            document.setFilename(file.getOriginalFilename());
            document.setExtension(FilenameUtils.getExtension(file.getOriginalFilename()));
            document.setMimeType(TIKA.detect(stream));

            LOGGER.debug(String.format("Uploading file: '%s', size: '%s' to content store: '%s'", document.getFilename(), file.getSize(), documentStore.getSpringContentStoreType()));
            documentStore.getStore().setContent(document, stream);
            LOGGER.debug(String.format("File: '%s' uploaded successfully. Content id: '%s'", document.getFilename(), document.getContentId()));

            LOGGER.debug(String.format("%s content set: '%s'", document.getIdentity(), document.getContentId()));
            stream.close();

            save(document);
        }
        catch (Exception e)
        {
            throw new DocumentException(e);
        }

        return document;
    }

    @Override
    public ServerDocument updateContent(final @NonNull ServerDocument document, final @NonNull MultipartFile file) throws EntityException
    {
        try
        {
            InputStream stream = new ByteArrayInputStream(file.getBytes());

            // Delete the old document content.
            LOGGER.debug(String.format("%s content removed: '%s'", document.getIdentity(), document.getContentId()));
            documentStore.getStore().unsetContent(document);

            // Set the new document content.
            document.setContentLength(file.getBytes().length);
            document.setFilename(file.getOriginalFilename());
            document.setExtension(FilenameUtils.getExtension(file.getOriginalFilename()));
            document.setMimeType(TIKA.detect(stream));

            LOGGER.debug(String.format("Uploading file: '%s', size: '%s' to content store: '%s'", document.getFilename(), file.getSize(), documentStore.getSpringContentStoreType()));
            documentStore.getStore().setContent(document, stream);
            LOGGER.debug(String.format("File: '%s' uploaded successfully. Content id: '%s'", document.getFilename(), document.getContentId()));
            save(document);
            LOGGER.debug(String.format("%s content set: '%s'", document.getIdentity(), document.getContentId()));

            stream.close();
        }
        catch (Exception e)
        {
            throw new DocumentException(e);
        }

        return document;
    }

    @Override
    public Resource downloadContent(final @NonNull ServerDocument document)
    {
        return documentStore.getStore().getResource(document);
    }

    /**
     * Retrieve the changes on properties between two client documents.
     * @param original Original client document.
     * @param other Other client document.
     * @return List of changes on properties.
     */
    private List<ValueChange> getPropertyChanges(final @NonNull ClientDocument original, final @NonNull ClientDocument other)
    {
        return javers.compare(original, other).getChangesByType(ValueChange.class);
    }

    /**
     * Apply the changes detected on properties to a server document.
     * @param documentServer Server document.
     * @param changes List of changes.
     * @throws EntityException Thrown to indicate an error occurred when trying to connect retrieve an entity.
     * @throws DocumentException Thrown to indicate an error occurred when trying to apply changes on document properties.
     */
    private void applyPropertyChanges(final @NonNull ServerDocument documentServer, final @NonNull List<ValueChange> changes) throws EntityException
    {
        for (ValueChange change : changes)
        {
            switch (change.getPropertyName())
            {
                case IDocument.DOCUMENT_TAGS ->
                        documentServer.setTags((String) change.getRight());

                case IDocument.DOCUMENT_TYPE ->
                        documentServer.setDocumentType((DocumentType) change.getRight());

                case IEntity.BASE_DESCRIPTION ->
                        documentServer.setDescription((String) change.getRight());

                case IEntity.BASE_NAME ->
                        documentServer.setName((String) change.getRight());

                case IEntity.BASE_REFERENCE ->
                        documentServer.setReference((String) change.getRight());

                case IStatusEntity.BASE_STATUS_TYPE ->
                        documentServer.setStatusType((EntityStatusType) change.getRight());

                case IEntity.BASE_PARENT_TYPE ->
                        documentServer.setParentType((EntityType) change.getRight());

                case IEntity.BASE_PARENT_ID -> {
                    //ServerEntity parent = (ServerEntity) factory.from(documentServer.getParentType(), UUID.fromString((String) change.getRight()));
                    //documentServer.setParent(parent);
                    // TODO Find another solution!
                    throw new EntityException(String.format("Not yet implemented handling field of type: %s", change.getPropertyName()));
                }

                default -> throw new DocumentException(String.format("Document property change for property name: '%s' is not handled!", change.getPropertyName()));
            }
        }
    }

    /**
     * Filter the changes on properties detected to the metadata part of a document.
     * @param changes List of changes.
     * @return List of filtered changes.
     */
    private List<ValueChange> filterDocumentChanges(final @NonNull List<ValueChange> changes, final @NonNull IEntityFilter filter)
    {
        List<ValueChange> filtered = new ArrayList<>();

        for (ValueChange change : changes)
        {
            if (filter.contains(change.getPropertyName()))
            {
                LOGGER.debug(String.format("-> property name: [%-15.15s], original: [%-50.50s], updated: [%s]", change.getPropertyName(), change.getLeft(), change.getRight()));
                filtered.add(change);
            }
        }

        return filtered;
    }

    //@Transactional
    @Override
    public ServerDocument save(ServerDocument document)
    {
        // Save the content file, if one exist and not already saved!
        if (document.getContent() != null && document.getContentId() == null)
        {
            document = (ServerDocument) documentStore.getStore().setContent(document, document.getContent());
            LOGGER.debug(String.format("%s successfully saved with content id: '%s'", document.getIdentity(), document.getContentId()));
        }

        document = documentRepository.save(document);
        LOGGER.debug(String.format("%s saved successfully", document.getIdentity()));

        return document;
    }

    @Override
    public ServerDocument saveAndFlush(ServerDocument document)
    {
        return documentRepository.saveAndFlush(document); //TODO What about the content ?
    }

    @Override
    public void deleteById(UUID id) throws DocumentException
    {
        ServerDocument document = findById(id);

        if (document == null)
        {
            throw new DocumentException(String.format("Document with id: '%s' cannot be found!", id.toString()));
        }

        // If a content file is associated, then delete it!
        if (document.getContentId() != null)
        {
            LOGGER.debug(String.format("Successfully deleted document content with id: '%s'", document.getContentId()));
            documentStore.getStore().unsetContent(document);
        }

        LOGGER.debug(String.format("Successfully deleted document with id: '%s'", document.getId()));
        documentRepository.deleteById(id);
    }

    @Override
    public List<ServerDocument> findAll()
    {
        List<ServerDocument> documents = documentRepository.findAll();
        documents.forEach(this::loadContent);

        return documents;
    }

    @Override
    public List<ServerDocument> findByParentId(final @NonNull UUID parentId)
    {
        return documentRepository.findByParentId(parentId);
    }

    @Override
    public void loadContent(ServerDocument document)
    {
        document.setContent(documentStore.getStore().getContent(document));
    }

    @Override
    public void loadContent(UUID documentId) throws DocumentException
    {
        ServerDocument document = findById(documentId);
        if (document != null)
        {
            loadContent(document);
        }

        throw new DocumentException(String.format("Document with id: '%s' cannot be found!", documentId.toString()));
    }

    @Override
    public List<ServerDocument> search(@NonNull DocumentQuery search) throws QueryConditionException
    {
        List<ServerDocument> documents;

        GenericSpecification<ServerDocument> specification = (GenericSpecification<ServerDocument>) search.getSpecification();

        documents = documentRepository.findAll(specification);
        documents.forEach(this::loadContent);

        return documents;
    }

//    private DocumentServer merge(final @NonNull DocumentServer source, final @NonNull DocumentServer target) throws DocumentException, EntityException
//    {
//        IServerEntity entity;
//
//        Diff diff = EntityComparator.getJavers().compare(source, target);
//
//        // Check if some object fields have changed?
//        for (ValueChange change : diff.getChangesByType(ValueChange.class))
//        {
//            switch (change.getPropertyName())
//            {
//                case AbstractQueryAudit.BASE_CREATED_DATE:
//                case AbstractQueryAudit.BASE_MODIFIED_DATE:
//                case AbstractQueryAudit.BASE_CREATED_BY:
//                case AbstractQueryAudit.BASE_MODIFIED_BY:
//                    // Do nothing as they are handled by the repository itself!
//                    break;
//
//                case BaseQueryEntity.BASE_DESCRIPTION:
//                    target.setDescription(source.getDescription());
//                    break;
//
//                case BaseQueryEntity.BASE_NAME:
//                    target.setName(source.getName());
//                    break;
//
//                case BaseQueryEntity.BASE_REFERENCE:
//                    target.setReference(source.getReference());
//                    break;
//
//                case AbstractQueryStatus.BASE_STATUS_TYPE:
//                    target.setStatusType(source.getStatusType());
//                    break;
//
//                case BaseQueryEntity.BASE_ENTITY_ID:
//                    throw new DocumentException("Cannot change document entity identifier!");
//
//                case BaseQueryEntity.BASE_ENTITY_TYPE:
//                    throw new DocumentException("Cannot change document entity type!");
//
//                case BaseQueryEntity.BASE_PARENT_TYPE:
//                    target.setParentType(source.getParentType());
//                    break;
//
//                case DocumentQuery.DOCUMENT_TAGS:
//                    target.setTags(source.getTags());
//                    break;
//
//                case DocumentQuery.DOCUMENT_TYPE:
//                    target.setDocumentType(source.getDocumentType());
//                    break;
//
//                    //TODO Handle the document content!
//
//                default:
//                    LOGGER.warn(String.format("Property name: %s not handled!", change.getPropertyName()));
//                    break;
//            }
//        }
//
//        // Check if some object references have changed.
//        for (ReferenceChange change : diff.getChangesByType(ReferenceChange.class))
//        {
//            switch (change.getPropertyName())
//            {
//                case BaseQueryEntity.BASE_PARENT:
//                    entity = factory.from(source.getParent().getEntityType(), source.getParent().getId());
//                    entity.removeDocument(source);
//                    factory.getEntityManager().persist(entity);
//
//                    entity = factory.from(target.getParent().getEntityType(), target.getParent().getId());
//                    entity.addDocument(target);
//                    factory.getEntityManager().persist(entity);
//
//                    source.setParent(source.getParent());
//                    break;
//
//                default:
//                    LOGGER.warn(String.format("Property name: %s not handled!", change.getPropertyName()));
//                    break;
//            }
//        }
//
//        return target;
//    }
}

