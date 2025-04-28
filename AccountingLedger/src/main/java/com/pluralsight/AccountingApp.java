package com.pluralsight;

import java.util.Scanner;

public class AccountingApp {

    public static void main(String[] args) {

        /* This app tracks all financial transactions for a business or for personal use
           All transactions should be read and saved to a transaction file. Each transaction should be saved as a
           single line with the following format:
            date|time|description|vendor|amount
            2023-04-15|10:13:25|ergonomic keyboard|Amazon|-89.50
            2023-04-15|11:15:00|Invoice 1001 paid|Joe|1500.00
         */

        Scanner scanner = new Scanner(System.in);
        boolean running = true; // controls the loop

        while (running) {
            System.out.println("\n---Home Screen---");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Invalid option. Please try again.");
            }
            continue;
        }

        char choice = Character.toUpperCase(input.charAt(0)); // only take the first letter and ignore cases

    }
}
