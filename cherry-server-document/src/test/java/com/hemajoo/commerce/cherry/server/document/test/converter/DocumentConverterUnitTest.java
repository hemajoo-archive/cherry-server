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
package com.hemajoo.commerce.cherry.server.document.test.converter;

import com.hemajoo.commerce.cherry.server.commons.entity.comparator.EntityComparator;
import com.hemajoo.commerce.cherry.server.data.model.document.IServerDocument;
import com.hemajoo.commerce.cherry.server.document.converter.DocumentConverter;
import com.hemajoo.commerce.cherry.server.document.converter.IDocumentConverter;
import com.hemajoo.commerce.cherry.server.document.randomizer.DocumentRandomizer;
import com.hemajoo.commerce.cherry.server.document.service.IDocumentService;
import com.hemajoo.commerce.cherry.server.document.test.base.AbstractPostgresUnitTest;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.exception.EntityException;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.identity.EntityIdentity;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.identity.Identity;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.type.EntityType;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.document.IClientDocument;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.document.exception.DocumentException;
import org.javers.core.diff.Diff;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Unit tests for the document converter class.
 * <hr>
 * Unit tests are supposed to be executed with the Maven 'db-test' profile activated.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Testcontainers // Not to be used to keep container alive after the tests!
@DirtiesContext
@SpringBootTest
class DocumentConverterUnitTest extends AbstractPostgresUnitTest
{
    /**
     * Document converter facility service.
     */
    @Autowired
    private IDocumentConverter docConverter;

    /**
     * Document services.
     */
    @Autowired
    private IDocumentService docService;

    /**
     * List element count.
     */
    private final int LIST_COUNT = 10;

    @Test
    @DisplayName("Convert a server document to an identity")
    final void testConvertServerToIdentityDocument() throws DocumentException
    {
        IServerDocument document = DocumentRandomizer.generateServerEntity(true);
        Identity identity = docConverter.fromServerToIdentity(document); //TODO We could simplify but directly extracting the identity from the server document instance such as: document.getIdentity();

        assertThat(identity)
                .as("Entity identity should not be null!")
                .isNotNull();

        assertThat(identity.getId())
                .as("Identifiers are expected to be the same!")
                .isEqualTo(document.getId());

        assertThat(identity.getEntityType())
                .as("Identity is supposed to be of type: DOCUMENT")
                .isEqualTo(EntityType.DOCUMENT);
    }

    @Test
    @DisplayName("Convert an identity to a server document")
    final void testConvertIdentityToServerDocument() throws EntityException
    {
        // For an entity identity to be mapped to a server entity, the server entity must exist in the underlying database!
        IServerDocument reference = docService.save(DocumentRandomizer.generateServerEntity(true));

        Identity identity = new EntityIdentity(reference.getIdentity());
        IServerDocument server = docConverter.fromIdentityToServer(identity);

        assertThat(server)
                .as("Server entity should not be null!")
                .isNotNull();

        assertThat(server.getId())
                .as("Identifiers are expected to be the same!")
                .isEqualTo(identity.getId());

        assertThat(server.getEntityType())
                .as("Identity is supposed to be of type: DOCUMENT")
                .isEqualTo(EntityType.DOCUMENT);
    }

    @Test
    @DisplayName("Convert an identity to a server document (that does not exist)")
    @SuppressWarnings("java:S5977")
    final void testConvertIdentityToServerDocumentNotExisting()
    {
        Identity identity = EntityIdentity.from(EntityType.DOCUMENT, UUID.randomUUID());

        assertThatThrownBy(() -> docConverter.fromIdentityToServer(identity))
                .isInstanceOf(EntityException.class);
    }

    @Test
    @DisplayName("Convert a client document with a owner to a server document")
    final void testConvertClientToServerDocumentWithOwner() throws EntityException
    {
        // For an entity identity to be mapped to a server entity, the server entity must exist in the underlying database!
        IServerDocument server = docService.save(DocumentRandomizer.generateServerEntity(false));

        IClientDocument client = DocumentRandomizer.generateClientEntity(true);
        client.setParent(server.getIdentity());

        IServerDocument other = docConverter.fromClientToServer(client);

        assertThat(other)
                .as("Server document should not be null!")
                .isNotNull();

        assertThat(other.getParent())
                .as("Server document owner should not be null!")
                .isNotNull();
    }

