package com.superdupermarkt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import java.time.LocalDate;

public class WeinTest {

    @Test
    public void testGetTagesQualität() {
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.now().plusDays(30);
        Wein wein = new Wein("TestWein", 30, date1, 20.0);

        // On the day of creation (date1), quality should be 30
        assertEquals(30, wein.getTagesQualität(date1));

        // Three days later (date2), quality should be 33 (30 + 3)
        assertEquals(33, wein.getTagesQualität(date2));

        // Quality should not exceed 50
        LocalDate date3 = LocalDate.now().plusDays(200);
        assertEquals(50, wein.getTagesQualität(date3));
    }

    @Test
    public void testIsProductRaus() {
        LocalDate date1 = LocalDate.of(2023, 9, 22);
        LocalDate date2 = LocalDate.of(2023, 9, 25);
        Wein wein = new Wein("TestWein", 30, date1, 20.0);

        // Product should never be 'raus'
        assertFalse(wein.isProductRaus(date1));
        assertFalse(wein.isProductRaus(date2));
    }

    @Test
    public void testCalculateTagesPreis() {
        LocalDate date1 = LocalDate.of(2023, 9, 22);
        Wein wein = new Wein("TestWein", 30, date1, 20.0);

        // Tagespreis should be 20.0 * 0.1 * Qualität (30) = 60.0
        assertEquals(60.0, wein.calculateTagesPreis(date1), 0.01);
    }
}