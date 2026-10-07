package com.sajjantawar.order.outbox;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sajjantawar.order.messaging.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component public class OutboxPublisher {
 private final OutboxRepository repository; private final KafkaTemplate<String,Object> kafka; private final ObjectMapper mapper;
 public OutboxPublisher(OutboxRepository repository,KafkaTemplate<String,Object> kafka,ObjectMapper mapper){this.repository=repository;this.kafka=kafka;this.mapper=mapper;}
 @Scheduled(fixedDelay=2000) public void publishPending(){
  for(OutboxEvent event:repository.findTop100ByPublishedFalseOrderByCreatedAtAsc()) try{
   if(KafkaOrderEventPublisher.ORDER_CREATED.equals(event.getEventType())){
    var payload=mapper.readValue(event.getPayload(),OrderCreatedEvent.class);
    kafka.send(KafkaOrderEventPublisher.ORDER_CREATED,event.getAggregateId().toString(),payload).get();
   }
   event.markPublished();repository.save(event);
  }catch(Exception ignored){}
 }
}