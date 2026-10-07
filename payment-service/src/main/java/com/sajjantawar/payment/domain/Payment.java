package com.sajjantawar.payment.domain;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
@Entity @Table(name="payments",uniqueConstraints=@UniqueConstraint(name="uk_payment_idempotency",columnNames="idempotency_key"))
public class Payment {
 @Id private UUID id;
 @Column(nullable=false) private UUID orderId;
 @Column(nullable=false,precision=19,scale=2) private BigDecimal amount;
 @Column(nullable=false,length=3) private String currency;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private PaymentStatus status;
 @Column(name="idempotency_key",nullable=false,length=120) private String idempotencyKey;
 @Column(nullable=false,updatable=false) private Instant createdAt;
 protected Payment(){}
 public Payment(UUID id,UUID orderId,BigDecimal amount,String currency,String idempotencyKey){this.id=id;this.orderId=orderId;this.amount=amount;this.currency=currency;this.idempotencyKey=idempotencyKey;this.status=PaymentStatus.PENDING;this.createdAt=Instant.now();}
 public UUID getId(){return id;} public UUID getOrderId(){return orderId;} public BigDecimal getAmount(){return amount;} public String getCurrency(){return currency;} public PaymentStatus getStatus(){return status;} public String getIdempotencyKey(){return idempotencyKey;} public Instant getCreatedAt(){return createdAt;}
 public void process(){if(status!=PaymentStatus.PENDING)throw new IllegalStateException("Payment cannot be processed from "+status);status=PaymentStatus.PROCESSING;status=PaymentStatus.COMPLETED;}
 public void fail(){if(status!=PaymentStatus.PROCESSING)throw new IllegalStateException("Payment is not processing");status=PaymentStatus.FAILED;}
}