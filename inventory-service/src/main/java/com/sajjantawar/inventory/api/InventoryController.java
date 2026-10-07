package com.sajjantawar.inventory.api;
import com.sajjantawar.inventory.domain.InventoryItem;
import com.sajjantawar.inventory.repository.InventoryRepository;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api/v1/inventory")
public class InventoryController {
 private final InventoryRepository repository;
 public InventoryController(InventoryRepository repository){this.repository=repository;}
 @GetMapping("/{sku}") public InventoryItem get(@PathVariable String sku){return repository.findBySku(sku).orElseThrow(()->new NoSuchElementException("SKU not found"));}
 @PostMapping("/{sku}/reserve") public InventoryItem reserve(@PathVariable String sku,@RequestParam int quantity){
  InventoryItem item=repository.findBySku(sku).orElseThrow(()->new NoSuchElementException("SKU not found"));item.reserve(quantity);return repository.save(item);
 }
 @PostMapping("/{sku}/release") public InventoryItem release(@PathVariable String sku,@RequestParam int quantity){
  InventoryItem item=repository.findBySku(sku).orElseThrow(()->new NoSuchElementException("SKU not found"));item.release(quantity);return repository.save(item);
 }
}