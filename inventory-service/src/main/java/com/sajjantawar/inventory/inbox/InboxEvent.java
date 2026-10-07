package com.sajjantawar.inventory.inbox;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
@Entity @Table(name="inbox_events")
public class InboxEvent {
 @Id private UUID eventId;
 @Column(nullable=false) private Instant processedAt;
 protected InboxEvent(){}
 public InboxEvent(UUID eventId){this.eventId=eventId;this.processedAt=Instant.now();}
}