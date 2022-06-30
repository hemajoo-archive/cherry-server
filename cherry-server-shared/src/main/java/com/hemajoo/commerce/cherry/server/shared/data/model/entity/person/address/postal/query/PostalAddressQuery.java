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
package com.hemajoo.commerce.cherry.server.shared.data.model.entity.person.address.postal.query;

import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.query.condition.QueryField;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.type.EntityType;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.person.address.postal.type.PostalAddressCategoryType;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.person.address.type.AddressType;

/**
 * Represents a <b>query</b> object for issuing queries on postal addresses.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public class PostalAddressQuery extends BaseEntityQuery
{
    /**
     * Field: <b>isDefault</b> of a <b>postal address</b> entity.
     */
    public static final String POSTAL_ADDRESS_IS_DEFAULT = "isDefault";

    /**
     * Field: <b>addressType</b> of a <b>postal address</b> entity.
     */
    public static final String POSTAL_ADDRESS_TYPE = "addressType";

    /**
     * Field: <b>categoryType</b> of a <b>postal address</b> entity.
     */
    public static final String POSTAL_ADDRESS_CATEGORY_TYPE = "categoryType";

    /**
     * Field: <b>streetName</b> of a <b>postal address</b> entity.
     */
    public static final String POSTAL_ADDRESS_STREET_NAME = "streetName";

    /**
     * Field: <b>streetNumber</b> of a <b>postal address</b> entity.
     */
    public static final String POSTAL_ADDRESS_STREET_NUMBER = "streetNumber";

    /**
     * Field: <b>locality</b> of a <b>postal address</b> entity.
     */
    public static final String POSTAL_ADDRESS_LOCALITY = "locality";

    /**
     * Field: <b>countryCode</b> of a <b>postal address</b> entity.
     */
    public static final String POSTAL_ADDRESS_COUNTRY_CODE = "countryCode";

    /**
     * Field: <b>zipCode</b> of a <b>postal address</b> entity.
     */
    public static final String POSTAL_ADDRESS_ZIP_CODE = "zipCode";

    /**
     * Field: <b>area</b> of a <b>postal address</b> entity.
     */
    public static final String POSTAL_ADDRESS_AREA = "area";

    /**
     * Creates a new <b>postal address</b> query object.
     */
    public PostalAddressQuery()
    {
        super(EntityType.POSTAL_ADDRESS);

        fields.add(QueryField.builder()
                .withFieldName(POSTAL_ADDRESS_IS_DEFAULT)
                .withFieldType(DataType.BOOLEAN)
                .build());
        fields.add(QueryField.builder()
                .withFieldName(POSTAL_ADDRESS_TYPE)
                .withFieldType(DataType.ENUM)
                .withClassType(AddressType.class)
                .build());
        fields.add(QueryField.builder()
                .withFieldName(POSTAL_ADDRESS_CATEGORY_TYPE)
                .withFieldType(DataType.ENUM)
                .withClassType(PostalAddressCategoryType.class)
                .build());
        fields.add(QueryField.builder()
                .withFieldName(POSTAL_ADDRESS_STREET_NAME)
                .withFieldType(DataType.STRING)
                .build());
        fields.add(QueryField.builder()
                .withFieldName(POSTAL_ADDRESS_STREET_NUMBER)
                .withFieldType(DataType.STRING)
                .build());
        fields.add(QueryField.builder()
                .withFieldName(POSTAL_ADDRESS_LOCALITY)
                .withFieldType(DataType.STRING)
                .build());
        fields.add(QueryField.builder()
                .withFieldName(POSTAL_ADDRESS_COUNTRY_CODE)
                .withFieldType(DataType.STRING)
                .build());
        fields.add(QueryField.builder()
                .withFieldName(POSTAL_ADDRESS_ZIP_CODE)
                .withFieldType(DataType.STRING)
                .build());
        fields.add(QueryField.builder()
                .withFieldName(POSTAL_ADDRESS_AREA)
                .withFieldType(DataType.STRING)
                .build());
    }
}
