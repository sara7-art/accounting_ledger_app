package com.pluralsight;

import java.util.Scanner;


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
                    ReportsScreen.reportsScreen();
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

        System.out.println("\n--- ALL TRANSACTIONS ---");

        for (int i = Main.transactions.size() - 1; i >= 0; i--) {
            System.out.println(Main.transactions.get(i));
        }
    }

    public static void displayDeposits() {

        System.out.println("\n--- DEPOSITS ---");

        for (int i = Main.transactions.size() - 1; i >= 0; i--) {

            Transaction t = Main.transactions.get(i);

            if (t.getAmount() > 0) {
                System.out.println(t);
            }
        }
    }

    public static void displayPayments() {

        System.out.println("\n--- PAYMENTS ---");

        for (int i = Main.transactions.size() - 1; i >= 0; i--) {

            Transaction t = Main.transactions.get(i);

            if (t.getAmount() < 0) {
                System.out.println(t);
            }
        }
    }
}