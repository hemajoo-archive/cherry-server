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
package com.hemajoo.commerce.cherry.server.data.model.person.address.email;

import com.hemajoo.commerce.cherry.server.data.model.base.IServerEntity;
import com.hemajoo.commerce.cherry.server.data.model.base.ServerEntity;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.type.EntityType;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.person.address.email.IEmailAddress;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.person.address.type.AddressType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * Represents a server data model <b>email address</b> entity.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Log4j2
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "EMAIL_ADDRESS")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ServerEmailAddress extends ServerEntity implements IEmailAddress, IServerEntity
{
    /**
     * Email address.
     */
    @Getter
    @Setter
    @NotNull(message = "Email address: 'email' cannot be null!")
    @Email(message = "Email address: '${validatedValue}' is not a valid email!")
    @Column(name = "EMAIL")
    private String email;

    /**
     * Is it the default email address?
     */
    @Getter
    @Setter
    @Column(name = "IS_DEFAULT", columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isDefaultEmail;

    /**
     * Email type.
     */
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "ADDRESS_TYPE")
    private AddressType addressType;

    /**
     * Creates a new persistent email address.
     */
    public ServerEmailAddress()
    {
        super(EntityType.EMAIL_ADDRESS);
    }
}
