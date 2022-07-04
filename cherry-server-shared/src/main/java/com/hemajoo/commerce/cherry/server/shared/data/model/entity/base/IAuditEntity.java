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

import java.io.Serializable;
import java.util.Date;

/**
 * Data model entities implementing this interface gain the ability to expose manage their audit information (creation and modification dates and who created and modified the entity).
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @since Cherry 0.1.0
 * @version 1.0.0
 */
public interface IAuditEntity extends Serializable
{
    /**
     * Returns the creation date.
     * @return Date.
     */
    Date getCreatedDate(); //TODO Should be changed to a ZonedDateTime

    /**
     * Sets the creation date.
     * @param date Creation date.
     */
    void setCreatedDate(final Date date);  //TODO Should be changed to a ZonedDateTime

    /**
     * Returns the last modification date.
     * @return Date.
     */
    Date getModifiedDate();  //TODO Should be changed to a ZonedDateTime

    /**
     * Sets the last modification date.
     * @param date Modification date.
     */
    void setModifiedDate(final Date date);  //TODO Should be changed to a ZonedDateTime

    /**
     * Returns the creation author.
     * @return Author.
     */
    String getCreatedBy();

    /**
     * Sets the creation author.
     * @param author Creation author.
     */
    void setCreatedBy(final String author);

    /**
     * Returns the last modification author.
     * @return Author.
     */
    String getModifiedBy();

    /**
     * Sets the last modification author.
     * @param author Modification author.
     */
    void setModifiedBy(final String author);
}
