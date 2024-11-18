package edu.ntnu.idi.idatt;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    Storage storage = new Storage();

    @Test
    void testGetItem() {
        String name = "milk";
        storage.updateStorage(name, 12.0, "dL", 12.0, "20-12-2024");

        Item item = storage.getItem(name);
        assertNotNull(item);
        assertEquals(name, item.getName());
    }

    @Test
    void testItemList() {
        String name = "milk";
        storage.updateStorage(name, 12.0, "dL", 12.0, "20-12-2024");

        ArrayList<String> itemList = storage.itemList(name);
        assertTrue(itemList.contains(name));
    }

    @Test
    void testItemListExpired() {
        String name = "yogurt";
        storage.updateStorage(name, 10.0, "dL", 20.0, "15-08-2023");

        ArrayList<String> expiredItems = storage.itemListExpired(name, 5.0);
        assertTrue(expiredItems.contains(name));
    }


    @Test
    void testItemExists() {
        storage.updateStorage("cheese", 4.0, "kg", 40.0, "10-10-2024");

        assertTrue(storage.itemExists("cheese"));
        assertFalse(storage.itemExists("ham"));
    }

    @Test
    void testExpiredList() {
        storage.updateStorage("bread", 10.0, "kg", 15.0, "01-01-2023");
        String currentDate = "02-01-2023";

        ArrayList<ArrayList<String>> expiredList = storage.expiredList(currentDate);
        assertFalse(expiredList.isEmpty());
        assertEquals("bread", expiredList.getFirst().getFirst());
    }

    @Test
    void testDeleteExpiredItems() {
        storage.updateStorage("juice", 10.0, "L", 10.0, "01-01-2023");
        String currentDate = "02-01-2023";

        storage.deleteExpiredItems(currentDate);
        assertTrue(storage.itemExists("juice"));
        assertEquals(0.0, storage.getItem("juice").getQuantity());
    }
}