package edu.ntnu.idi.idatt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExpirationDateTest {
    ExpirationDate expirationDate;

    @BeforeEach
    void setUp() {
        expirationDate = new ExpirationDate("milk", 10.0, "20-12-2024");
    }

    @Test
    void testConstructor() {
        assertNotNull(expirationDate);
        assertEquals("20-12-2024", expirationDate.getDate(0));
        assertEquals(10.0, expirationDate.amountExpired("21-12-2024"));
    }

    @Test
    void testUpdate() {
        expirationDate.update(5.0, "25-12-2024");

        assertEquals("25-12-2024", expirationDate.getDate(1));
        // Total quantity with different expiration dates
        assertEquals(2, expirationDate.dates.size());
    }

    @Test
    void testRemove() {
        expirationDate.remove(5.0, 10.0);
        // Verify that 5 units were removed, remaining should be 5
        assertEquals(5.0, expirationDate.quantities.getFirst());
    }

    @Test
    void testOldestIndex() {
        expirationDate.update(5.0, "25-12-2024");
        assertEquals(0, expirationDate.oldestIndex());
    }

    @Test
    void testGetDate() {
        expirationDate.update(5.0, "25-12-2024");
        assertEquals("25-12-2024", expirationDate.getDate(1));
    }

    @Test
    void testAmountExpired() {
        expirationDate.update(5.0, "25-12-2024");
        assertEquals(10.0, expirationDate.amountExpired("21-12-2024"));
        assertEquals(15.0, expirationDate.amountExpired("26-12-2024"));
    }

    @Test
    void testIsPastDate() {
        assertTrue(ExpirationDate.isPastDate("20-12-2023", "21-12-2024"));
        assertFalse(ExpirationDate.isPastDate("22-12-2024", "21-12-2024"));
    }
}