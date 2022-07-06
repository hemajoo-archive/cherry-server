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
package com.hemajoo.commerce.cherry.server.shared.data.model.entity.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.ClientEntity;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.type.EntityStatusType;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.type.EntityType;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.person.address.email.ClientEmailAddress;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.person.address.postal.ClientPostalAddress;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.person.address.type.AddressType;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.person.phone.ClientPhoneNumber;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.person.type.GenderType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import me.xdrop.jrand.model.person.PersonType;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Provides implementation of a client data model person entity.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Data
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
public class ClientPerson extends ClientEntity implements IClientPerson
{
    /**
     * Minimal birthdate.
     */
    public static final LocalDate MIN_BIRTHDATE = LocalDate.of(1900, Month.JANUARY, 1);

    /**
     * Person last name.
     */
    @Schema(name = "lastName", description = "Person last name", example = "Eastwood", required = true)
    private String lastName;

    /**
     * Person first name.
     */
    @Schema(name = "firstName", description = "Person first name", example = "Clint", required = true)
    private String firstName;

    /**
     * Person birthdate.
     */
    @Schema(name = "birthDate", description = "Person birth date (YYYY-MM-DD)", example = "1930-05-31", required = true)
    private Date birthDate;

    /**
     * Person gender type.
     */
    @Schema(name = "genderType", description = "Person gender", example = "MALE", required = true)
    //@Enumerated(EnumType.STRING)
    private GenderType genderType;

    /**
     * Person type.
     */
    @Schema(name = "personType", description = "Person type", example = "PHYSICAL", required = true)
    //@Enumerated(EnumType.STRING)
    private PersonType personType;

    /**
     * Set of postal addresses associated to the person.
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Schema(hidden = true)
    private List<ClientPostalAddress> postalAddresses = new ArrayList<>();

    /**
     * Set of phone numbers associated to the person.
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Schema(hidden = true)
    private List<ClientPhoneNumber> phoneNumbers = new ArrayList<>();

    /**
     * Set of email addresses associated to the person.
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Schema(hidden = true)
    private List<ClientEmailAddress> emailAddresses = new ArrayList<>();

    /**
     * Creates a new person.
     */
    public ClientPerson()
    {
        super(EntityType.PERSON);
    }

    /**
     * Sets the person last name.
     * @param lastName Last name.
     */
    public void setLastName(final @NonNull String lastName)
    {
        this.lastName = lastName;
        setName(this.lastName + ", " + this.firstName);
    }

    /**
     * Sets the person first name.
     * @param firstName First name.
     */
    public void setFirstName(final @NonNull String firstName)
    {
        this.firstName = firstName;
        setName(this.lastName + ", " + this.firstName);
    }

    /**
     * Returns the default email address.
     * @return Default email address if one, null otherwise.
     */
    @JsonIgnore
    public final ClientEmailAddress getDefaultEmailAddress()
    {
        Optional<ClientEmailAddress> optional = emailAddresses.stream()
                .filter(ClientEmailAddress::getIsDefaultEmail).findFirst();

        return optional.orElse(null);
    }

    /**
     * Returns if the person has a default email address?
     * @return <b>True</b> if the person has a default email address, <b>false</b> otherwise.
     */
    public final boolean hasDefaultEmailAddress()
    {
        return emailAddresses.stream().anyMatch(ClientEmailAddress::getIsDefaultEmail);
    }

    /**
     * Returns the default postal address.
     * @return Default postal address if one, null otherwise.
     */
    @JsonIgnore
    public final ClientPostalAddress getDefaultPostalAddress()
    {
        Optional<ClientPostalAddress> optional =  postalAddresses.stream()
                .filter(ClientPostalAddress::getIsDefault).findFirst();

        return optional.orElse(null);
    }

    /**
     * Checks if the given email already exist?
     * @param email Email to check.
     * @return {@code True} if it already exist, {@code false} otherwise.
     */
    public final boolean existEmail(final @NonNull String email)
    {
        return emailAddresses.stream()
                .anyMatch(emailAddress -> emailAddress.getEmail().equalsIgnoreCase(email));
    }

    /**
     * Retrieves email addresses matching the given {@link AddressType}.
     * @param type Address type.
     * @return List of email addresses.
     */
    public final List<ClientEmailAddress> findEmailAddressByType(final AddressType type)
    {
        return emailAddresses.stream()
                .filter(emailAddress -> emailAddress.getAddressType() == type)
                .toList();
    }

    /**
     * Retrieves email addresses matching the given {@link EntityStatusType}.
     * @param status Status type.
     * @return List of email addresses.
     */
    public final List<ClientEmailAddress> findEmailAddressByStatus(final EntityStatusType status)
    {
        return emailAddresses.stream()
                .filter(emailAddress -> emailAddress.getStatusType() == status)
                .toList();
    }

    /**
     * Retrieves postal addresses matching the given {@link AddressType}.
     * @param type Address type.
     * @return List of postal addresses.
     */
    public final List<ClientPostalAddress> findPostalAddressByType(final AddressType type)
    {
        return postalAddresses.stream()
                .filter(postalAddress -> postalAddress.getAddressType() == type)
                .toList();
    }

    /**
     * Retrieves postal addresses matching the given {@link EntityStatusType}.
     * @param status Status type.
     * @return List of postal addresses.
     */
    public final List<ClientPostalAddress> findPostalAddressByStatus(final EntityStatusType status)
    {
        return postalAddresses.stream()
                .filter(postalAddress -> postalAddress.getStatusType() == status)
                .toList();
    }
}
