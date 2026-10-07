package com.sajjantawar.payment.messaging;

import com.sajjantawar.payment.inbox.InboxEvent;
import com.sajjantawar.payment.inbox.InboxRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentKafkaListener {
    private final InboxRepository inbox;
    public PaymentKafkaListener(InboxRepository inbox) { this.inbox = inbox; }

    @KafkaListener(topics = "payment.requested", groupId = "payment-service")
    public void onPaymentRequested(PaymentRequestedEvent event) {
        if (inbox.existsById(event.eventId())) return;
        inbox.save(new InboxEvent(event.eventId()));
    }
}