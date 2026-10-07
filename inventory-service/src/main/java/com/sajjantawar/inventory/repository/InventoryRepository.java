package com.sajjantawar.inventory.repository;
import com.sajjantawar.inventory.domain.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface InventoryRepository extends JpaRepository<InventoryItem,UUID>{Optional<InventoryItem> findBySku(String sku);}