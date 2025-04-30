package com.pluralsight;

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
                    addDeposit(scanner);
                    break;
                case 'P':
                    makePayment(scanner);
                    break;
                case 'L':
                    viewLedger();
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

    public static void addDeposit(Scanner scanner) {
        System.out.print("Enter deposit amount: ");
        float amount = readFloat(scanner);

        System.out.print("Enter deposit description: ");
        String description = scanner.nextLine();

        // save this info to transactions.csv
        System.out.printf("Deposit of $%.2f with description: \"%s\" ", amount, description);
    }

    public static void makePayment(Scanner scanner) {

        System.out.print("Enter payment amount: ");
        float amount = readFloat(scanner);

        System.out.print("Enter payment description: ");
        String description = scanner.nextLine();

        // save this info to transactions.csv
        System.out.printf("Payment of $%.2f with description: \"%s\" ", amount, description);
    }

    public static void viewLedger() {
        System.out.println("Displaying ledger.");
    }

    public static float readFloat(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Float.parseFloat(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Please enter a valid amount: ");
            }
        }
    }

    private static void promptReturnToMenu() {
        System.out.println("\nPress Enter to return to the main menu...");
        scanner.nextLine();
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