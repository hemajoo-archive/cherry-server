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
package com.hemajoo.commerce.cherry.server.data.model.person;

import com.hemajoo.commerce.cherry.server.data.model.base.IServerEntity;
import com.hemajoo.commerce.cherry.server.data.model.base.ServerEntity;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.type.EntityType;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.person.address.postal.IPostalAddress;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.person.address.postal.type.PostalAddressCategoryType;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.person.address.type.AddressType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Represents a server postal address entity.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
@Table(name = "POSTAL_ADDRESS")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class PostalAddressServer extends ServerEntity implements IPostalAddress, IServerEntity
{
    /**
     * Postal address street name.
     */
    @Getter
    @Setter
    @NotNull(message = "Postal address: 'streetName' cannot be null!")
    @Column(name = "STREET_NAME")
    private String streetName;

    /**
     * Postal address street number.
     */
    @Getter
    @Setter
    @NotNull(message = "Postal address: 'streetNumber' cannot be null!")
    @Column(name = "STREET_NUMBER")
    private String streetNumber;

    /**
     * Postal address locality.
     */
    @Getter
    @Setter
    @NotNull(message = "Postal address: 'locality' cannot be null!")
    @Column(name = "LOCALITY")
    private String locality;

    /**
     * Postal address country code (ISO Alpha-3 code).
     */
    @Getter
    @Setter
    @NotNull(message = "Postal address: 'countryCode' cannot be null!")
    @Column(name = "COUNTRY_CODE", length = 3)
    private String countryCode;

    /**
     * Postal address zip (postal) code.
     */
    @Getter
    @Setter
    @NotNull(message = "Postal address: 'zipCode' cannot be null!")
    @Column(name = "ZIP_CODE", length = 10)
    private String zipCode;

    /**
     * Postal address area/region/department depending on the country.
     */
    @Getter
    @Setter
    @Column(name = "AREA")
    private String area;

    /**
     * Is it a default postal address?
     */
    @Getter
    @Setter
    @Column(name = "IS_DEFAULT")
    private Boolean isDefault;

    /**
     * Postal address type.
     */
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "ADDRESS_TYPE")
    private AddressType addressType;

    /**
     * Postal address category type.
     */
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORY_TYPE")
    private PostalAddressCategoryType categoryType;

    /**
     * The person identifier this postal address belongs to.
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Getter
    @Setter
    @ManyToOne(targetEntity = ServerPerson.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_ID", nullable = false)
    private ServerPerson person;

    /**
     * Creates a new postal address.
     */
    public PostalAddressServer()
    {
        super(EntityType.POSTAL_ADDRESS);
    }
}
