package com.sajjantawar.inventory.messaging;
import com.sajjantawar.inventory.inbox.*;
import com.sajjantawar.inventory.repository.InventoryRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component public class InventoryKafkaListener {
 private final InboxRepository inbox; private final InventoryRepository inventory;
 public InventoryKafkaListener(InboxRepository inbox,InventoryRepository inventory){this.inbox=inbox;this.inventory=inventory;}
 @Transactional
 @KafkaListener(topics="order.created",groupId="inventory-service")
 public void onOrderCreated(OrderCreatedEvent event){
  if(inbox.existsById(event.eventId())) return;
  // Demo policy: reserve one unit of a deterministic SKU for every order.
  inventory.findBySku("SKU-LAPTOP-001").ifPresent(item->{try{item.reserve(1);inventory.save(item);}catch(IllegalStateException ignored){}}); 
  inbox.save(new InboxEvent(event.eventId()));
 }
}