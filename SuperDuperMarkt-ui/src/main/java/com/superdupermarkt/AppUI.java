package com.superdupermarkt;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppUI {
    private static Scanner scanner;
    static boolean exit;

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        scanner = new Scanner(System.in);

        exit = false;
        while (!exit) {
            displayMenu();
            String choice = getUserChoice();

            switch (choice) {
                case "1":
                    List<Product> productsNew = SampleData.getSampleProducts();
                    if (productsNew.size() > 0) {
                        products.addAll(productsNew);
                        System.out.println("--> " + productsNew.size() + " Example Products Added");
                    }
                    break;
                case "2":
                    displayProductList(products);
                    LocalDate Stichtag = LocalDate.now();
                    int x = 0;
                    while (promptForNextDay()) {
                        displayProductsForDate(Stichtag.plusDays(x), products, x);
                        x = x + 1;
                    }
                    break;
                case "3":
                    try {
                        String Filepath = promptForCSVFilePath();
                        List<Product> productsImport = new ArrayList<>();
                        productsImport = ImportCSV.getProductsFromFile(Filepath);
                        if (productsImport.size() > 0) {
                            products.addAll(productsImport);
                            System.out.println("--> " + productsImport.size() + " Example Products Added");
                        }

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case "4":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }

    public AppUI(Scanner scanner) {
        AppUI.scanner = scanner;
    }

    public static String getUserChoice() {
        // UI logic for getting user input
        return scanner.nextLine();
    }

    public static void displayMenu() {
        // UI logic for displaying the menu options
        System.out.println("\n________________________________");
        System.out.println("|===== SUPER DUPER MARKET =====|");
        System.out.println("|______________________________|");
        System.out.println("1. Add Example Products");
        System.out.println("2. List Products");
        System.out.println("3. Add Products from CSV File");
        System.out.println("4. Exit");
        System.out.print("Select an option: ");
    }

    public static void displayProductList(List<Product> productList) {
        // UI logic for displaying the list of products
        System.out.println("\n=== List of Products ===");
        if (productList.isEmpty()) {
            System.out.println("--> No products available.");
        } else {
            System.out.println("\n_____________________");
            System.out.println("|== Original Daten ==|");
            System.out.println("|____________________|");

            for (int i = 0; i < productList.size(); i++) {
                System.out.println(i + 1 + ". " + productList.get(i).getOriginalData());
            }
        }

    }

    public static boolean promptForNextDay() {
        // UI logic for prompting the user to move to the next day
        System.out.print("\n--> Nächster Tag? (Y/N)");
        String choice = getUserChoice();
        char response = Character.toUpperCase(choice.charAt(0));
        while (true) {
            switch (response) {
                case 'Y':
                    return true;
                case 'N':
                    return false;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    return true;
            }
        }
    }

    public static void displayProductsForDate(LocalDate date, List<Product> products, int x) {
        // UI logic for displaying product data for a given date
        System.out.println("\n_____________________________");
        System.out.println("|=== " + date + " (Tag + " + x + ") ===|");
        System.out.println("|____________________________|");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i).getDataForDate(date));
        }
    }

    public static String promptForCSVFilePath() {
        // UI logic for prompting the user for a CSV file path
        System.out.print("\n--> Load Data from products.csv? (Y/N)");
        String choice = getUserChoice();
        char response = Character.toUpperCase(choice.charAt(0));

        switch (response) {
            case 'N':
                System.out.print("\n--> Filename. File should be in resources folder of superDuperMarkt-import:");
                return getUserChoice();
            default:
                return "products.csv";
        }

    }

}