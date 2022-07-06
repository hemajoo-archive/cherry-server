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

import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.identity.EntityIdentity;

/**
 * Defines the behavior of a client data model entity.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IClientEntity extends IEntity
{
    /**
     * Returns the parent entity this entity belongs to.
     * @return Parent entity identity.
     */
    EntityIdentity getParent();

    /**
     * Sets the parent entity identity.
     * @param parent Parent entity identity.
     */
    void setParent(final EntityIdentity parent);
}
