package com.hemajoo.commerce.cherry.server.base.mapper;

import com.hemajoo.commerce.cherry.server.commons.entity.mapper.CycleAvoidingMappingContext;
import com.hemajoo.commerce.cherry.server.data.model.base.ServerEntity;
import com.hemajoo.commerce.cherry.server.data.model.document.IServerDocument;
import com.hemajoo.commerce.cherry.server.data.model.document.ServerDocument;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.exception.EntityException;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.identity.EntityIdentity;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.document.ClientDocument;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.document.IClientDocument;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.persistence.EntityManager;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-04T14:07:30+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
public class AbstractDocumentMapperImpl extends AbstractDocumentMapper {

    private final AbstractEntityMapper abstractEntityMapper = AbstractEntityMapper.INSTANCE;

    @Override
    public EntityIdentity fromServerToIdentity(IServerDocument entity, CycleAvoidingMappingContext context) {
        EntityIdentity target = context.getMappedInstance( entity, EntityIdentity.class );
        if ( target != null ) {
            return target;
        }

        if ( entity == null ) {
            return null;
        }

        EntityIdentity entityIdentity = new EntityIdentity();

        context.storeMappedInstance( entity, entityIdentity );

        entityIdentity.setEntityType( entity.getEntityType() );
        entityIdentity.setId( entity.getId() );

        return entityIdentity;
    }

    @Override
    public ServerDocument fromClientToServer(IClientDocument document, CycleAvoidingMappingContext context, EntityManager entityManager) throws EntityException {
        ServerDocument target = context.getMappedInstance( document, ServerDocument.class );
        if ( target != null ) {
            return target;
        }

        if ( document == null ) {
            return null;
        }

        ServerDocument serverDocument = new ServerDocument();

        context.storeMappedInstance( document, serverDocument );

        serverDocument.setCreatedDate( document.getCreatedDate() );
        serverDocument.setModifiedDate( document.getModifiedDate() );
        serverDocument.setCreatedBy( document.getCreatedBy() );
        serverDocument.setModifiedBy( document.getModifiedBy() );
        serverDocument.setStatusType( document.getStatusType() );
        serverDocument.setSince( document.getSince() );
        serverDocument.setParent( abstractEntityMapper.map( document.getParent(), entityManager ) );
        serverDocument.setId( document.getId() );
        serverDocument.setEntityType( document.getEntityType() );
        serverDocument.setName( document.getName() );
        serverDocument.setDescription( document.getDescription() );
        serverDocument.setReference( document.getReference() );
        serverDocument.setTags( document.getTags() );
        serverDocument.setDocumentType( document.getDocumentType() );
        serverDocument.setExtension( document.getExtension() );
        serverDocument.setFilename( document.getFilename() );
        serverDocument.setContentId( document.getContentId() );
        serverDocument.setContentLength( document.getContentLength() );
        serverDocument.setMimeType( document.getMimeType() );
        serverDocument.setContentPath( document.getContentPath() );

        return serverDocument;
    }

    @Override
    public ClientDocument fromServerToClient(IServerDocument document, CycleAvoidingMappingContext context) {
        ClientDocument target = context.getMappedInstance( document, ClientDocument.class );
        if ( target != null ) {
            return target;
        }

        if ( document == null ) {
            return null;
        }

        ClientDocument clientDocument = new ClientDocument();

        context.storeMappedInstance( document, clientDocument );

        clientDocument.setCreatedDate( document.getCreatedDate() );
        clientDocument.setModifiedDate( document.getModifiedDate() );
        clientDocument.setCreatedBy( document.getCreatedBy() );
        clientDocument.setModifiedBy( document.getModifiedBy() );
        clientDocument.setStatusType( document.getStatusType() );
        clientDocument.setSince( document.getSince() );
        clientDocument.setId( document.getId() );
        clientDocument.setEntityType( document.getEntityType() );
        clientDocument.setName( document.getName() );
        clientDocument.setDescription( document.getDescription() );
        clientDocument.setReference( document.getReference() );
        clientDocument.setDocuments( serverDocumentListToEntityIdentityList( document.getDocuments(), context ) );
        clientDocument.setParent( serverEntityToEntityIdentity( document.getParent(), context ) );
        clientDocument.setDocumentType( document.getDocumentType() );
        clientDocument.setTags( document.getTags() );
        clientDocument.setExtension( document.getExtension() );
        clientDocument.setFilename( document.getFilename() );
        clientDocument.setContentId( document.getContentId() );
        clientDocument.setContentLength( document.getContentLength() );
        clientDocument.setMimeType( document.getMimeType() );
        clientDocument.setContentPath( document.getContentPath() );

        return clientDocument;
    }

