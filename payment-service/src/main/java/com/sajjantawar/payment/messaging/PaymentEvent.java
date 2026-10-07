package com.sajjantawar.payment.messaging;
import java.time.Instant;
import java.util.UUID;
public record PaymentEvent(UUID eventId,UUID correlationId,Instant occurredAt,UUID orderId,String status){}