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
package com.hemajoo.commerce.cherry.server.document.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hemajoo.commerce.cherry.server.commons.entity.query.BaseQueryEntity;
import com.hemajoo.commerce.cherry.server.commons.entity.query.condition.QueryField;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.type.EntityType;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.type.FieldDataType;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.document.type.DocumentType;
import lombok.Builder;

/**
 * Represents a <b>query</b> object for issuing queries on document entities.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public class DocumentQuery extends BaseQueryEntity
{
    @JsonIgnore
    public static final String DOCUMENT_EXTENSION = "extension";

    @JsonIgnore
    public static final String DOCUMENT_FILENAME = "filename";

    @JsonIgnore
    public static final String DOCUMENT_CONTENT_PATH = "contentPath";

    @JsonIgnore
    public static final String DOCUMENT_CONTENT_LENGTH = "contentLength";

    @JsonIgnore
    public static final String DOCUMENT_TYPE = "documentType";

    @JsonIgnore
    public static final String DOCUMENT_MIMETYPE = "mimeType";

    @JsonIgnore
    public static final String DOCUMENT_TAGS = "tags";

    /**
     * Creates a new <b>query</b> instance for the documents.
     */
    public DocumentQuery()
    {
        super(EntityType.DOCUMENT);

        fields.add(QueryField.builder()
                .withFieldName(DOCUMENT_EXTENSION)
                .withFieldType(FieldDataType.STRING)
                .build());
        fields.add(QueryField.builder()
                .withFieldName(DOCUMENT_FILENAME)
                .withFieldType(FieldDataType.STRING)
                .build());
        fields.add(QueryField.builder()
                .withFieldName(DOCUMENT_CONTENT_PATH)
                .withFieldType(FieldDataType.STRING)
                .build());
        fields.add(QueryField.builder()
                .withFieldName(DOCUMENT_TYPE)
                .withFieldType(FieldDataType.ENUM)
                .withClassType(DocumentType.class)
                .build());
        fields.add(QueryField.builder()
                .withFieldName(DOCUMENT_CONTENT_LENGTH)
                .withFieldType(FieldDataType.LONG)
                .build());
        fields.add(QueryField.builder()
                .withFieldName(DOCUMENT_MIMETYPE)
                .withFieldType(FieldDataType.STRING)
                .build());
        fields.add(QueryField.builder()
                .withFieldName(DOCUMENT_TAGS)
                .withFieldType(FieldDataType.STRING)
                .build());
    }

    /**
     * Creates a new document query instance.
     * @return Document query.
     */
    @Builder
    public static DocumentQuery create()
    {
        return new DocumentQuery();
    }
}
