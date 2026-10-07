package com.sajjantawar.order.api;
import com.sajjantawar.order.domain.*;
import com.sajjantawar.order.repository.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api/v1/orders")
public class OrderController {
 private final OrderRepository repository;
 public OrderController(OrderRepository repository){this.repository=repository;}
 @PostMapping public ResponseEntity<OrderResponse> create(@Valid @RequestBody CreateOrderRequest request){
  CustomerOrder order=new CustomerOrder(UUID.randomUUID(),request.customerId(),request.totalAmount(),request.currency());
  return ResponseEntity.status(HttpStatus.CREATED).body(OrderResponse.from(repository.save(order)));
 }
 @GetMapping("/{id}") public OrderResponse get(@PathVariable UUID id){return repository.findById(id).map(OrderResponse::from).orElseThrow(()->new NoSuchElementException("Order not found"));}
 @GetMapping("/customer/{customerId}") public List<OrderResponse> byCustomer(@PathVariable String customerId){return repository.findByCustomerIdOrderByCreatedAtDesc(customerId).stream().map(OrderResponse::from).toList();}
}