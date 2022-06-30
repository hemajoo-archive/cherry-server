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
package com.hemajoo.commerce.cherry.server.shared.data.model.entity.person.address.email.query;

import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.query.BaseQueryEntity;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.query.condition.QueryField;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.type.EntityType;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.person.address.type.AddressType;

/**
 * Represents a query object for issuing queries on email address data model entity.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public final class EmailAddressQuery extends BaseQueryEntity
{
    /**
     * Field: <b>email</b> of an <b>email address</b> entity.
     */
    public static final String EMAIL_ADDRESS_EMAIL = "email";

    /**
     * Field: <b>isDefault</b> of an <b>email address</b> entity.
     */
    public static final String EMAIL_ADDRESS_IS_DEFAULT = "isDefault";

    /**
     * Field: <b>addressType</b> of an <b>email address</b> entity.
     */
    public static final String EMAIL_ADDRESS_TYPE = "addressType";

    /**
     * Creates a new <b>email address</b> query object.
     */
    public EmailAddressQuery()
    {
        super(EntityType.EMAIL_ADDRESS);

        fields.add(QueryField.builder()
                .withFieldName(EMAIL_ADDRESS_EMAIL)
                .withFieldType(DataType.STRING)
                .build());
        fields.add(QueryField.builder()
                .withFieldName(EMAIL_ADDRESS_IS_DEFAULT)
                .withFieldType(DataType.BOOLEAN)
                .build());
        fields.add(QueryField.builder()
                .withFieldName(EMAIL_ADDRESS_TYPE)
                .withFieldType(DataType.ENUM)
                .withClassType(AddressType.class)
                .build());
    }
}
