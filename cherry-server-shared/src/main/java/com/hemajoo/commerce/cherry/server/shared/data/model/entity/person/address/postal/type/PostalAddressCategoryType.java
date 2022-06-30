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
package com.hemajoo.commerce.cherry.server.shared.data.model.entity.person.address.postal.type;

/**
 * Enumeration representing the several possible postal address category types.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public enum PostalAddressCategoryType
{
    /**
     * <b>Default</b> postal address.
     */
    DEFAULT,

    /**
     * <b>Billing</b> postal address.
     */
    BILLING,

    /**
     * <b>Delivery</b> postal address.
     */
    DELIVERY,

    /**
     * <b>Other</b> postal address.
     */
    OTHER
}
