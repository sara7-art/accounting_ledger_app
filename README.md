 Accounting_Ledger_Application

 Overview

This is a Java command-line application used to track financial transactions such as deposits and payments.

 Features

* Add deposits
* Make payments
* View all transactions
* Filter deposits and payments
* Run reports (monthly, yearly, custom search)

 Data Storage

Transactions are stored in a file named: transactions.csv


 Format

date|time|description|vendor|amount


 Project Structure

 Main.java → starts the application
 HomeScreen.java → main menu
 LedgerScreen.java → view transactions
 ReportScreen.java → reports and search
Transaction.java → data model
