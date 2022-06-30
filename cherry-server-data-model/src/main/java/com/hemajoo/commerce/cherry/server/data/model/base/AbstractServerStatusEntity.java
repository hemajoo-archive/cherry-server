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
package com.hemajoo.commerce.cherry.server.data.model.base;

import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.IStatusEntity;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.type.EntityStatusType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Represents an abstract server data model status aware entity.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class AbstractServerStatusEntity extends AbstractServerAuditEntity implements IStatusEntity, IServerEntity
{
    /**
     * Entity status.
     */
    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_TYPE", length = 50)
    private EntityStatusType statusType;

    /**
     * Inactivity time stamp information (server time) that must be filled when the entity becomes inactive.
     */
    @Getter
    @Setter
    @Schema(hidden = true)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SINCE", length = 26)
    private Date since;

    /**
     * Returns if this entity is active?
     * @return {@code True} if the entity is active, {@code false} otherwise.
     */
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
     * Sets the status of the entity.
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
