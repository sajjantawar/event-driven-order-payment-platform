# Event Flow

## Successful order

`OrderCreated -> InventoryReserved -> PaymentCompleted -> OrderConfirmed`

## Compensation

`OrderCreated -> InventoryRejected -> OrderCancelled`

or

`OrderCreated -> InventoryReserved -> PaymentFailed -> InventoryReleased -> OrderCancelled`

Each event carries an event ID and correlation ID so a distributed transaction can be traced end-to-end.
