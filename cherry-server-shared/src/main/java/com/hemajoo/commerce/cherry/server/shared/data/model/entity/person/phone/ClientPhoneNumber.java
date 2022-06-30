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
package com.hemajoo.commerce.cherry.server.shared.data.model.entity.person.phone;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.ClientEntity;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.identity.Identity;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.type.EntityType;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.person.phone.type.PhoneNumberCategoryType;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.person.phone.type.PhoneNumberType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Represents a <b>client phone number entity</b>.
 * Provides an implementation of a phone number client data model entity.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ClientPhoneNumber extends ClientEntity implements IClientPhoneNumber
{
    /**
     * Phone number.
     */
    @Schema(name = "number", description = "Phone number", example = "0652897412")
    private String number;

    /**
     * Phone number country code (ISO Alpha-3 code).
     */
    @Schema(name = "countryCode", description = "Phone number country code (ISO Alpha-3)", example = "FRA")
    private String countryCode;

    /**
     * Phone number type.
     */
    //@Enumerated(EnumType.STRING)
    @Schema(name = "phoneType", description = "Phone number type", example = "PRIVATE")
    private PhoneNumberType phoneType;

    /**
     * Phone number category type.
     */
    //@Enumerated(EnumType.STRING)
    @Schema(name = "categoryType", description = "Phone number category type", example = "MOBILE")
    private PhoneNumberCategoryType categoryType;

    /**
     * Is it a default phone number?
     */
    @Schema(name = "isDefault", description = "Is it the default phone number?", example = "true")
    private Boolean isDefault;

    /**
     * The entity identity this phone number belongs to.
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties("phoneNumbers")
    @Schema(name = "owner", description = "Entity identity this phone number belongs to", example = "1")
    private Identity owner;

    /**
     * Creates a new phone number.
     */
    public ClientPhoneNumber()
    {
        super(EntityType.PHONE_NUMBER);
    }
}
