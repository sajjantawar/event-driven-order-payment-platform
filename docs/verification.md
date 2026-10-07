# Verification Strategy

The project is designed to be verified at four levels.

## Unit tests
Domain state transitions and inventory/payment invariants are tested with JUnit 5.

## Integration tests
Testcontainers PostgreSQL tests will verify Flyway migrations, persistence and service boundaries.

## Event tests
Kafka integration tests will verify:
- event publication
- consumer processing
- inbox deduplication
- Saga transitions
- compensation paths

## End-to-end
Playwright will drive the Angular application through the API gateway and verify a complete successful order and a failed/compensated order.

## Reliability scenarios
The test suite should explicitly cover:
- duplicate idempotency key
- duplicate Kafka delivery
- insufficient inventory
- payment failure
- retry after consumer failure
- optimistic-lock conflict
- outbox publication after database commit

A green CI run is the authoritative proof that the configured checks pass.
