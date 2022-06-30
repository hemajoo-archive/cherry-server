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
package com.hemajoo.commerce.cherry.server.shared.data.model.entity.person.address.postal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.ClientEntity;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.identity.Identity;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.type.EntityType;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.person.address.postal.type.PostalAddressCategoryType;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.person.address.type.AddressType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Provides an implementation of a postal address client data model entity.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Data
//@Builder(setterPrefix = "with") // Does not work well with MapStruct!
@EqualsAndHashCode(callSuper = false)
public class PostalAddressClient extends ClientEntity implements IClientPostalAddress
{
    /**
     * Postal address street name.
     */
    @Schema(name = "name", description = "Postal address street name", example = "Rue de la Libération")
    private String streetName;

    /**
     * Postal address street number.
     */
    @Schema(name = "number", description = "Postal address street number", example = "18 bis")
    private String streetNumber;

    /**
     * Postal address locality.
     */
    @Schema(name = "locality", description = "Postal address locality (city)", example = "Paris")
    private String locality;

    /**
     * Postal address country code (ISO Alpha-3 code).
     */
    @Schema(name = "countryCode", description = "Postal address country code (ISO Alpha-3)", example = "FRA")
    private String countryCode;

    /**
     * Postal address zip (postal) code.
     */
    @Schema(name = "zipCode", description = "Postal address zip code (postal code)", example = "75000")
    private String zipCode;

    /**
     * Postal address area/region/department depending on the country.
     */
    @Schema(name = "area", description = "Postal address area (region/state)", example = "Île-de-France")
    private String area;

    /**
     * Is it a default postal address?
     */
    @Schema(name = "isDefault", description = "Is it a default postal address ?", example = "true")
    private Boolean isDefault;

    /**
     * Postal address type.
     */
    @Schema(name = "addressType", description = "Postal address type", example = "PRIVATE")
    //@Enumerated(EnumType.STRING)
    private AddressType addressType;

    /**
     * Postal address category.
     */
    @Schema(name = "categoryType", description = "Postal address category type", example = "POSTAL")
    //@Enumerated(EnumType.STRING)
    private PostalAddressCategoryType categoryType;

    /**
     * The person identifier this postal address belongs to.
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties("postalAddresses")
    @Schema(name = "owner", description = "Entity identity this postal address belongs to", example = "1")
    private Identity owner;

    /**
     * Creates a new postal address.
     */
    public PostalAddressClient()
    {
        super(EntityType.POSTAL_ADDRESS);
    }
}
