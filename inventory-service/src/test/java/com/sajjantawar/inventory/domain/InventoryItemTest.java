package com.sajjantawar.inventory.domain;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;
class InventoryItemTest {
 @Test void reserveReducesAvailableQuantity(){InventoryItem item=new InventoryItem(UUID.randomUUID(),"SKU-1",10);item.reserve(3);assertThat(item.getAvailableQuantity()).isEqualTo(7);}
 @Test void reserveRejectsOversell(){InventoryItem item=new InventoryItem(UUID.randomUUID(),"SKU-1",2);assertThatThrownBy(()->item.reserve(3)).isInstanceOf(IllegalStateException.class);}
 @Test void releaseRestoresQuantity(){InventoryItem item=new InventoryItem(UUID.randomUUID(),"SKU-1",2);item.release(3);assertThat(item.getAvailableQuantity()).isEqualTo(5);}
}