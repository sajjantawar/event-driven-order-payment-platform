package com.sajjantawar.payment.repository;
import com.sajjantawar.payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface PaymentRepository extends JpaRepository<Payment,UUID>{Optional<Payment> findByIdempotencyKey(String key);}