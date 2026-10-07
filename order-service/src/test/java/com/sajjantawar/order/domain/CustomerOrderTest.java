package com.sajjantawar.order.domain;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;
class CustomerOrderTest {
 @Test void newOrderStartsInCreatedState(){
  CustomerOrder order=new CustomerOrder(UUID.randomUUID(),"customer-1",new BigDecimal("99.90"),"USD");
  assertThat(order.getStatus()).isEqualTo(OrderStatus.CREATED);
 }
 @Test void orderCanMoveThroughLifecycle(){
  CustomerOrder order=new CustomerOrder(UUID.randomUUID(),"customer-1",BigDecimal.TEN,"USD");
  order.moveTo(OrderStatus.INVENTORY_PENDING);
  order.moveTo(OrderStatus.INVENTORY_RESERVED);
  order.moveTo(OrderStatus.PAYMENT_PENDING);
  assertThat(order.getStatus()).isEqualTo(OrderStatus.PAYMENT_PENDING);
 }
}