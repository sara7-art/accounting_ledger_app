package com.pluralsight;

import java.time.LocalDate;
import java.util.Scanner;

public class ReportsScreen {
    static Scanner scanner = new Scanner(System.in);

    public static void reportsScreen() {
        while (true) {
            System.out.println();
            System.out.println("\n***** REPORTS *****");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("6) Custom Search");
            System.out.println("0) Back");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    monthToDate();
                    break;
                case "2":
                    previousMonth();
                    break;
                case "3":
                    yearToDate();
                    break;
                case "4":
                    previousYear();
                    break;
                case "5":
                    searchByVendor();
                    break;
                case "6":
                    customSearch();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public static void monthToDate() {

        // Get today's date from the system (computer clock)
        LocalDate now = LocalDate.now();

        System.out.println("\n--- MONTH TO DATE ---");

        boolean found = false; // used to detect if we printed anything

        // Loop through ALL transactions in the program
        for (Transaction t : Main.transactions) {

            // Convert the date string from CSV into a real LocalDate object
            LocalDate date = LocalDate.parse(t.getDate());

            // Check if transaction is:
            // 1. NOT in the future
            // 2. same month as today
            // 3. same year as today
            if (!date.isAfter(now)
                    && date.getMonth() == now.getMonth()
                    && date.getYear() == now.getYear()) {

                System.out.println(t); // print matching transaction
                found = true;
            }
        }


    // If nothing matched, show message instead of “silent failure”
        if (!found) {
            System.out.println("No transactions found for this month.");
        }
    }

    public static void previousMonth() {

    // Get the previous month based on today's date
    LocalDate target = LocalDate.now().minusMonths(1);

    System.out.println("\n--- PREVIOUS MONTH ---");

    boolean found = false;

    for (Transaction t : Main.transactions) {

        LocalDate date = LocalDate.parse(t.getDate());

        // Compare ONLY month + year (important for previous month)
        if (date.getMonth() == target.getMonth()
                && date.getYear() == target.getYear()) {

            System.out.println(t);
            found = true;
        }
    }

    if (!found) {
        System.out.println("No transactions found for previous month.");
    }
}

public static void yearToDate() {

    // Get current year (example: 2026)
    int year = LocalDate.now().getYear();

    System.out.println("\n--- YEAR TO DATE ---");

    boolean found = false;

    for (Transaction t : Main.transactions) {

        LocalDate date = LocalDate.parse(t.getDate());

        // Include everything from this year (Jan 1 → today)
        if (date.getYear() == year) {

            System.out.println(t);
            found = true;
        }
    }

    if (!found) {
        System.out.println("No transactions found for this year.");
    }
}
public static void previousYear() {

    // Get last year (example: 2025 if current is 2026)
    int year = LocalDate.now().minusYears(1).getYear();

    System.out.println("\n--- PREVIOUS YEAR ---");

    boolean found = false;

    for (Transaction t : Main.transactions) {

        LocalDate date = LocalDate.parse(t.getDate());

        // Only transactions from previous year
        if (date.getYear() == year) {

            System.out.println(t);
            found = true;
        }
    }

    if (!found) {
        System.out.println("No transactions found for previous year.");
    }
}

    public static void searchByVendor () {
        System.out.print("Enter vendor name: ");
        String input = scanner.nextLine().toLowerCase();

        for (Transaction t : Main.transactions) {
            if (t.getVendor().toLowerCase().contains(input)) {
                System.out.println(t);
            }
        }
    }
    ////////////////////////////////CUSTOM SEARCH//////////////////////////////////////////////////////
    public static void customSearch () {
        // user input (optional)
        // This is used to search transactions

        System.out.print("Start Date (yyyy-mm-dd) or blank: ");
        String startInput = scanner.nextLine();

        System.out.print("End Date (yyyy-mm-dd) or blank: ");
        String endInput = scanner.nextLine();

        System.out.print("Description or blank: ");
        String descInput = scanner.nextLine().toLowerCase(); // We convert it to lowercase so search is not case-sensitive

        System.out.print("Vendor or blank: ");
        String vendorInput = scanner.nextLine().toLowerCase();

        System.out.print("Amount or blank: ");
        String amountInput = scanner.nextLine();

        // Convert start date string into a LocalDate object
        // If user left it blank, we store null (meaning: no filter)
        LocalDate startDate = startInput.isEmpty() ? null : LocalDate.parse(startInput);

        // Convert end date string into a LocalDate object
        // If blank -> no end date filter
        LocalDate endDate = endInput.isEmpty() ? null : LocalDate.parse(endInput);

        // Convert amount string into a Double
        // If blank -> no amount filter
        Double amount = amountInput.isEmpty() ? null : Double.parseDouble(amountInput);

        // Loop through all transactions stored in the system
        for (Transaction t : Main.transactions) {

            // Convert transaction date (stored as String) into LocalDate
            LocalDate date = LocalDate.parse(t.getDate());


            // If start date exists and transaction is before it -> skip this transaction
            if (startDate != null && date.isBefore(startDate)) continue;

            // If end date exists and transaction is after it -> skip it
            if (endDate != null && date.isAfter(endDate)) continue;

            // If description filter is not empty and transaction does NOT contain it -> skip
            if (!descInput.isEmpty() && !t.getDescription().toLowerCase().contains(descInput)) continue;

            // If vendor filter is not empty and transaction does NOT match -> skip
            if (!vendorInput.isEmpty() && !t.getVendor().toLowerCase().contains(vendorInput)) continue;

            // If amount filter exists and transaction amount is NOT equal -> skip
            if (amount != null && t.getAmount() != amount) continue;

            // If transaction passes all filters, print it
            System.out.println(t);
        }
    }
}
