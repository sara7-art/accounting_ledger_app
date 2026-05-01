package com.pluralsight;
import static com.pluralsight.Main.*;
import java.util.Scanner;
import static com.pluralsight.ReportsScreen.reportsScreen;

public class LedgerScreen {

    static Scanner scanner = new Scanner(System.in);

    public static void ledgerScreen() {
        while (true) {
            System.out.println();
            System.out.println("\n***** LEDGER *****");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");

            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "A":
                    displayAll();
                    break;
                case "D":
                    displayDeposits();
                    break;
                case "P":
                    displayPayments();
                    break;
                case "R":
                    reportsScreen();
                    break;
                case "H":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

                    //DISPLAY METHODS//

    public static void displayAll() {

        // All entries should show newest entries first
        // loop goes through the list backwards so the newest transactions appear first in the ledger
        for (int i = transactions.size() - 1; i >= 0; i--) {
            System.out.println(transactions.get(i));
        }
    }

    public static void displayDeposits() {

        // Start from last index in the list
        // Continue until we reach the FIRST item
        // Move backwards (decrease index by 1 each time)
        for (int i = transactions.size() - 1; i >= 0; i--) {

            // Get transaction at current index
            if (transactions.get(i).getAmount() > 0) {
                System.out.println(transactions.get(i));
            }
        }
    }

    public static void displayPayments() {

        for (int i = transactions.size() - 1; i >= 0; i--) { // loop goes through the list backwards so the newest transactions appear first in the ledger

            // Get transaction at current index
            if (transactions.get(i).getAmount() < 0) {
                System.out.println(transactions.get(i));
            }
        }
    }
}
