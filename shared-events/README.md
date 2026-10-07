# Shared Event Contracts

The platform uses versioned event contracts between services.

Current event names:
- OrderCreated
- InventoryReserved
- InventoryRejected
- PaymentRequested
- PaymentCompleted
- PaymentFailed
- InventoryReleaseRequested

Every event carries:
- eventId
- correlationId
- occurredAt
- aggregateId
- eventType
- payload
