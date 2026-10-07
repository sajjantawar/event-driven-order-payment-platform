package com.sajjantawar.order.messaging;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
@Component public class KafkaOrderEventPublisher {
 public static final String ORDER_CREATED="order.created";
 private final KafkaTemplate<String,Object> kafka;
 public KafkaOrderEventPublisher(KafkaTemplate<String,Object> kafka){this.kafka=kafka;}
 public void publish(OrderCreatedEvent event){kafka.send(ORDER_CREATED,event.orderId().toString(),event);}
}