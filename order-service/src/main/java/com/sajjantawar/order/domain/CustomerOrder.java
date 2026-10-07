package com.sajjantawar.order.domain;
import jakarta.persistence.*;import java.math.BigDecimal;import java.time.Instant;import java.util.UUID;
@Entity @Table(name="orders")
public class CustomerOrder {
 @Id private UUID id; @Column(nullable=false,length=80) private String customerId; @Column(nullable=false,length=80) private String sku; @Column(nullable=false) private int quantity; @Column(nullable=false,precision=19,scale=2) private BigDecimal totalAmount; @Column(nullable=false,length=3) private String currency; @Enumerated(EnumType.STRING) @Column(nullable=false,length=30) private OrderStatus status; @Column(nullable=false,updatable=false) private Instant createdAt; @Version private long version;
 protected CustomerOrder(){}
 public CustomerOrder(UUID id,String customerId,String sku,int quantity,BigDecimal totalAmount,String currency){this.id=id;this.customerId=customerId;this.sku=sku;this.quantity=quantity;this.totalAmount=totalAmount;this.currency=currency;this.status=OrderStatus.CREATED;this.createdAt=Instant.now();}
 public UUID getId(){return id;} public String getCustomerId(){return customerId;} public String getSku(){return sku;} public int getQuantity(){return quantity;} public BigDecimal getTotalAmount(){return totalAmount;} public String getCurrency(){return currency;} public OrderStatus getStatus(){return status;} public Instant getCreatedAt(){return createdAt;} public long getVersion(){return version;}
 public void moveTo(OrderStatus next){if(next==null)throw new IllegalArgumentException("Order status is required");this.status=next;}
}