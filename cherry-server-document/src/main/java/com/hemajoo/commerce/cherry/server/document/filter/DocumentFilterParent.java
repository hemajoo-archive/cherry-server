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
package com.hemajoo.commerce.cherry.server.document.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hemajoo.commerce.cherry.server.base.filter.AbstractFilter;

/**
 * Represents a <b>document metadata</b> filter object.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public class DocumentFilterParent extends AbstractFilter
{
    /**
     * Filter for the document parent type.
     */
    @JsonIgnore
    public static final String DOCUMENT_PARENT_TYPE = "parentType";

    /**
     * Filter for the document parent id.
     */
    @JsonIgnore
    public static final String DOCUMENT_PARENT_ID = "parentId";

    /**
     * Creates a new <b>document parent</b> filter object.
     */
    public DocumentFilterParent()
    {
        filters.add(DOCUMENT_PARENT_TYPE);
        filters.add(DOCUMENT_PARENT_ID);
    }
}
