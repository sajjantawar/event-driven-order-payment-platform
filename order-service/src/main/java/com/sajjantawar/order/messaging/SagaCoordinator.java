package com.sajjantawar.order.messaging;

import com.sajjantawar.order.domain.OrderStatus;
import com.sajjantawar.order.outbox.OutboxEvent;
import com.sajjantawar.order.outbox.OutboxRepository;
import com.sajjantawar.order.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Component
public class SagaCoordinator {
 private final OrderRepository orders; private final OutboxRepository outbox; private final ObjectMapper mapper;
 public SagaCoordinator(OrderRepository orders,OutboxRepository outbox,ObjectMapper mapper){this.orders=orders;this.outbox=outbox;this.mapper=mapper;}

 @Transactional
 @KafkaListener(topics="inventory.result",groupId="order-saga")
 public void onInventory(InventoryEvent event){
  var order=orders.findById(event.orderId()).orElse(null); if(order==null)return;
  if("RESERVED".equals(event.result())){
   order.moveTo(OrderStatus.PAYMENT_PENDING);
   try{var command=new PaymentRequestedEvent(UUID.randomUUID(),event.correlationId(),order.getId(),order.getTotalAmount(),order.getCurrency());outbox.save(new OutboxEvent(UUID.randomUUID(),order.getId(),"payment.requested",mapper.writeValueAsString(command)));}catch(Exception e){throw new IllegalStateException(e);}
  } else { order.moveTo(OrderStatus.CANCELLED); }
  orders.save(order);
 }

 @Transactional
 @KafkaListener(topics="payment.result",groupId="order-saga")
 public void onPayment(PaymentEvent event){
  var order=orders.findById(event.orderId()).orElse(null); if(order==null)return;
  if("COMPLETED".equals(event.status())) order.moveTo(OrderStatus.CONFIRMED); else order.moveTo(OrderStatus.CANCELLED);
  orders.save(order);
 }
}