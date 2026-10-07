package com.sajjantawar.inventory.domain;
import jakarta.persistence.*;
import java.util.UUID;
@Entity @Table(name="inventory")
public class InventoryItem {
 @Id private UUID id;
 @Column(nullable=false,unique=true,length=80) private String sku;
 @Column(nullable=false) private int availableQuantity;
 @Version private long version;
 protected InventoryItem(){}
 public InventoryItem(UUID id,String sku,int availableQuantity){this.id=id;this.sku=sku;this.availableQuantity=availableQuantity;}
 public UUID getId(){return id;} public String getSku(){return sku;} public int getAvailableQuantity(){return availableQuantity;} public long getVersion(){return version;}
 public void reserve(int quantity){if(quantity<=0)throw new IllegalArgumentException("Quantity must be positive");if(availableQuantity<quantity)throw new IllegalStateException("Insufficient inventory");availableQuantity-=quantity;}
 public void release(int quantity){if(quantity<=0)throw new IllegalArgumentException("Quantity must be positive");availableQuantity+=quantity;}
}