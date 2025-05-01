package com.pluralsight;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ReportsMenu {

    private String tranFile;

    public ReportsMenu(String tranFile) {
        this.tranFile = tranFile;
    }


    public static void showReportsMenu(String tranFile) {

        Scanner scanner = new Scanner(System.in);

        boolean inReportsMenu = true;

        while (inReportsMenu) {

            {
                System.out.println("\n---Reports Menu---");
                System.out.println("1) Month To Date");
                System.out.println("2) Previous Month");
                System.out.println("3) Year To Date");
                System.out.println("4) Previous Year");
                System.out.println("5) Search by Vendor");
                System.out.println("0) Back");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        reportMonthToDate(tranFile);
                        break;
                    case 2:
                        reportPreviousMonth(tranFile);
                        break;
                    case 3:
                        reportYearToDate(tranFile);
                        break;
                    case 4:
                        reportPreviousYear(tranFile);
                        break;
                    case 5:
                        searchByVendor(tranFile, scanner);
                        break;
                    case 0:
                        inReportsMenu = false; // go back to previous menu
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        }
    }


    public static void reportMonthToDate(String tranFile) {
        ArrayList<String[]> transactions = new ArrayList<>();
        LocalDate today = LocalDate.now();

        try (BufferedReader reader = new BufferedReader(new FileReader(tranFile))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 5);
                if (parts.length == 5) {
                    String dateString = parts[0].trim(); // format: yyyy-MM-dd

                    try {
                        LocalDate date = LocalDate.parse(dateString);
                        if (date.getYear() == today.getYear() && date.getMonth() == today.getMonth()) {
                            transactions.add(parts);
                        }
                    } catch (Exception e) {
                        System.out.println("Skipping invalid date: " + dateString);
                    }
                }
            }

            // display results newest first
            System.out.println("\n---Month to Date Report---");
            for (int i = transactions.size() - 1; i >= 0; i--) {
                String[] t = transactions.get(i);
                float amount = Float.parseFloat(t[4]);
                System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f%n",
                        t[0], t[1], t[2], t[3], amount);
            }

        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }
    }

    public static void reportPreviousMonth(String tranFile) {
        ArrayList<String[]> transactions = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate previousMonth = today.minusMonths(1);

        try (BufferedReader reader = new BufferedReader(new FileReader(tranFile))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 5);
                if (parts.length == 5) {
                    String dateString = parts[0].trim(); // format: yyyy-MM-dd

                    try {
                        LocalDate date = LocalDate.parse(dateString);
                        if (date.getYear() == previousMonth.getYear() && date.getMonth() == previousMonth.getMonth()) {
                            transactions.add(parts);
                        }
                    } catch (Exception e) {
                        System.out.println("Skipping invalid date: " + dateString);
                    }
                }
            }

            System.out.println("\n---Previous Month Report---");
            for (int i = transactions.size() - 1; i >= 0; i--) {
                String[] t = transactions.get(i);
                float amount = Float.parseFloat(t[4]);
                System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f%n",
                        t[0], t[1], t[2], t[3], amount);
            }

        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }
    }

    public static void reportYearToDate(String tranFile) {
        ArrayList<String[]> transactions = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate startOfYear = today.withDayOfYear(1);

        try (BufferedReader reader = new BufferedReader(new FileReader(tranFile))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 5);
                if (parts.length == 5) {
                    String dateString = parts[0].trim(); // format: yyyy-MM-dd

                    try {
                        LocalDate date = LocalDate.parse(dateString);
                        if (!date.isBefore(startOfYear) && !date.isAfter(today)) {
                            transactions.add(parts);
                        }
                    } catch (Exception e) {
                        System.out.println("Skipping invalid date: " + dateString);
                    }
                }
            }

            System.out.println("\n---Year to Date Report---");
            for (int i = transactions.size() - 1; i >= 0; i--) {
                String[] t = transactions.get(i);
                float amount = Float.parseFloat(t[4]);
                System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f%n",
                        t[0], t[1], t[2], t[3], amount);
            }

        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }
    }


    public static void reportPreviousYear(String tranFile) {
        ArrayList<String[]> transactions = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate startOfPrevYear = today.minusYears(1).withDayOfYear(1);
        LocalDate endOfPrevYear = startOfPrevYear.withMonth(12).withDayOfMonth(31);

        try (BufferedReader reader = new BufferedReader(new FileReader(tranFile))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 5);
                if (parts.length == 5) {
                    String dateString = parts[0].trim(); // format: yyyy-MM-dd

                    try {
                        LocalDate date = LocalDate.parse(dateString);
                        if (!date.isBefore(startOfPrevYear) && !date.isAfter(endOfPrevYear)) {
                            transactions.add(parts);
                        }
                    } catch (Exception e) {
                        System.out.println("Skipping invalid date: " + dateString);
                    }
                }
            }

            System.out.println("\n---Previous Year Report---");
            for (int i = transactions.size() - 1; i >= 0; i--) {
                String[] t = transactions.get(i);
                float amount = Float.parseFloat(t[4]);
                System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f%n",
                        t[0], t[1], t[2], t[3], amount);
            }

        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }
    }


    public static void searchByVendor(String tranFile, Scanner scanner) {

        ArrayList<String[]> transactions = new ArrayList<>();

        scanner.nextLine(); // ignore scanner int buffer


        System.out.print("\nEnter vendor name to search: ");
        String vendorSearch = scanner.nextLine().trim().toLowerCase();

        try (BufferedReader reader = new BufferedReader(new FileReader(tranFile))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 5);
                if (parts.length == 5) {
                    String vendor = parts[3].trim().toLowerCase();
                    if (vendor.contains(vendorSearch)) {
                        transactions.add(parts);
                    }
                }
            }

            System.out.println("\n---Transactions for Vendor: " + vendorSearch + "---");
            for (int i = transactions.size() - 1; i >= 0; i--) {
                String[] t = transactions.get(i);
                float amount = Float.parseFloat(t[4]);
                System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f%n",
                        t[0], t[1], t[2], t[3], amount);
            }

            if (transactions.isEmpty()) {
                System.out.println("No transactions found for vendor: " + vendorSearch);
            }

        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }
    }
}

