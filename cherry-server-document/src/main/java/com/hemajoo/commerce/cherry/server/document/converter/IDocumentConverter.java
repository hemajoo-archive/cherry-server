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

import com.hemajoo.commerce.cherry.server.data.model.document.IServerDocument;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.exception.EntityException;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.identity.Identity;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.document.IClientDocument;

public interface IDocumentConverter
{
    /**
     * Converts from a server document entity to an entity identity.
     * @param server Server document entity.
     * @return Entity identity.
     */
    Identity fromServerToIdentity(IServerDocument server);

    /**
     * Converts from an entity identity to a server document entity.
     * @param identity Entity identity.
     * @return Server document entity.
     * @throws EntityException Thrown to indicate an error occurred when trying to convert a document.
     */
    IServerDocument fromIdentityToServer(Identity identity) throws EntityException;

    /**
     * Converts from a client document entity to a server document entity.
     * @param client Client document entity.
     * @return Server document entity.
     * @throws EntityException Thrown to indicate an error occurred when trying to convert a document.
     */
    IServerDocument fromClientToServer(IClientDocument client) throws EntityException;

    /**
     * Converts from a server document entity to a client document entity.
     * @param server Server document entity.
     * @return Client document entity.
     */
    IClientDocument fromServerToClient(IServerDocument server);
}
