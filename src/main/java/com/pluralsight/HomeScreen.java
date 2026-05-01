package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import static com.pluralsight.LedgerScreen.ledgerScreen;
import static com.pluralsight.Main.transactions;
import static com.pluralsight.Main.scanner;

public class HomeScreen {
    public static void homeScreen() {
        // Infinite loop -> program runs until user exits//
        while (true) {

            // Display menu options to user (UI)
            System.out.println("\n==== LEDGER APP ====");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");

            // Read user input and convert to uppercase//
            String option = scanner.nextLine().trim().toUpperCase();

            System.out.print("Choose an option: ");
            //user input option//
            //Decide what to do based on user input//
            switch (option) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    ledgerScreen();
                    break;
                case "X":
                    System.exit(0);// // stops the program
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

                            //ADD DEPOSIT//

    public static void addDeposit() {

        // Ask user for transaction details //
        System.out.print("Date: ");
        String date = scanner.nextLine();

        System.out.print("Time: ");
        String time = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        // Ensure deposit is positive
        if (amount < 0) amount = -amount;

        // Create new transaction object //
        Transaction transaction = new Transaction(date, time, description, vendor, amount);

        // Add to list //
        transactions.add(transaction);

        // save to file //
        saveTransaction(transaction);
    }

                       // Make Payment //

    public static void makePayment() {

        // Same as deposit but amount must be negative //
        System.out.print("Date: ");
        String date = scanner.nextLine();

        System.out.print("Time: ");
        String time = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        // Ensure payment is negative
        if (amount > 0) amount = -amount;

        //
        Transaction transaction = new Transaction(date, time, description, vendor, amount);

        //
        transactions.add(transaction);

        //
        saveTransaction(transaction);
    }

                            //Save Transaction//

    public static void saveTransaction(Transaction t) {
        try {
            // Open file in APPEND mode (true = don’t overwrite) //
            BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true));

            // Write new line into file //
            writer.write("\n" + t.toString());

            writer.close();

        } catch (Exception e) {
            System.out.println("Error saving transaction.");
        }
    }
}

