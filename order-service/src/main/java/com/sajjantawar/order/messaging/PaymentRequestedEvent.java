package com.sajjantawar.order.messaging;
import java.math.BigDecimal;import java.util.UUID;
public record PaymentRequestedEvent(UUID eventId,UUID correlationId,UUID orderId,BigDecimal amount,String currency){}