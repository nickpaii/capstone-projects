package com.pluralsight;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;


public class AddDeposit {

    private String tranFile;

    public AddDeposit(String tranFile) {
        this.tranFile = tranFile;
    }

    public void addDeposit() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter deposit description: ");
        String description = scanner.nextLine();

        System.out.print("Enter vendor name: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter deposit amount: ");
        float amount = scanner.nextFloat();


        String date = LocalDate.now().toString();
        String time = LocalTime.now().withNano(0).toString();

        String transaction = String.join(",", date, time, description, vendor + "| " + amount);

        try (FileWriter writer = new FileWriter(tranFile, true)) {
            writer.write(transaction + "\n");
            System.out.println("Deposit saved.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
