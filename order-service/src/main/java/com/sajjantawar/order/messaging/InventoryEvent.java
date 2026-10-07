package com.sajjantawar.order.messaging;
import java.time.Instant;import java.util.UUID;
public record InventoryEvent(UUID eventId,UUID correlationId,Instant occurredAt,UUID orderId,String sku,int quantity,String result){}