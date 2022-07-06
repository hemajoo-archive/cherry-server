# Hemajoo Commerce - Cherry Server

## Status

| Metric     | Status                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
|:-----------|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `CI/CD`    | [![Cherry Server - CI](https://github.com/hemajoo-commerce/cherry-server/actions/workflows/build.yml/badge.svg)](https://github.com/hemajoo-commerce/cherry-server/actions/workflows/build.yml)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
| `Quality`  | [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com.hemajoo.commerce%3Acherry-server&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=com.hemajoo.commerce%3Acherry-server) [![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.hemajoo.commerce%3Acherry-server&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=com.hemajoo.commerce%3Acherry-server) [![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=com.hemajoo.commerce%3Acherry-server&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=com.hemajoo.commerce%3Acherry-server) [![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.hemajoo.commerce%3Acherry-server&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=com.hemajoo.commerce%3Acherry-server) |
| `Issues`   | [![Bugs](https://sonarcloud.io/api/project_badges/measure?project=com.hemajoo.commerce%3Acherry-server&metric=bugs)](https://sonarcloud.io/summary/new_code?id=com.hemajoo.commerce%3Acherry-server) [![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=com.hemajoo.commerce%3Acherry-server&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=com.hemajoo.commerce%3Acherry-server) [![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=com.hemajoo.commerce%3Acherry-server&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=com.hemajoo.commerce%3Acherry-server) [![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=com.hemajoo.commerce%3Acherry-server&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=com.hemajoo.commerce%3Acherry-server)                                               |
| `Metrics`  | [![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=com.hemajoo.commerce%3Acherry-server&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=com.hemajoo.commerce%3Acherry-server) [![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=com.hemajoo.commerce%3Acherry-server&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=com.hemajoo.commerce%3Acherry-server)                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| `Coverage` | [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.hemajoo.commerce%3Acherry-server&metric=coverage)](https://sonarcloud.io/summary/new_code?id=com.hemajoo.commerce%3Acherry-server)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |


## Composition

The **Cherry Server** is a Java Maven multi-modules project providing persistence services and entities to interact with the underlying database and data. It is composed of the following modules:

| Module                       | Description                                                                        |
|------------------------------|:-----------------------------------------------------------------------------------|
| `cherry-server-parent`       | Global configuration of the **Cherry Server** project.                             |
| `cherry-server-commons`      | Component providing common entities for the persistence layer (server side).       |
| `cherry-server-shared`       | Component providing entities to be shared between the server and the client sides. |
| `cherry-server-data-model`   | Component providing the persistence data model entities (server side).             |
| `cherry-server-document`     | Component providing the persistence services specific to the document area.        |
| `cherry-server-rest`         | Component providing REST APIs exposed by the persistence layer.                    |
| `cherry-server-person`       | Component providing the persistence services specific to the person area.          |
| `cherry-server-organization` | Component providing the persistence services specific to the organization area.    |
| `cherry-server-account`      | Component providing the persistence services specific to the account area.         |
| `cherry-server-catalog`      | Component providing the persistence services specific to the catalog area.         |
| `cherry-server-product`      | Component providing the persistence services specific to the product area.         |
| `cherry-server-service`      | Component providing the persistence services specific to the service area.         |
| `cherry-server-calendar`     | Component providing the persistence services specific to the calendar area.        |
| `cherry-server-billing`      | Component providing the persistence services specific to the billing area.         |
| `cherry-server-employee`     | Component providing the persistence services specific to the employee area.        |

## Description

This project is part of the `Hemajoo Commerce - Cherry` solution which is a full stack Java microservices e-commerce application deployed on the cloud.

## Architecture

### See: [Cherry Server - Architecture](./doc/architecture.md)

## Infrastructure

### See: [Cherry Server - Infrastructure](./doc/infrastructure.md)

## DevOps

### See: [Cherry Server - DevOps](./doc/devops.md)

## Release History

### See: [Cherry Server - Release History](./doc/release_history.md)

## Documentation

### See: [Cherry Server - Documentation](./doc/documentation.md)

## Processes

### See: [Cherry Server - Processes](./doc/processes.md)

## Notes

### See: [Cherry Server - Notes](./doc/notes.md)

## Links

### See: [Cherry Server - Links](./doc/links.md)
