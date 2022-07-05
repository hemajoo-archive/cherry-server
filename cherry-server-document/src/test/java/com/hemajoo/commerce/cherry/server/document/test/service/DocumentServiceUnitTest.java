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
package com.hemajoo.commerce.cherry.server.document.test.service;

import com.hemajoo.commerce.cherry.server.commons.entity.query.condition.QueryCondition;
import com.hemajoo.commerce.cherry.server.commons.entity.query.condition.QueryConditionException;
import com.hemajoo.commerce.cherry.server.data.model.document.ServerDocument;
import com.hemajoo.commerce.cherry.server.document.query.DocumentQuery;
import com.hemajoo.commerce.cherry.server.document.randomizer.DocumentRandomizer;
import com.hemajoo.commerce.cherry.server.document.service.IDocumentService;
import com.hemajoo.commerce.cherry.server.document.test.base.AbstractPostgresUnitTest;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.type.EntityStatusType;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.base.type.QueryOperatorType;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.document.exception.DocumentException;
import com.hemajoo.commerce.cherry.server.shared.data.model.entity.document.type.DocumentType;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Date;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the <b>document</b> service class.
 * <br>
 * The <b>persistence</b> module unit tests are all executed against a <b>PostgresSQL</b> database contained in a <b>Docker container</b>, so the database instance is
 * container in the <b>Docker image</b>.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@DirtiesContext
@Testcontainers // Not to be used to keep container alive after the tests!
@SpringBootTest
@Log4j2
class DocumentServiceUnitTest extends AbstractPostgresUnitTest
{
    /**
     * Date time formatter.
     */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS z");

    /**
     * Document service.
     */
    @Autowired
    private IDocumentService documentService;

    /**
     * Prepare before each test.
     * @throws DocumentException Thrown to indicate an error occurred when trying to randomly generate test documents.
     */
    @BeforeEach
    public void beforeEach() throws DocumentException
    {
        // Not using a @TestInstance(TestInstance.Lifecycle.PER_CLASS) and an associated beforeAll / afterAll because of a bug with the Spring context!
        ServerDocument document;

        // Generate 100 test documents
        for (int i = 0; i < 100; i++)
        {
            document = documentService.save(DocumentRandomizer.generateServerEntity(false));
        }
    }

    /**
     * Cleanup after each test.
     * @throws DocumentException Thrown to indicate an error occurred when trying to randomly generate test documents.
     */
    @AfterEach
    public void afterEach() throws DocumentException
    {
        for (ServerDocument document : documentService.findAll())
        {
            try
            {
                documentService.deleteById(document.getId());
            }
            catch (EmptyResultDataAccessException e)
            {
                // Do nothing!
            }
        }
    }

    @Test
    @DisplayName("Create a document")
    void testCreateDocument() throws DocumentException
    {
        ServerDocument document = documentService.save(DocumentRandomizer.generateServerEntity(false));

        assertThat(document)
                .as("Document should not be null!")
                .isNotNull();

        assertThat(document.getId())
                .as("Document identifier should not be null!")
                .isNotNull();
    }

    @Test
    @DisplayName("Ensure we cannot set a document as being the parent of another document")
    void testSetDocumentAsParentOfDocument() throws DocumentException
    {
        ServerDocument parent = documentService.save(DocumentRandomizer.generateServerEntity(false));
        ServerDocument child = documentService.save(DocumentRandomizer.generateServerEntity(false));

        assertThat(parent)
                .as("Parent document should not be null!")
                .isNotNull();
        assertThat(parent.getId())
                .as("Parent document identifier should not be null!")
                .isNotNull();
        assertThat(child)
                .as("Child document should not be null!")
                .isNotNull();
        assertThat(child.getId())
                .as("Child document identifier should not be null!")
                .isNotNull();

        assertThrows(DocumentException.class, () -> {
            parent.addDocument(child);
        });
    }

    @Test
    @DisplayName("Update a document")
    void testUpdateDocument() throws DocumentException
    {
        ServerDocument document = documentService.save(DocumentRandomizer.generateServerEntity(false));

        assertThat(document)
                .as("Document should not be null!")
                .isNotNull();

        assertThat(document.getId())
                .as("Document identifier should not be null!")
                .isNotNull();

        String description = document.getDescription();
        document.setDescription("Test description for document: " + document.getId());
        documentService.saveAndFlush(document);

        ServerDocument updated = documentService.findById(document.getId());

        assertThat(updated)
                .as("Document should not be null!")
                .isNotNull();

        assertThat(updated.getDescription())
                .as("Documents description should be different!")
                .isNotEqualTo(description);
    }

    @Test
    @DisplayName("Delete a document")
    void testDeleteDocument() throws DocumentException
    {
        ServerDocument document = documentService.save(DocumentRandomizer.generateServerEntity(false));

        assertThat(document)
                .as("Document should not be null!")
                .isNotNull();

        assertThat(document.getId())
                .as("Document identifier should not be null!")
                .isNotNull();

        documentService.deleteById(document.getId());

        assertThat(documentService.findById(document.getId()))
                .as("Document should be null!")
                .isNull();
    }

