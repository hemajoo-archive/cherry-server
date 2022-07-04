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

import lombok.NonNull;

/**
 * Server data model entities implementing this interface gain the ability to be filtered.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IEntityFilter
{
    /**
     * Returns if the filters contains the given property name.
     * @param propertyName Property name.
     * @return <b>True</b> if the filters contains the given property name, <b>false</b>> otherwise.
     */
    boolean contains(final @NonNull String propertyName);
}
