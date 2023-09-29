package com.superdupermarkt;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DataTest {
    List<Product> sampleList = SampleData.getSampleProducts();

    // Test Käse from sample data if Verfallsdatum 50-100 days
    // in future
    @Test
    public void testVerfallsdatumInFuture() {
        // List<Product> sampleList = App.getSampleProducts();

        List<Käse> käseList = new ArrayList<>();

        for (Product product : sampleList) {
            if (product instanceof Käse) {
                käseList.add((Käse) product);
            }
        }

        // Add instances of Käse (Cheese) with Verfallsdatum between 50 and 100 days in
        // the future
        LocalDate currentDate = LocalDate.now();
        LocalDate validMinDate = currentDate.plusDays(50);
        LocalDate validMaxDate = currentDate.plusDays(100);

        // Iterate through cheeseList and check if Verfallsdatum is in the valid range
        for (Product cheese : käseList) {
            LocalDate expirationDate = cheese.getVerfallsdatum();
            assertTrue("Käse " + cheese.getBezeichnung() + " has an invalid Verfallsdatum: " + expirationDate,
                    expirationDate.isAfter(validMinDate) && expirationDate.isBefore(validMaxDate));
        }
    }
}
