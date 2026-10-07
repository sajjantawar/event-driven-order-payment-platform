# Event-Driven Order & Payment Platform

Enterprise-style Java 21 / Spring Boot microservices platform demonstrating event-driven architecture, Kafka messaging, Saga-style order orchestration, idempotency, retries, inventory reservation, payment processing, auditability, and Angular 19.

## Architecture

```
Angular 19
    |
API Gateway
    |
    +--> Order Service ------+
    |                        |
    +--> Payment Service     +--> Kafka
    |                        |
    +--> Inventory Service --+
    |
Notification Service
```

## Core workflow

```
ORDER_CREATED
      |
INVENTORY_RESERVATION_REQUESTED
      |
INVENTORY_RESERVED
      |
PAYMENT_REQUESTED
      |
PAYMENT_COMPLETED
      |
ORDER_CONFIRMED
```

Failure paths compensate the workflow by cancelling the order and releasing inventory.

## Planned capabilities

- Java 21 and Spring Boot 3.5
- Angular 19
- Kafka event-driven communication
- Saga-style orchestration
- Transactional outbox
- Inbox/idempotent consumers
- Optimistic locking
- Payment and inventory state machines
- JWT/RBAC
- PostgreSQL + Flyway
- JUnit 5, Mockito and Testcontainers
- Playwright E2E tests
- Docker Compose
- GitHub Actions
- OpenAPI/Swagger
- Correlation IDs and audit trail

Implementation is being built incrementally with production-style verification.
