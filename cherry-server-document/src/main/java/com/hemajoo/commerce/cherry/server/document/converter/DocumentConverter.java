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
package com.hemajoo.commerce.cherry.server.document.converter;

import com.hemajoo.commerce.cherry.server.base.mapper.AbstractDocumentMapper;
import com.hemajoo.commerce.cherry.server.base.mapper.AbstractEntityMapper;
import com.hemajoo.commerce.cherry.server.commons.entity.mapper.CycleAvoidingMappingContext;
import com.hemajoo.commerce.cherry.server.data.model.document.ServerDocument;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.exception.EntityException;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.identity.EntityIdentity;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.document.ClientDocument;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Component to convert between client and server document data model entities.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Component
public class DocumentConverter
{
    /**
     * Entity manager.
     */
    @Getter
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Converts from a server document entity to an entity identity.
     * @param server Server document entity.
     * @return Entity identity.
     */
    public EntityIdentity fromServerToIdentity(ServerDocument server)
    {
        return AbstractDocumentMapper.INSTANCE.fromServerToIdentity(server, new CycleAvoidingMappingContext());
    }

    /**
     * Converts from an entity identity to a server document entity.
     * @param identity Entity identity.
     * @return Server document entity.
     * @throws EntityException Thrown to indicate an error occurred when trying to convert a document.
     */
    public ServerDocument fromIdentityToServer(EntityIdentity identity) throws EntityException
    {
        return AbstractEntityMapper.INSTANCE.map(identity, entityManager);
    }

    /**
     * Converts from a client document entity to a server document entity.
     * @param client Client document entity.
     * @return Server document entity.
     * @throws EntityException Thrown to indicate an error occurred when trying to convert a document.
     */
    public ServerDocument fromClientToServer(ClientDocument client) throws EntityException
    {
        return AbstractDocumentMapper.INSTANCE.fromClientToServer(client, new CycleAvoidingMappingContext(), entityManager);
    }

    /**
     * Converts from a server document entity to a client document entity.
     * @param server Server document entity.
     * @return Client document entity.
     */
    public ClientDocument fromServerToClient(ServerDocument server)
    {
        return AbstractDocumentMapper.INSTANCE.fromServerToClient(server, new CycleAvoidingMappingContext());
    }

    /**
     * Copy a server document entity.
     * @param server Server document entity.
     * @return Copied server document entity.
     * @throws EntityException Thrown to indicate an error occurred when trying to copy a document.
     */
    public static ServerDocument copy(ServerDocument server) throws EntityException
    {
        return AbstractDocumentMapper.INSTANCE.copy(server, new CycleAvoidingMappingContext());
    }

    /**
     * Copy a client document entity.
     * @param client Client document entity.
     * @return Copied client document entity.
     * @throws EntityException Thrown to indicate an error occurred when trying to copy a document.
     */
    public static ClientDocument copy(ClientDocument client) throws EntityException
    {
        return AbstractDocumentMapper.INSTANCE.copy(client, new CycleAvoidingMappingContext());
    }
}
