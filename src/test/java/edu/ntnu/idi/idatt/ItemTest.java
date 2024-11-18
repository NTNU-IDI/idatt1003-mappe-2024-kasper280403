package edu.ntnu.idi.idatt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    Item item;
    ExpirationDate expirationDate;

    @BeforeEach
    void setUp() {
        expirationDate = new ExpirationDate("milk", 10.0, "20-12-2024");
        item = new Item("milk", 10.0, "dL", 0.5, expirationDate);
    }

    @Test
    void testConstructor() {
        assertNotNull(item);
        assertEquals("milk", item.getName());
        assertEquals(10.0, item.getQuantity());
        assertEquals("dL", item.getUnit());
        assertEquals(0.5, item.getPrice());
        assertEquals("20-12-2024", item.getExpiration());
    }

    @Test
    void testGetName() {
        assertEquals("milk", item.getName());
    }

    @Test
    void testGetQuantity() {
        assertEquals(10.0, item.getQuantity());
    }

    @Test
    void testGetUnit() {
        assertEquals("dL", item.getUnit());
    }

    @Test
    void testGetPrice() {
        assertEquals(0.5, item.getPrice());
    }

    @Test
    void testGetExpiration() {
        assertEquals("20-12-2024", item.getExpiration());
    }
}