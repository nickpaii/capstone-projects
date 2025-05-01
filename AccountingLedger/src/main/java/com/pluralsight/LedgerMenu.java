package com.pluralsight;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class LedgerMenu {

    private String tranFile;

    public LedgerMenu(String tranFile) {
        this.tranFile = tranFile;
    }

    public void displayTransactions(String filter) {
        ArrayList<String[]> transactions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(tranFile))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", 5);
                if (parts.length == 5) {
                    transactions.add(parts);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
            return;
        }


        System.out.println("\n---Ledger Menu: " + filter.toUpperCase() + " ---");

        for (int i = transactions.size() - 1; i >= 0; i--) {
            String[] t = transactions.get(i);
            float amount;

            try {
                amount = Float.parseFloat(t[4]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount in transaction: " + String.join(" | ", t));
                continue;
            }

            boolean show = filter.equalsIgnoreCase("ALL") ||
                    (filter.equalsIgnoreCase("DEPOSIT") && amount > 0) ||
                    (filter.equalsIgnoreCase("PAYMENT") && amount < 0);

            if (show) {
                System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: $%.2f%n",
                        t[0], t[1], t[2], t[3], amount);
            }
        }
    }
}
