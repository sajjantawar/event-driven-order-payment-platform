# Architecture

## Services

### API Gateway
Single entry point for the Angular client. Responsible for routing and cross-cutting request concerns.

### Order Service
Owns order lifecycle and order state transitions.

### Inventory Service
Owns product stock and reservation lifecycle. Uses optimistic locking to prevent overselling.

### Payment Service
Owns payment lifecycle and idempotent payment processing.

### Notification Service
Consumes business events and represents asynchronous customer notifications.

## Event-driven flow

1. Client submits an order.
2. Order Service persists the order.
3. Order Service publishes an order-created event through an outbox.
4. Inventory Service reserves stock.
5. Inventory Service emits inventory-reserved or inventory-rejected.
6. Payment Service processes payment.
7. Payment Service emits payment-completed or payment-failed.
8. Order Service confirms or compensates the order.

## Consistency

The platform deliberately uses eventual consistency between service-owned databases. Local transactions guarantee consistency inside each service; Kafka events propagate state changes across service boundaries.

## Reliability patterns

- Idempotency keys for commands.
- Transactional outbox for reliable event publication.
- Inbox table for duplicate-event suppression.
- Optimistic locking for inventory.
- Explicit state transitions.
- Retry and dead-letter handling.
