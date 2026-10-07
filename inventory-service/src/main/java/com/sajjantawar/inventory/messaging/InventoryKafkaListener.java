package com.sajjantawar.inventory.messaging;
import com.sajjantawar.inventory.inbox.*;import com.sajjantawar.inventory.repository.InventoryRepository;import org.springframework.kafka.annotation.KafkaListener;import org.springframework.kafka.core.KafkaTemplate;import org.springframework.stereotype.Component;import org.springframework.transaction.annotation.Transactional;
@Component public class InventoryKafkaListener {
 private final InboxRepository inbox;private final InventoryRepository inventory;private final KafkaTemplate<String,Object> kafka;
 public InventoryKafkaListener(InboxRepository inbox,InventoryRepository inventory,KafkaTemplate<String,Object> kafka){this.inbox=inbox;this.inventory=inventory;this.kafka=kafka;}
 @Transactional @KafkaListener(topics="order.created",groupId="inventory-service")
 public void onOrderCreated(OrderCreatedEvent event){
  if(inbox.existsById(event.eventId()))return;
  var item=inventory.findBySku(event.sku()).orElse(null);String result="REJECTED";
  if(item!=null){try{item.reserve(event.quantity());inventory.save(item);result="RESERVED";}catch(IllegalStateException ignored){}}
  kafka.send("inventory.result",event.orderId().toString(),new InventoryEvent(java.util.UUID.randomUUID(),event.correlationId(),java.time.Instant.now(),event.orderId(),event.sku(),event.quantity(),result));
  inbox.save(new InboxEvent(event.eventId()));
 }
}