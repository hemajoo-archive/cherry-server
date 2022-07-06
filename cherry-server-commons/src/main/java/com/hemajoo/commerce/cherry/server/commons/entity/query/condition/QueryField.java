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
package com.hemajoo.commerce.cherry.server.commons.entity.query.condition;

import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.type.EntityType;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.type.FieldDataType;
import lombok.*;

/**
 * Represents a query <b>field</b>.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@NoArgsConstructor
public class QueryField
{
    /**
     * Field name.
     */
    @Getter
    @Setter
    private String fieldName;

    /**
     * Field data type.
     */
    @Getter
    @Setter
    private FieldDataType fieldType;

    /**
     * Field class type.
     */
    @Getter
    @Setter
    private Class<?> fieldClassType;

    /**
     * Entity type the field references.
     */
    @Getter
    @Setter
    private EntityType entityType;

    /**
     * Creates a new query field.
     * @param fieldName Field name.
     * @param fieldType Field type.
     * @param classType Class type (of the field's value).
     * @param entityType Entity type.
     */
    @Builder(setterPrefix = "with")
    public QueryField(final @NonNull String fieldName, final @NonNull FieldDataType fieldType, final Class<?> classType, final EntityType entityType)
    {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.fieldClassType = classType;
        this.entityType = entityType;
    }
}
