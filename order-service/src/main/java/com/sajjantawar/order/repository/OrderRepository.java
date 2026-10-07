package com.sajjantawar.order.repository;
import com.sajjantawar.order.domain.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface OrderRepository extends JpaRepository<CustomerOrder,UUID>{List<CustomerOrder> findByCustomerIdOrderByCreatedAtDesc(String customerId);}