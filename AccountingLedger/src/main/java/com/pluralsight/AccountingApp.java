package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AccountingApp {


    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        /* This app tracks all financial transactions for a business or for personal use
           All transactions should be read and saved to a transaction file. Each transaction should be saved as a
           single line with the following format:
            date|time|description|vendor|amount
            2023-04-15|10:13:25|ergonomic keyboard|Amazon|-89.50
            2023-04-15|11:15:00|Invoice 1001 paid|Joe|1500.00
         */


        boolean running = true; // controls the loop

        String tranFile = "data/transactions.csv";

        while (running) {
            showMainMenu();
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Invalid option. Please try again.");
                continue;
            }

            char choice = Character.toUpperCase(input.charAt(0)); // only take the first letter and ignore cases

            switch (choice) {               // switch cases for each menu option and default for incorrect inputs
                case 'D':
                    AddDeposit addDeposit = new AddDeposit(tranFile);
                    addDeposit.addDeposit();
                    break;
                case 'P':
                    MakePayment makePayment = new MakePayment(tranFile);
                    makePayment.makePayment();
                    break;
                case 'L':
                    boolean ledgerRunning = true;
                    while (ledgerRunning) {
                        System.out.println("\n---Ledger Menu---");
                        System.out.println("A) Show All Entries");
                        System.out.println("D) Show Deposits");
                        System.out.println("P) Show Payments");
                        System.out.println("R) Show Reports");
                        System.out.println("H) Return to Home Screen");
                        System.out.print("Enter your choice: ");

                        String ledgerChoice = scanner.nextLine();

                        LedgerMenu ledger = new LedgerMenu(tranFile);

                        switch (ledgerChoice.toUpperCase()) {
                            case "A":
                                ledger.displayTransactions("ALL");
                                break;
                            case "D":
                                ledger.displayTransactions("DEPOSITS");
                                break;
                            case "P":
                                ledger.displayTransactions("PAYMENTS");
                                break;
                            case "R":
                                ReportsMenu.showReportsMenu(tranFile);
                                break;
                            case "H":
                                ledgerRunning = false;
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                                break;
                        }
                    }
                    break;
                case 'X':
                    System.out.println("Exiting the application. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Please select D, P, L, or X.");
                    break;
            }
        }

        scanner.close();
    }


    public static void showMainMenu() {
        System.out.println("\n---Home Screen---");
        System.out.println("D) Add Deposit");
        System.out.println("P) Make Payment (Debit)");
        System.out.println("L) Ledger");
        System.out.println("X) Exit");
        System.out.print("Enter your choice: ");
    }
}