    @Override
    public ServerDocument copy(IServerDocument entity, CycleAvoidingMappingContext context) throws EntityException {
        ServerDocument target = context.getMappedInstance( entity, ServerDocument.class );
        if ( target != null ) {
            return target;
        }

        if ( entity == null ) {
            return null;
        }

        ServerDocument serverDocument = new ServerDocument();

        context.storeMappedInstance( entity, serverDocument );

        serverDocument.setCreatedDate( entity.getCreatedDate() );
        serverDocument.setModifiedDate( entity.getModifiedDate() );
        serverDocument.setCreatedBy( entity.getCreatedBy() );
        serverDocument.setModifiedBy( entity.getModifiedBy() );
        serverDocument.setStatusType( entity.getStatusType() );
        serverDocument.setSince( entity.getSince() );
        serverDocument.setParent( entity.getParent() );
        serverDocument.setId( entity.getId() );
        serverDocument.setEntityType( entity.getEntityType() );
        serverDocument.setName( entity.getName() );
        serverDocument.setDescription( entity.getDescription() );
        serverDocument.setReference( entity.getReference() );
        serverDocument.setTags( entity.getTags() );
        serverDocument.setDocumentType( entity.getDocumentType() );
        serverDocument.setExtension( entity.getExtension() );
        serverDocument.setFilename( entity.getFilename() );
        serverDocument.setContentId( entity.getContentId() );
        serverDocument.setContentLength( entity.getContentLength() );
        serverDocument.setMimeType( entity.getMimeType() );
        serverDocument.setContentPath( entity.getContentPath() );
        if ( serverDocument.getDocuments() != null ) {
            List<ServerDocument> list = entity.getDocuments();
            if ( list != null ) {
                serverDocument.getDocuments().addAll( list );
            }
        }

        return serverDocument;
    }

    @Override
    public ClientDocument copy(IClientDocument entity, CycleAvoidingMappingContext context) throws EntityException {
        ClientDocument target = context.getMappedInstance( entity, ClientDocument.class );
        if ( target != null ) {
            return target;
        }

        if ( entity == null ) {
            return null;
        }

        ClientDocument clientDocument = new ClientDocument();

        context.storeMappedInstance( entity, clientDocument );

        clientDocument.setCreatedDate( entity.getCreatedDate() );
        clientDocument.setModifiedDate( entity.getModifiedDate() );
        clientDocument.setCreatedBy( entity.getCreatedBy() );
        clientDocument.setModifiedBy( entity.getModifiedBy() );
        clientDocument.setStatusType( entity.getStatusType() );
        clientDocument.setSince( entity.getSince() );
        clientDocument.setId( entity.getId() );
        clientDocument.setEntityType( entity.getEntityType() );
        clientDocument.setName( entity.getName() );
        clientDocument.setDescription( entity.getDescription() );
        clientDocument.setReference( entity.getReference() );
        clientDocument.setParent( entity.getParent() );
        clientDocument.setDocumentType( entity.getDocumentType() );
        clientDocument.setTags( entity.getTags() );
        clientDocument.setExtension( entity.getExtension() );
        clientDocument.setFilename( entity.getFilename() );
        clientDocument.setContentId( entity.getContentId() );
        clientDocument.setContentLength( entity.getContentLength() );
        clientDocument.setMimeType( entity.getMimeType() );
        clientDocument.setContentPath( entity.getContentPath() );

        return clientDocument;
    }

    protected List<EntityIdentity> serverDocumentListToEntityIdentityList(List<ServerDocument> list, CycleAvoidingMappingContext context) {
        List<EntityIdentity> target = context.getMappedInstance( list, List.class );
        if ( target != null ) {
            return target;
        }

        if ( list == null ) {
            return null;
        }

        List<EntityIdentity> list1 = new ArrayList<EntityIdentity>( list.size() );
        context.storeMappedInstance( list, list1 );

        for ( ServerDocument serverDocument : list ) {
            list1.add( fromServerToIdentity( serverDocument, context ) );
        }

        return list1;
    }

    protected EntityIdentity serverEntityToEntityIdentity(ServerEntity serverEntity, CycleAvoidingMappingContext context) {
        EntityIdentity target = context.getMappedInstance( serverEntity, EntityIdentity.class );
        if ( target != null ) {
            return target;
        }

        if ( serverEntity == null ) {
            return null;
        }

        EntityIdentity entityIdentity = new EntityIdentity();

        context.storeMappedInstance( serverEntity, entityIdentity );

        entityIdentity.setEntityType( serverEntity.getEntityType() );
        entityIdentity.setId( serverEntity.getId() );

        return entityIdentity;
    }
}