    @Disabled
    @Test
    @DisplayName("Create an invalid search criteria")
    void testCreateInvalidSearchCriteria() //TODO Review this unit test to create an invalid search criteria!
    {
        try
        {
            DocumentQuery.create()
                    .addCondition(QueryCondition.builder()
                    .addField(DocumentQuery.DOCUMENT_CONTENT_PATH)
                    .addValue("john.doe@gmail.com")
                    .addOperator(QueryOperatorType.EQUAL)
                    .build());
            Assertions.fail(String.format("Should have raised an: '%s'", QueryConditionException.class));
        }
        catch (QueryConditionException e)
        {
            // Do nothing!
        }
    }

    @Test
    @DisplayName("Query documents by their extension equal to")
    void testQueryDocumentByExtensionEqualTo() throws QueryConditionException
    {
        final String DOCUMENT_EXTENSION = "jpg";

        DocumentQuery query = DocumentQuery.create()
                .addCondition(QueryCondition.builder()
                        .addField(DocumentQuery.DOCUMENT_EXTENSION)
                        .addOperator(QueryOperatorType.EQUAL)
                        .addValue(DOCUMENT_EXTENSION)
                        .build())
                .addCondition(QueryCondition.builder()
                        .addField(DocumentQuery.DOCUMENT_CONTENT_LENGTH)
                        .addOperator(QueryOperatorType.BETWEEN)
                        .addValue(0L)
                        .addValue(50000L)
                        .build());

        List<ServerDocument> documents = documentService.search(query);
        assertThat(documents)
                .as("Document list should not be empty!")
                .isNotEmpty();

        for (ServerDocument document : documents)
        {
            assertThat(document.getExtension())
                    .as(String.format("Document extension should be: '%s'", DOCUMENT_EXTENSION))
                    .isEqualTo(DOCUMENT_EXTENSION);
            assertThat(document.getContentId())
                    .as("Document content id should not be null!")
                    .isNotNull();
            assertThat(document.getContent())
                    .as("Document content should not be null!")
                    .isNotNull();
        }
    }

    @Test
    @DisplayName("Query documents by mime type equal to: 'text/plain' and document type equal to: 'DOCUMENT_GENERIC'")
    void testQueryDocumentByMimeTypeEqualTo() throws QueryConditionException
    {
        final String DOCUMENT_MIMETYPE = "text/plain";
        final DocumentType DOCUMENT_TYPE = DocumentType.DOCUMENT_GENERIC;

        DocumentQuery query = DocumentQuery.create()
                .addCondition(QueryCondition.builder()
                        .addField(DocumentQuery.DOCUMENT_MIMETYPE)
                        .addOperator(QueryOperatorType.EQUAL)
                        .addValue(DOCUMENT_MIMETYPE)
                        .build())
                .addCondition(QueryCondition.builder()
                        .addField(DocumentQuery.DOCUMENT_TYPE)
                        .addOperator(QueryOperatorType.EQUAL)
                        .addValue(DOCUMENT_TYPE)
                        .build());

        List<ServerDocument> documents = documentService.search(query);
        assertThat(documents)
                .as("Document list should not be empty!")
                .isNotEmpty();

        for (ServerDocument document : documents)
        {
            assertThat(document.getMimeType())
                    .as(String.format("Document mime type should be: '%s'", DOCUMENT_MIMETYPE))
                    .isEqualTo(DOCUMENT_MIMETYPE);
            assertThat(document.getContentId())
                    .as("Document content id should not be null!")
                    .isNotNull();
            assertThat(document.getContent())
                    .as("Document content should not be null!")
                    .isNotNull();
        }
    }

    @Test
    @DisplayName("Query documents by inactive date between")
    void testQueryDocumentByInactiveDateBetween() throws QueryConditionException
    {
        final ZonedDateTime DOCUMENT_DATE_LOW = ZonedDateTime.parse("2000-08-21 00:00:00.001 Europe/Paris", formatter);
        final Instant DOCUMENT_DATE_HIGH = Instant.now();

        DocumentQuery query = DocumentQuery.create()
                .addCondition(QueryCondition.builder()
                        .addField(DocumentQuery.BASE_SINCE)
                        .addOperator(QueryOperatorType.BETWEEN)
                        .addValue(Date.from(DOCUMENT_DATE_LOW.toInstant()))
                        .addValue(Date.from(DOCUMENT_DATE_HIGH))
                        .build());

        List<ServerDocument> documents = documentService.search(query);
        assertThat(documents)
                .as("Document list should not be empty!")
                .isNotEmpty();

        for (ServerDocument document : documents)
        {
            assertThat(document.getStatusType())
                    .as(String.format("Document status type should be: '%s'", EntityStatusType.INACTIVE))
                    .isEqualTo(EntityStatusType.INACTIVE);
            assertThat(document.getSince())
                    .as(String.format("Document inactive (since) date should be greater or equal to: '%s' and less or equal to: '%s'", DOCUMENT_DATE_LOW, DOCUMENT_DATE_HIGH))
                    .isBetween(Date.from(DOCUMENT_DATE_LOW.toInstant()), Date.from(DOCUMENT_DATE_HIGH));
        }
    }

