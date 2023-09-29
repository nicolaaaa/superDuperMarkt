package com.superdupermarkt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import java.time.LocalDate;

public class KäseTest {

    @Test
    public void testGetTagesQualität() {
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.now().plusDays(2);
        Käse käse = new Käse("TestKäse", 10, date1, 20.0);

        // On the day of creation (date1), quality should be 10
        assertEquals(10, käse.getTagesQualität(date1));

        // Two days later (date2), quality should be 8 (10 - 2)
        assertEquals(8, käse.getTagesQualität(date2));
    }

    @Test
    public void testIsProductRaus() {
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.now().plusDays(20);
        Käse käse = new Käse("TestKäse", 40, date2, 20.0);
        Käse käse2 = new Käse("TestKäse2", 20, date2, 20.0);

        // If Verfallsdatum is in the future, product is not 'raus'
        assertFalse(käse.isProductRaus(date1));

        // If Verfallsdatum == Abfragedatum, product is 'raus'
        assertTrue(käse.isProductRaus(date2));

        // käse2 is 'raus' both dates due to quality condition
        assertTrue(käse2.isProductRaus(date1));
        assertTrue(käse2.isProductRaus(date2));

    }

}