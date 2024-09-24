package com.mirchanv.jdbc_clob;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class CharacterLargeObject {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/durgadb";
        String username = "root";
        String password = "rootroot";

        try (Scanner scanner = new Scanner(System.in);
             Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pst = conn.prepareStatement("insert into user_manuals values(?, ?, ?)")) {

            System.out.print("Please enter model of product: ");
            String model = scanner.nextLine();

            System.out.print("Please enter the brand of the product: ");
            String brand = scanner.nextLine();

            System.out.print("Please specify location of user manual: ");
            String filePath = scanner.nextLine();

            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);

            pst.setString(1, model);
            pst.setString(2, brand);
            pst.setCharacterStream(3, fileReader, file.length());

            int rowCount = pst.executeUpdate();

            if (rowCount == 1) {
                System.out.println("User manual of " + brand + " model: " + model + " added successfully!");
            } else {
                System.out.println("Record could not be added!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
