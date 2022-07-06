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
package com.hemajoo.commerce.cherry.server.shared.data.model.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.identity.Identity;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.identity.IdentityAware;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.document.exception.DocumentException;

/**
 * Entities implementing this interface are tagged as being part of the <b>Cherry</b> data model (server or client side).
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @since Cherry 0.1.0
 * @version 1.0.0
 */
public interface IEntity extends IStatusEntity, Identity, IdentityAware
{
    /**
     * Field: <b>id</b> of an entity.
     */
    @JsonIgnore
    public static final String BASE_ENTITY_ID = "id";

    /**
     * Field: <b>entityType</b> of an entity.
     */
    @JsonIgnore
    public static final String BASE_ENTITY_TYPE = "entityType";

    /**
     * Field: <b>name</b> of an entity.
     */
    @JsonIgnore
    public static final String BASE_NAME = "name";

    /**
     * Field: <b>description</b> of an entity.
     */
    @JsonIgnore
    public static final String BASE_DESCRIPTION = "description";

    /**
     * Field: <b>reference</b> of an entity.
     */
    @JsonIgnore
    public static final String BASE_REFERENCE = "reference";

    /**
     * Field: <b>parentId</b> of an entity.
     */
    @JsonIgnore
    public static final String BASE_PARENT_ID = "parentId";

    /**
     * Field: <b>parent</b> of an entity.
     */
    @JsonIgnore
    public static final String BASE_PARENT = "parent";

    /**
     * Field: <b>parentType</b> of an entity.
     */
    @JsonIgnore
    public static final String BASE_PARENT_TYPE = "parentType";

    /**
     * Field: <b>tags</b> of an entity.
     */
    @JsonIgnore
    public static final String BASE_TAGS = "tags";

    /**
     * Returns the entity name.
     * @return Entity name.
     */
    String getName();

    /**
     * Sets the entity name.
     * @param name Entity name.
     */
    void setName(final String name);

    /**
     * Returns the entity description.
     * @return Entity description.
     */
    String getDescription();

    /**
     * Sets the entity description.
     * @param description Entity description.
     */
    void setDescription(final String description);

    /**
     * Returns the entity reference.
     * @return Entity reference.
     */
    String getReference();

    /**
     * Sets the entity reference.
     * @param reference Entity reference.
     */
    void setReference(final String reference);

    /**
     * Adds a tag.
     * @param tag Tag.
     */
    void addTag(final String tag);

    /**
     * Removes a tag.
     * @param tag Tag to remove.
     */
    void removeTag(final String tag);

    /**
     * Returns a random tag.
     * @return Random tag or <b>null</b> if no tag exist.
     * @throws DocumentException Thrown in case an error occurred while trying to get a random tag.
     */
    String getRandomTag() throws DocumentException;

    /**
     * Checks if the given tag exist.
     * @param tag Tag.
     * @return {@code True} if the tag exist, {@code false} otherwise.
     */
    boolean existTag(final String tag);

    /**
     * Returns the number of tags.
     * @return Number of tags.
     */
    int getTagCount();
}
