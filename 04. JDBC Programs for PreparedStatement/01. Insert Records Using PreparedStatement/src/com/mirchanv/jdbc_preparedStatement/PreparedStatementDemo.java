package com.mirchanv.jdbc_preparedStatement;

import java.sql.Connection;
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
             PreparedStatement pst = conn.prepareStatement("insert into emp1 values(?, ?, ?, ?)")) {

            while (true) {
                System.out.print("Please enter employee ID : ");
                int empId = scanner.nextInt();

                System.out.print("Please enter employee Name : ");
                String empName = scanner.next();

                System.out.print("Please enter employee Salary : ");
                float empSalary = scanner.nextFloat();

                System.out.print("Please enter employee Address : ");
                String empAddress = scanner.next();

                pst.setInt(1, empId);
                pst.setString(2, empName);
                pst.setFloat(3, empSalary);
                pst.setString(4, empAddress);

                int rowCount = pst.executeUpdate();

                if (rowCount == 1) {
                    System.out.println("Employee : " + empId + " added successfully!");
                } else {
                    System.out.println("Employee could not be added!");
                }

                System.out.print("Would you like to add another employee? [Yes/No] : ");
                String choice = scanner.next();

                if (choice.equalsIgnoreCase("No")) {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
