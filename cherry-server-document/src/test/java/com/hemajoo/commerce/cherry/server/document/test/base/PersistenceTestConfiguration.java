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

import com.hemajoo.commerce.cherry.server.data.model.document.ServerDocument;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.document.exception.ContentStoreException;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.content.fs.config.EnableFilesystemStores;
import org.springframework.content.fs.config.FilesystemStoreConfigurer;
import org.springframework.content.fs.io.FileSystemResourceLoader;
import org.springframework.content.s3.S3ObjectId;
import org.springframework.content.s3.config.EnableS3Stores;
import org.springframework.content.s3.config.S3StoreConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * A {@code Spring} configuration containing definitions for the persistence layer for the {@code test} environment.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Configuration
@ComponentScan(basePackages = "com.hemajoo.commerce.cherry.server")
@EnableJpaRepositories(basePackages = "com.hemajoo.commerce.cherry.server")
@EntityScan(basePackages = "com.hemajoo.commerce.cherry.server")
@EnableFilesystemStores(basePackages = "com.hemajoo.commerce.cherry.server")
@EnableS3Stores(basePackages = "com.hemajoo.commerce.cherry.server")
@EnableJpaAuditing(auditorAwareRef = "auditorProvider", dateTimeProviderRef = "auditingDateTimeProvider")
public class PersistenceTestConfiguration
{
    @Getter
    @Value("${hemajoo.commerce.cherry.server.store.location}")
    private String baseContentStoreLocation;

    /**
     * Amazon S3 client when using a S3 document store.
     * @return {@link S3Client}.
     */
    @Bean
    public S3Client client()
    {
        Region region = Region.EU_WEST_3;

        return S3Client.builder()
                .region(region)
                .build();
    }

    /**
     * File system content store configurator.
     * @return {@link FilesystemStoreConfigurer}.
     */
    @Bean
    @ConditionalOnProperty(prefix = "spring.content.storage", name = "type", havingValue = "filesystem")
    public FilesystemStoreConfigurer configureFileSystemContent()
    {
        return registry -> registry.addConverter(new Converter<ServerDocument, String>()
        {
            @Override
            public String convert(final @NonNull ServerDocument document)
            {
                return File.separator + document.getContentId();
            }
        });
    }

    /**
     * Amazon S3 content store configurator.
     * @return {@link S3Client}.
     */
    @Bean
    @ConditionalOnProperty(prefix = "spring.content.storage", name = "type", havingValue = "s3")
    public S3StoreConfigurer configureS3Content()
    {
        return registry -> registry.addConverter(new Converter<ServerDocument, S3ObjectId>()
        {
            @Override
            public S3ObjectId convert(ServerDocument source)
            {
                return new S3ObjectId("hemajoo.commerce.cherry", "dev/internal/" + source.getContentId());
                //                        return new S3ObjectId(entity.getCustomBucketField(), entity.getCustomContentIdField());
            }
        });
    }

    /**
     * Provides the auditor implementation when saving an entity in the backend.
     * @return {@link AuditorAware}.
     */
    @Bean(name = "auditorProvider")
    public AuditorAware<String> auditorProvider()
    {
        return new PersistenceTestAuditor();
    }

    /**
     * Provides the date time implementation when saving an entity in the backend (for creation and modification properties).
     * @return {@link DateTimeProvider}.
     */
    @Bean(name = "auditingDateTimeProvider")
    public DateTimeProvider dateTimeProvider()
    {
        return () -> Optional.of(LocalDateTime.now());
    }

    /**
     * File system root path to use for storing documents.
     * @return File system root path.
     * @throws ContentStoreException Thrown to indicate an error occurred when trying to retrieve the filesystem root path.
     */
    @Bean
    public File fileSystemRoot() throws ContentStoreException
    {
        if (baseContentStoreLocation == null || baseContentStoreLocation.isEmpty())
        {
            throw new ContentStoreException("Property: 'hemajoo.commerce.cherry.store.location' cannot be null or empty!");
        }

        // Clear the content store for a test environment
        try
        {
            Arrays.stream(
                    Objects.requireNonNull(
                            new File(baseContentStoreLocation).listFiles())).forEach(File::delete);
        }
        catch (Exception e)
        {
            // Directory does not exist, do nothing!
        }

        return new File(baseContentStoreLocation);
    }

    /**
     * Returns the content store file system resource loader.
     * @return File system resource loader.
     * @throws ContentStoreException Thrown to indicate an error occurred when trying to access file system resource loader.
     */
    @Bean
    FileSystemResourceLoader fileSystemResourceLoader() throws ContentStoreException
    {
        // For file system storage.
        return new FileSystemResourceLoader(fileSystemRoot().getAbsolutePath());
    }
}