    @Test
    @DisplayName("Query documents by filename matching")
    void testQueryDocumentByFilenameMatching() throws QueryConditionException
    {
        final String FILENAME_PATTERN = "license";

        DocumentQuery query = DocumentQuery.create()
                .addCondition(QueryCondition.builder()
                        .addField(DocumentQuery.DOCUMENT_FILENAME)
                        .addOperator(QueryOperatorType.CONTAINS)
                        .addValue(FILENAME_PATTERN)
                        .build());

        List<ServerDocument> documents = documentService.search(query);
        assertThat(documents)
                .as("Document list should not be empty!")
                .isNotEmpty();

        for (ServerDocument document : documents)
        {
            assertThat(document.getFilename())
                    .as(String.format("Document filename: '%s' should contain: '%s'", document.getFilename(), FILENAME_PATTERN))
                    .contains(FILENAME_PATTERN);
        }
    }

    @Test
    @DisplayName("Query documents by content length between")
    void testQueryDocumentByContentLengthBetween() throws QueryConditionException
    {
        final Long CONTENT_LENGTH_LOW = 34000L;
        final Long CONTENT_LENGTH_HIGH = 35000L;

        DocumentQuery query = DocumentQuery.create()
                .addCondition(QueryCondition.builder()
                        .addField(DocumentQuery.DOCUMENT_CONTENT_LENGTH)
                        .addOperator(QueryOperatorType.BETWEEN)
                        .addValue(CONTENT_LENGTH_LOW)
                        .addValue(CONTENT_LENGTH_HIGH)
                        .build());

        List<ServerDocument> documents = documentService.search(query);
        assertThat(documents)
                .as("Document list should not be empty!")
                .isNotEmpty();

        for (ServerDocument document : documents)
        {
            assertThat(document.getContentLength())
                    .as(String.format("Document content length: '%s' should be greater or equal to: '%s' and less or equal to: '%s'", document.getContentLength(), CONTENT_LENGTH_LOW, CONTENT_LENGTH_HIGH))
                    .isBetween(CONTENT_LENGTH_LOW, CONTENT_LENGTH_HIGH);
        }
    }

    @Test
    @DisplayName("Query documents by content length greater than or equal to")
    void testQueryDocumentByContentLengthGreaterThanOrEqual() throws QueryConditionException
    {
        final Long CONTENT_LENGTH_LOW = 35000L;

        DocumentQuery query = DocumentQuery.create()
                .addCondition(QueryCondition.builder()
                        .addField(DocumentQuery.DOCUMENT_CONTENT_LENGTH)
                        .addOperator(QueryOperatorType.GREATER_THAN_EQUAL)
                        .addValue(CONTENT_LENGTH_LOW)
                        .build());

        List<ServerDocument> documents = documentService.search(query);
        assertThat(documents)
                .as("Document list should not be empty!")
                .isNotEmpty();

        for (ServerDocument document : documents)
        {
            assertThat(document.getContentLength())
                    .as(String.format("Document content length: '%s' should be greater or equal to: '%s'", document.getContentLength(), CONTENT_LENGTH_LOW))
                    .isGreaterThanOrEqualTo(CONTENT_LENGTH_LOW);
        }
    }

    @Test
    @DisplayName("Query documents by their document type equal to")
    void testQueryDocumentByDocumentTypeEqualTo() throws QueryConditionException
    {
        final DocumentType DOCUMENT_TYPE = DocumentType.DOCUMENT_INVOICE;

        DocumentQuery query = DocumentQuery.create()
                .addCondition(QueryCondition.builder()
                        .addField(DocumentQuery.DOCUMENT_TYPE)
                        .addOperator(QueryOperatorType.EQUAL)
                        .addValue(DOCUMENT_TYPE)
                        .build());

        List<ServerDocument> documents = documentService.search(query);
        assertThat(documents)
                .as("Document list should not be empty!")
                .isNotEmpty();

        for (ServerDocument document : documents)
        {
            assertThat(document.getDocumentType())
                    .as(String.format("Document type expected: '%s'!", document.getDocumentType()))
                    .isEqualTo(DOCUMENT_TYPE);
        }
    }

    @Test
    @DisplayName("Query documents by their status type equal to")
    void testQueryDocumentByStatusTypeEqualTo() throws QueryConditionException
    {
        final EntityStatusType DOCUMENT_STATUS = EntityStatusType.INACTIVE;

        DocumentQuery query = DocumentQuery.create()
                .addCondition(QueryCondition.builder()
                        .addField(DocumentQuery.BASE_STATUS_TYPE)
                        .addOperator(QueryOperatorType.EQUAL)
                        .addValue(DOCUMENT_STATUS)
                        .build());

        List<ServerDocument> documents = documentService.search(query);
        assertThat(documents)
                .as("Document list should not be empty!")
                .isNotEmpty();

        for (ServerDocument document : documents)
        {
            assertThat(document.getStatusType())
                    .as(String.format("Document status type expected: '%s'!", document.getStatusType()))
                    .isEqualTo(DOCUMENT_STATUS);
        }
    }
}
