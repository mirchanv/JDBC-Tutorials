package com.mirchanv.jdbc_preparedStatement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class PreparedStatementDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/durgadb";
        String username = "root";
        String password = "rootroot";

        try (Scanner scanner = new Scanner(System.in);
             Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pst = conn.prepareStatement("insert into students values(?, ?, ?)")){

            while (true) {
                System.out.print("Please enter student ID : ");
                int id = scanner.nextInt();

                System.out.print("Please enter student Name : ");
                String name = scanner.next();

                System.out.print("Please enter Date of Admission (yyyy-MM-DD): ");
                String date = scanner.next();

                pst.setInt(1, id);
                pst.setString(2, name);
                pst.setDate(3, Date.valueOf(date));

                int rowCount = pst.executeUpdate();

                if (rowCount == 1) {
                    System.out.println("Employee " + id + " added successfully!");
                } else {
                    System.out.println("Could not add employee record!");
                }

                System.out.print("Do you want to add another student? Yes/No : ");
                String choice = scanner.next();

                if (choice.equalsIgnoreCase("No")) break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
