package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

 //In case user select ledger screen
import static com.pluralsight.LedgerScreen.ledgerScreen; // In case user select ledger screen
import static com.pluralsight.Main.*;


public class HomeScreen {
    public static void homeScreen() {
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

    //Helper method -create transaction
    public static Transaction createTransaction(Scanner scanner, boolean isDeposit) {

        System.out.print("\nEnter date: ");
        String date = scanner.nextLine();

        System.out.print("Enter time: ");
        String time = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine()); // convert input to number

        // fix amount
        if (isDeposit) {
            amount = Math.abs(amount);
        } else {
            amount = -Math.abs(amount);
        }

        // Create a Transaction object using user input
        // Return the created transaction back to the calling method
        return new Transaction(date, time, description, vendor, amount);
    }

    public static void addDeposit() {

        // Create a deposit transaction (true = deposit)
        Transaction t = createTransaction(scanner, true);

        transactions.add(t);  // Add transaction to the list in memory
        saveTransaction(t);   // Save transaction to the file (CSV)
    }


    public static void makePayment() {

        // Create a deposit transaction (false = deposit)
        Transaction t = createTransaction(scanner, false);

        transactions.add(t); // Add transaction to the list in memory
        saveTransaction(t);  // Save transaction to the file (CSV)
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

