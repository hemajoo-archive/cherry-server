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
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.type.EntityStatusType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * Represents an abstract client data model status aware entity.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractClientStatusEntity extends AbstractClientAuditEntity
{
    /**
     * Entity status type.
     */
    //@JsonProperty("status")
    //@Enumerated(EnumType.STRING)
    private EntityStatusType statusType;

    /**
     * Timestamp since this entity is inactive.
     */
    //@JsonProperty("inactiveSince")
    @Schema(hidden = true) // As it is set automatically when status changes.
    private Date since;

    /**
     * Returns if the entity is active?
     * @return {@code True} if the entity is active, {@code false} otherwise.
     */
    @Schema(hidden = true) // Status type is used instead!
    @JsonIgnore
    public final boolean isActive()
    {
        return statusType == EntityStatusType.ACTIVE;
    }

    /**
     * Sets the underlying entity to {@link EntityStatusType#ACTIVE}.
     */
    public final void setActive()
    {
        statusType = EntityStatusType.ACTIVE;
        since = null;
    }

    /**
     * Sets the underlying entity to {@link EntityStatusType#INACTIVE}.
     */
    public final void setInactive()
    {
        statusType = EntityStatusType.INACTIVE;
        since = new Date(System.currentTimeMillis());
    }

    /**
     * Sets the status type.
     * @param status Status.
     */
    public void setStatusType(EntityStatusType status)
    {
        if (status != this.statusType)
        {
            if (status == EntityStatusType.INACTIVE)
            {
                setInactive();
            }
            else
            {
                setActive();
            }
        }
    }
}
