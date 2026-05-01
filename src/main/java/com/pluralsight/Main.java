package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import static com.pluralsight.HomeScreen.homeScreen;
import static com.pluralsight.LedgerScreen.ledgerScreen;
import static com.pluralsight.ReportsScreen.reportsScreen;

public class Main {

    // This list stores all transactions loaded from the CSV file //
    static ArrayList<Transaction> transactions = new ArrayList<>();

    // Scanner is used to get user input from the keyboard //
    static Scanner scanner = new Scanner(System.in);

    static void main() {

        // Load transactions from the file into memory //
        loadTransactions();
        homeScreen();
        ledgerScreen();
        reportsScreen();

    }

    public static void loadTransactions() {

        try {
            // Open the CSV file for reading //
            BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));

            String line;

            // Skip the header line (date|time|...) //
            reader.readLine();


            // Read file line by line //
            while ((line = reader.readLine()) != null) {


                // Split the line using | as separator //
                String[] parts = line.split("\\|");

                String date = parts[0];
                String time = parts[1];
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]); //  // amount (convert String -> double)// //

                Transaction transaction = new Transaction(date, time, description, vendor, amount);
                transactions.add(transaction);

            }

            // Close file after reading
            reader.close();

        } catch (Exception e) {
            System.out.println("Error reading file");
        }
    }
}
