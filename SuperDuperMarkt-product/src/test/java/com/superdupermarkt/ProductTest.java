package com.superdupermarkt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.time.LocalDate;

public class ProductTest {

    @Test
    public void testGetBezeichnung() {
        Product product = new Product("TestProduct", 10, LocalDate.now(), 20.0);
        assertEquals("TestProduct", product.getBezeichnung());
    }

    @Test
    public void testGetVerfallsdatum() {
        LocalDate date = LocalDate.now();
        Product product = new Product("TestProduct", 10, date, 20.0);
        assertEquals(date, product.getVerfallsdatum());
    }

    @Test
    public void testGetPreis() {
        Product product = new Product("TestProduct", 10, LocalDate.now(), 20.0);
        assertEquals(20.0, product.getPreis(), 0.01);
    }

    @Test
    public void testGetQualität() {
        Product product = new Product("TestProduct", 10, LocalDate.now(), 20.0);
        assertEquals(10, product.getQualität());
    }

    @Test
    public void testCalculateTagesPreis() {
        LocalDate date = LocalDate.now();
        Product product = new Product("TestProduct", 10, date, 20.0);
        double expectedTagesPreis = 20.0 + 0.1 * 10;
        assertEquals(expectedTagesPreis, product.calculateTagesPreis(date), 0.01);
    }

    @Test
    public void testIsProductRaus() {
        LocalDate date = LocalDate.now();
        Product product = new Product("TestProduct", 10, date, 20.0);
        assertTrue(product.isProductRaus(date));
    }

    @Test
    public void testGetDataForDate() {
        LocalDate date = LocalDate.now();
        Product product = new Product("TestProduct", 10, date, 20.0);
        String expectedData = "(( RAUS )) ---TestProduct       --- Tagespreis: 21,00       --- Qualität: 10";
        assertEquals(expectedData, product.getDataForDate(date));
    }
}
