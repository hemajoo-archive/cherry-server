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
package com.hemajoo.commerce.cherry.server.document.store;

import com.hemajoo.commerce.cherry.server.data.model.document.ServerDocument;
import org.springframework.content.rest.StoreRestResource;
import org.springframework.content.s3.store.S3ContentStore;

/**
 * Content store repository of type <b>Amazon S3</b> for storing data model server entity documents' content.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@StoreRestResource
public interface IDocumentStoreS3 extends S3ContentStore<ServerDocument, String>
{
    // Empty.
}
