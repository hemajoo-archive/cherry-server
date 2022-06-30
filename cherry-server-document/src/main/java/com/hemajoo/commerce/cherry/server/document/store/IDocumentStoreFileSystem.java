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
import org.springframework.content.fs.store.FilesystemContentStore;
import org.springframework.content.rest.StoreRestResource;

/**
 * Content store repository of type <b>file system</b> for storing data model server entity documents' content.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@StoreRestResource
public interface IDocumentStoreFileSystem extends FilesystemContentStore<ServerDocument, String>
{
    // Empty.
}
