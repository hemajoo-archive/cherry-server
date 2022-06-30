/*
 * (C) Copyright Resse Christophe 2021 - All Rights Reserved
 * -----------------------------------------------------------------------------------------------
 * All information contained herein is, and remains the property of
 * Resse Christophe. and its suppliers, if any. The intellectual and technical
 * concepts contained herein are proprietary to Resse C. and its
 * suppliers and may be covered by U.S. and Foreign Patents, patents
 * in process, and are protected by trade secret or copyright law.
 *
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained from
 * Resse Christophe (christophe.resse@gmail.com).
 * -----------------------------------------------------------------------------------------------
 */
package com.hemajoo.commerce.cherry.server.document.test.base;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Provides a JPA (Java Persistence API) auditor information for <b>test</b> schema database.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public class PersistenceTestAuditor implements AuditorAware<String>
{
    @Getter
    @Value("${hemajoo.commerce.cherry.server.jpa.test.auditor.name}")
    private String auditorName = "default";

    @Override
    public Optional<String> getCurrentAuditor()
    {
        return Optional.of(auditorName);
    }
}
