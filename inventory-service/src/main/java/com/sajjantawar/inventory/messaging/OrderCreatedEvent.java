package com.sajjantawar.inventory.messaging;
import java.math.BigDecimal;import java.time.Instant;import java.util.UUID;
public record OrderCreatedEvent(UUID eventId,UUID correlationId,Instant occurredAt,UUID orderId,String customerId,String sku,int quantity,BigDecimal totalAmount,String currency){}