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

import com.hemajoo.commerce.cherry.backend.persistence.base.entity.IServerEntity;
import com.hemajoo.commerce.cherry.backend.shared.person.address.postal.IPostalAddress;

/**
 * Behavior of a server postal address entity.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IPostalAddressServer extends IPostalAddress, IServerEntity
{
//    /**
//     * Returns the person owning this email address.
//     * @return Person.
//     */
//    ServerPerson getPerson();
//
//    /**
//     * Sets the person owning this email address.
//     * @param owner Person.
//     */
//    void setPerson(final ServerPerson owner);
}
