package com.pluralsight;

import java.time.LocalDate;
import java.util.Scanner;
import static com.pluralsight.Main.transactions;

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

            Scanner scanner = new Scanner(System.in);
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

    public static void monthToDate(){
        LocalDate now = LocalDate.now();
        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate());

            if (date.getMonth() == now.getMonth() && date.getYear() == now.getYear()) {
                System.out.println(transaction);
            }
        }
    }

    public static void previousMonth() {

        LocalDate now = LocalDate.now().minusMonths(1);

        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate());

            if (date.getMonth() == now.getMonth() && date.getYear() == now.getYear()) {
                System.out.println(transaction);
            }
        }
    }
    public static void yearToDate () {
        int year = LocalDate.now().getYear();
        for (Transaction t : transactions) {
            LocalDate date = LocalDate.parse(t.getDate());

            if (date.getYear() == year) {
                System.out.println(t);
            }
        }
    }
    public static void previousYear() {
        int year = LocalDate.now().minusYears(1).getYear();
        for (Transaction t : transactions) {
            LocalDate date = LocalDate.parse(t.getDate());

            if (date.getYear() == year) {
                System.out.println(t);
            }
        }
    }
    public static void searchByVendor () {
        System.out.print("Enter vendor name: ");
        String input = scanner.nextLine().toLowerCase();

        for (Transaction t : transactions) {
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
        for (Transaction t : transactions) {

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
