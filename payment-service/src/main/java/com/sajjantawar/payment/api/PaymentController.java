package com.sajjantawar.payment.api;
import com.sajjantawar.payment.domain.*;
import com.sajjantawar.payment.repository.PaymentRepository;
import jakarta.validation.constraints.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.*;
@RestController @RequestMapping("/api/v1/payments")
public class PaymentController {
 private final PaymentRepository repository;
 public PaymentController(PaymentRepository repository){this.repository=repository;}
 @PostMapping
 public ResponseEntity<Payment> pay(@RequestHeader("Idempotency-Key") String key,@RequestParam UUID orderId,@RequestParam @DecimalMin("0.01") BigDecimal amount,@RequestParam @Pattern(regexp="[A-Z]{3}") String currency){
  if(key.isBlank())throw new IllegalArgumentException("Idempotency-Key is required");
  var existing=repository.findByIdempotencyKey(key);if(existing.isPresent())return ResponseEntity.ok(existing.get());
  Payment payment=new Payment(UUID.randomUUID(),orderId,amount,currency,key);payment.process();return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(payment));
 }
}