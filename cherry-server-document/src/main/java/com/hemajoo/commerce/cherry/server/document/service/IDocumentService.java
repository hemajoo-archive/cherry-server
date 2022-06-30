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

import com.hemajoo.commerce.cherry.server.data.model.document.ServerDocument;
import com.hemajoo.commerce.cherry.server.document.repository.IDocumentRepository;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.exception.EntityException;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.query.condition.QueryConditionException;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.document.ClientDocument;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.document.exception.DocumentException;
import lombok.NonNull;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

/**
 * Persistence service for server data model document entities.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IDocumentService
{
    /**
     * Return the underlying repository.
     * @return Document repository.
     */
    IDocumentRepository getRepository();

    /**
     * Return the number of documents.
     * @return Number of documents.
     */
    Long count();

    /**
     * Find a document given its identifier.
     * @param id Document identifier.
     * @return Document if found, null otherwise.
     * @throws DocumentException raised if the given document id has not been found!
     */
    ServerDocument findById(UUID id) throws DocumentException;

    /**
     * Update a document entity.
     * @param document Document to update.
     * @return Updated document.
     * @throws EntityException Thrown in case an error occurred while updating a document entity.
     */
    ServerDocument update(final ServerDocument document) throws EntityException;

    /**
     * Update a document metadata.
     * @param document Client document containing the metadata information to update.
     * @return Updated server document.
     * @throws EntityException Thrown to indicate an error occurred when updating a document metadata information.
     */
    ServerDocument updateMetadata(@NonNull ClientDocument document) throws EntityException;

    /**
     * Upload a document and its content.
     * @param document Document.
     * @param file File (document content).
     * @return Document.
     * @throws EntityException Thrown to indicate an error occurred when uploading a document content.
     */
    ServerDocument uploadContent(final @NonNull ServerDocument document, final @NonNull MultipartFile file) throws EntityException;

    /**
     * Update a document content.
     * @param document Document.
     * @param file File (document content).
     * @return Document.
     * @throws EntityException Thrown to indicate an error occurred when updating a document content.
     */
    ServerDocument updateContent(final @NonNull ServerDocument document, final @NonNull MultipartFile file) throws EntityException;

    Resource downloadContent(final @NonNull ServerDocument document);

    /**
     * Save a document.
     * @param document Document to save.
     * @return Saved document.
     * @throws DocumentException Raised if an error occurred while trying to save the document.
     */
    ServerDocument save(ServerDocument document) throws DocumentException;

    /**
     * Save and flush a document.
     * @param document Document.
     * @return Saved document.
     */
    ServerDocument saveAndFlush(ServerDocument document);

    /**
     * Delete a document given its identifier.
     * @param id Document identifier.
     * @throws DocumentException Thrown in case the document to delete does not exist.
     */
    void deleteById(UUID id) throws DocumentException;

    /**
     * Return all the documents.
     * @return List of documents.
     */
    List<ServerDocument> findAll();

    /**
     * Load the content (media file) of the document.
     * @param document Document.
     * @throws DocumentException Raised if an error occurred while trying to load the document.
     */
    void loadContent(ServerDocument document) throws DocumentException;

    /**
     * Load the content (media file) of the document.
     * @param documentId Document identifier.
     * @throws DocumentException Raised if an error occurred while trying to load the document.
     */
    void loadContent(UUID documentId) throws DocumentException;

    /**
     * Search for documents given some criteria.
     * @param search Search object.
     * @return List of documents matching the given criteria.
     */
    List<ServerDocument> search(final @NonNull DocumentQuery search) throws QueryConditionException;

    /**
     * Find the documents belonging to a given parent entity.
     * @param parentId Parent entity identifier.
     * @return List of documents.
     */
    List<ServerDocument> findByParentId(final @NonNull UUID parentId);
}
