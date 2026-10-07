package com.sajjantawar.order.messaging;
import java.time.Instant;import java.util.UUID;
public record PaymentEvent(UUID eventId,UUID correlationId,UUID orderId,String status,Instant occurredAt){}