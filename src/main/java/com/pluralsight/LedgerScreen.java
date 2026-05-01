package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;
import static com.pluralsight.ReportsScreen.reportsScreen;

public class LedgerScreen {
    static ArrayList<Transaction> transactions = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void ledgerScreen() {
        System.out.println("\nA) All");
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
            case "H":
                return;
            default:
                System.out.println("Invalid option.");
        }
    }

                    //DISPLAY METHODS//

    public static void displayAll() {
        for (int i = transactions.size() - 1; i >= 0; i--) {
            System.out.println(transactions.get(i));
        }
    }

    public static void displayDeposits() {
        for (int i = transactions.size() - 1; i >= 0; i--) {
            if (transactions.get(i).getAmount() > 0) {
                System.out.println(transactions.get(i));
            }
        }
    }

    public static void displayPayments() {
        for (int i = transactions.size() - 1; i >= 0; i--) {
            if (transactions.get(i).getAmount() < 0) {
                System.out.println(transactions.get(i));
            }
        }
    }
}
