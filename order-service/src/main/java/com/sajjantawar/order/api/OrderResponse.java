package com.sajjantawar.order.api;
import com.sajjantawar.order.domain.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
public record OrderResponse(UUID id,String customerId,String sku,int quantity,BigDecimal totalAmount,String currency,OrderStatus status,Instant createdAt,long version) {
 public static OrderResponse from(CustomerOrder o){return new OrderResponse(o.getId(),o.getCustomerId(),o.getSku(),o.getQuantity(),o.getTotalAmount(),o.getCurrency(),o.getStatus(),o.getCreatedAt(),o.getVersion());}
}