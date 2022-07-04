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
package com.hemajoo.commerce.cherry.server.person.phone;

import com.hemajoo.commerce.cherry.server.commons.entity.query.BaseQueryEntity;
import com.hemajoo.commerce.cherry.server.commons.entity.query.condition.QueryField;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.type.EntityType;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.type.FieldDataType;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.person.phone.type.PhoneNumberCategoryType;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.person.phone.type.PhoneNumberType;

/**
 * Object to issue queries on <b>phone number</b> entities.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public class PhoneNumberQuery extends BaseQueryEntity
{
    /**
     * Field: <b>isDefault</b> of a <b>phone number</b> entity.
     */
    public static final String PHONE_NUMBER_IS_DEFAULT = "isDefault";

    /**
     * Field: <b>number</b> of a <b>phone number</b> entity.
     */
    public static final String PHONE_NUMBER_NUMBER = "number";

    /**
     * Field: <b>countryCode</b> of a <b>phone number</b> entity.
     */
    public static final String PHONE_NUMBER_COUNTRY_CODE = "countryCode";

    /**
     * Field: <b>phoneType</b> of a <b>phone number</b> entity.
     */
    public static final String PHONE_NUMBER_PHONE_TYPE = "phoneType";

    /**
     * Field: <b>categoryType</b> of a <b>phone number</b> entity.
     */
    public static final String PHONE_NUMBER_CATEGORY_TYPE = "categoryType";

    /**
     * Creates a new query object for the <b>phone number</b> entity.
     */
    public PhoneNumberQuery()
    {
        super(EntityType.PHONE_NUMBER);

        fields.add(QueryField.builder()
                .withFieldName(PHONE_NUMBER_CATEGORY_TYPE)
                .withFieldType(FieldDataType.ENUM)
                .withClassType(PhoneNumberCategoryType.class)
                .build());
        fields.add(QueryField.builder()
                .withFieldName(PHONE_NUMBER_COUNTRY_CODE)
                .withFieldType(FieldDataType.STRING)
                .build());
        fields.add(QueryField.builder()
                .withFieldName(PHONE_NUMBER_NUMBER)
                .withFieldType(FieldDataType.STRING)
                .build());
        fields.add(QueryField.builder()
                .withFieldName(PHONE_NUMBER_IS_DEFAULT)
                .withFieldType(FieldDataType.BOOLEAN)
                .build());
        fields.add(QueryField.builder()
                .withFieldName(PHONE_NUMBER_PHONE_TYPE)
                .withFieldType(FieldDataType.ENUM)
                .withClassType(PhoneNumberType.class)
                .build());
    }
}
