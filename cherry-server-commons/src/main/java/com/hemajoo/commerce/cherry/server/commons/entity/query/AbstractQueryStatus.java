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
package com.hemajoo.commerce.cherry.server.commons.entity.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hemajoo.commerce.cherry.server.commons.entity.query.condition.QueryField;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.type.EntityStatusType;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.type.EntityType;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.type.FieldDataType;
import lombok.Data;
import lombok.NonNull;

/**
 * Represents an abstract <b>query</b> object for the <b>status</b> part of entities.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Data
public abstract class AbstractQueryStatus extends AbstractQueryAudit
{
    /**
     * Field: <b>statusType</b> of an entity.
     */
    @JsonIgnore
    public static final String BASE_STATUS_TYPE = "statusType";

    /**
     * Field: <b>since</b> (inactive date) of an entity.
     */
    @JsonIgnore
    public static final String BASE_SINCE = "since";

    /**
     * Creates a new abstract status search.
     * @param entityType Entity type.
     */
    public AbstractQueryStatus(final @NonNull EntityType entityType)
    {
        super(entityType);

        fields.add(QueryField.builder()
                .withFieldName(BASE_STATUS_TYPE)
                .withFieldType(FieldDataType.ENUM)
                .withClassType(EntityStatusType.class)
                .build());
        fields.add(QueryField.builder()
                .withFieldName(BASE_SINCE)
                .withFieldType(FieldDataType.DATE)
                .build());
    }

    /**
     * Creates a new abstract status search.
     */
    public AbstractQueryStatus()
    {
        fields.add(QueryField.builder()
                .withFieldName(BASE_STATUS_TYPE)
                .withFieldType(FieldDataType.ENUM)
                .withClassType(EntityStatusType.class)
                .build());
        fields.add(QueryField.builder()
                .withFieldName(BASE_SINCE)
                .withFieldType(FieldDataType.DATE)
                .build());
    }
}
