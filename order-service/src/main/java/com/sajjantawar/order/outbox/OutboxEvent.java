package com.sajjantawar.order.outbox;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
@Entity @Table(name="outbox_events")
public class OutboxEvent {
 @Id private UUID id;
 @Column(nullable=false) private UUID aggregateId;
 @Column(nullable=false,length=100) private String eventType;
 @Column(nullable=false,columnDefinition="TEXT") private String payload;
 @Column(nullable=false) private boolean published;
 @Column(nullable=false,updatable=false) private Instant createdAt;
 protected OutboxEvent(){}
 public OutboxEvent(UUID id,UUID aggregateId,String eventType,String payload){this.id=id;this.aggregateId=aggregateId;this.eventType=eventType;this.payload=payload;this.published=false;this.createdAt=Instant.now();}
 public UUID getId(){return id;} public UUID getAggregateId(){return aggregateId;} public String getEventType(){return eventType;} public String getPayload(){return payload;} public boolean isPublished(){return published;} public Instant getCreatedAt(){return createdAt;} public void markPublished(){published=true;}
}