    @Test
    @DisplayName("Convert a client document with a non existing owner to a server document. Should raise an exception!")
    @SuppressWarnings("java:S5977")
    final void testConvertClientToServerDocumentWithNonExistingOwner() throws DocumentException
    {
        IClientDocument client = DocumentRandomizer.generateClientEntity(true);
        client.setParent(new EntityIdentity(EntityType.PERSON,UUID.randomUUID()));

        // If the owner of the client document to convert does not exist, ensure an exception is raised!
        assertThatThrownBy(() -> docConverter.fromClientToServer(client))
                .isInstanceOf(EntityException.class);
    }

    @Test
    @DisplayName("Convert a list of server documents to a list of client documents")
    final void testConvertServerToClientDocumentList() throws DocumentException
    {
        List<IServerDocument> documents = new ArrayList<>();
        for (int i = 0; i < LIST_COUNT; i++)
        {
            documents.add(DocumentRandomizer.generateServerEntity(true));
        }

        List<IClientDocument> clients = documents.stream()
                .map(document -> docConverter.fromServerToClient(document)).toList();

        assertThat(clients.size())
                .as("Document server and client list should have the same size!")
                .isEqualTo(documents.size());

        clients.forEach(client -> assertThat(client)
                .as("Client document should not be null!")
                .isNotNull());
    }

    @Test
    @DisplayName("Convert a list of client documents to a list of server documents")
    final void testConvertClientToServerDocumentList() throws EntityException
    {
        List<IClientDocument> clients = new ArrayList<>();
        for (int i = 0; i < LIST_COUNT; i++)
        {
            clients.add(DocumentRandomizer.generateClientEntity(true));
        }

        List<IServerDocument> servers = new ArrayList<>();
        IServerDocument serverDocumentEntity;
        for (IClientDocument client : clients)
        {
            serverDocumentEntity = docConverter.fromClientToServer(client);
            servers.add(serverDocumentEntity);
        }

        assertThat(servers.size())
                .as("Both lists should have the same size!")
                .isEqualTo(clients.size());

        servers.forEach(document -> assertThat(document)
                .as("Server document should not be null!")
                .isNotNull());
    }

    @Test
    @DisplayName("Convert a list of server documents to a list of entity identities")
    final void testConvertServerDocumentToIdentityList() throws DocumentException
    {
        List<IServerDocument> documents = new ArrayList<>();
        for (int i = 0; i < LIST_COUNT; i++)
        {
            documents.add(DocumentRandomizer.generateServerEntity(true));
        }

        List<Identity> identities = documents.stream()
                .map(document -> docConverter.fromServerToIdentity(document)).toList();

        assertThat(identities.size())
                .as("Both lists should have the same size!")
                .isEqualTo(documents.size());

        identities.forEach(identity -> assertThat(identity)
                .as("Identity should not be null!")
                .isNotNull());
    }

    @Test
    @DisplayName("Copy a server document")
    final void testCopyServerDocument() throws EntityException
    {
        IServerDocument document = DocumentRandomizer.generateServerEntity(true);
        IServerDocument copy = DocumentConverter.copy(document);

        assertThat(document)
                .as("Both server documents should be equal!")
                .isEqualTo(copy);

        Diff diff = EntityComparator.getJavers().compare(document, copy);
        assertThat(diff.getChanges().size())
                .as("Both server documents should be equal!")
                .isZero();
    }

    @Test
    @DisplayName("Copy a client document")
    final void testCopyClientDocument() throws EntityException
    {
        IClientDocument document = DocumentRandomizer.generateClientEntity(true);
        IClientDocument copy = DocumentConverter.copy(document);

        assertThat(document)
                .as("Both client documents should be equal!")
                .isEqualTo(copy);

        Diff diff = EntityComparator.getJavers().compare(document, copy);
        assertThat(diff.getChanges().size())
                .as("Both client documents should be equal!")
                .isZero();
    }
}
