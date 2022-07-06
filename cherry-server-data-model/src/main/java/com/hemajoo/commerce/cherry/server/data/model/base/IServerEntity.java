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
package com.hemajoo.commerce.cherry.server.data.model.base;

import com.hemajoo.commerce.cherry.server.data.model.document.IServerDocument;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.IEntity;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.exception.EntityException;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.document.exception.DocumentException;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

/**
 * Entities implementing this interface are tagged as a server data model <b>entity</b>.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @since Cherry 0.1.0
 * @version 1.0.0
 */
public interface IServerEntity extends IEntity
{
    /**
     * Returns the parent entity of this entity (can be null).
     * @return Parent entity if set, <b>null</b>> otherwise.
     */
    ServerEntity getParent();

    /**
     * Sets the parent entity for this entity (can be null).
     * @param parent Parent entity.
     * @throws EntityException Thrown to indicate an error occurred when trying to set the parent entity.
     */
    void setParent(final ServerEntity parent) throws EntityException;

    /**
     * Return the number of documents this entity holds.
     * @return Number of documents.
     */
    int getDocumentCount();

    /**
     * Return the complete list of documents this entity holds.
     * @return List of documents.
     */
    List<IServerDocument> getDocuments();

    /**
     * Add a document to this entity.
     * @param document Document to add.
     * @throws DocumentException Thrown to indicate an error when trying to add a document.
     */
    void addDocument(final @NonNull IServerDocument document) throws DocumentException;

    /**
     * Check if the given document exist in the list of documents for this entity?
     * @param document Document to check.
     * @return <b>True</b>> if the document exist, <b>false</b>> otherwise.
     */
    boolean existDocument(final @NonNull IServerDocument document);

    /**
     * Check if the given document identifier exist in the list of documents for this entity?
     * @param documentId Document identifier to check.
     * @return <b>True</b>> if the document exist, <b>false</b> otherwise.
     */
    boolean existDocument(final UUID documentId);

    /**
     * Remove a document from the list of documents for this entity.
     * @param document Document to remove.
     */
    void removeDocument(final @NonNull IServerDocument document);

    /**
     * Remove a document from the list of documents for this entity.
     * @param documentId Document identifier to remove.
     */
    void removeDocument(final @NonNull UUID documentId);
}
