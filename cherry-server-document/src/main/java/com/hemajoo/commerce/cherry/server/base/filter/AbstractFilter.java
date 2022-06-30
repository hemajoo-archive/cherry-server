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
package com.hemajoo.commerce.cherry.server.base.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides an abstract implementation of a server data model entity filter.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public abstract class AbstractFilter implements IEntityFilter
{
    /**
     * List of filters.
     */
    @JsonIgnore
    protected final List<String> filters = new ArrayList<>();

    @Override
    public final boolean contains(final @NonNull String propertyName)
    {
        return filters.contains(propertyName);
    }
}
