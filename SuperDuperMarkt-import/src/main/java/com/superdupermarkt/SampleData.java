package com.superdupermarkt;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SampleData {
    public static List<Product> getSampleProducts() {
        // Utility logic for generating sample products
        List<Product> products = new ArrayList<>();

        // Sample products
        products.add(new Product("Product A", 20, LocalDate.of(2023, 10, 22), 50.00));
        products.add(new Product("Product B", 15, LocalDate.of(2023, 10, 1), 30.00));
        products.add(new Käse("Käse A", 35, LocalDate.of(2023, 11, 22), 4.00));
        products.add(new Käse("Käse B", 50, LocalDate.of(2023, 11, 30), 4.00));
        products.add(new Wein("Wein V", 3, LocalDate.of(2023, 10, 23), 40.00));
        products.add(new Wein("Wein D", 5, LocalDate.of(2023, 11, 8), 40.00));
        return products;
    }

}