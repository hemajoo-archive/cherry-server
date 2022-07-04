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

import com.hemajoo.commerce.cherry.server.commons.entity.query.condition.QueryCondition;
import com.hemajoo.commerce.cherry.server.commons.entity.query.condition.QueryConditionException;
import lombok.NonNull;

/**
 * Defines the behavior of a <b>query</b> object.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IQuery
{
    /**
     * Adds a query condition.
     * @param condition Condition.
     * @param <T> Query object type.
     * @return Query object type.
     * @throws QueryConditionException Thrown to indicate an error occurred when adding a query condition.
     */
    <T extends BaseQueryEntity> T addCondition(final @NonNull QueryCondition condition) throws QueryConditionException;

    /**
     * Returns the specification for the query object.
     * @return Specification object.
     */
    GenericSpecification<?> getSpecification();

    /**
     * Validates the conditions of the query object.
     * @throws QueryConditionException Thrown to indicate an error occurred when validating the conditions of a query.
     */
    void validate() throws QueryConditionException;
}
