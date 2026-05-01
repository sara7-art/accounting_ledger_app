package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

import static com.pluralsight.LedgerScreen.ledgerScreen;
public class HomeScreen {

    public static void homeScreen() {
        Scanner scanner = new Scanner(System.in);
        // Infinite loop -> program runs until user exits//
        while (true) {

            // Display menu options to user (UI)
            System.out.println("\n***** MENU *****");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");

            // Read user input and convert to uppercase//
            String option = scanner.nextLine().trim().toUpperCase();

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

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        // ensure deposit is positive
        if (amount < 0) {
            System.out.println("Deposit must be positive!");
            return;
        }

        // create transaction
        Transaction t = new Transaction(
                java.time.LocalDate.now().toString(),
                java.time.LocalTime.now().toString(),
                description,
                vendor,
                amount
        );

        // CONFIRM INPUT
        System.out.println("\n--- CONFIRM DEPOSIT ---");
        System.out.println(t);
        System.out.print("Confirm? (Y/N): ");

        String confirm = scanner.nextLine().toUpperCase();

        if (confirm.equals("Y")) {
            Main.transactions.add(t);
            System.out.println("Deposit added successfully!");
        } else {
            System.out.println("Deposit cancelled.");
        }
    }

    public static void makePayment() {


        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        // payments should be negative
        if (amount > 0) {
            amount = -amount;
        }

        Transaction t = new Transaction(
                java.time.LocalDate.now().toString(),
                java.time.LocalTime.now().toString(),
                description,
                vendor,
                amount
        );

        // CONFIRM INPUT
        System.out.println("\n--- CONFIRM PAYMENT ---");
        System.out.println(t);
        System.out.print("Confirm? (Y/N): ");

        String confirm = scanner.nextLine().toUpperCase();

        if (confirm.equals("Y")) {
            Main.transactions.add(t);
            System.out.println("Payment added successfully!");
        } else {
            System.out.println("Payment cancelled.");
        }
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

