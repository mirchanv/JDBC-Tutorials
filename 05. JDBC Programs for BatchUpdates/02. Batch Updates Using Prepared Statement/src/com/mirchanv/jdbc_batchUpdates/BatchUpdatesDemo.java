package com.mirchanv.jdbc_batchUpdates;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class BatchUpdatesDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/durgadb";
        String username = "root";
        String password = "rootroot";

        try (Scanner scanner = new Scanner(System.in);
             Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pst = conn.prepareStatement("insert into emp1 values(?, ?, ?, ?)")) {

            while (true) {
                System.out.print("Please enter employee ID : ");
                int id = scanner.nextInt();

                System.out.print("Please enter employee Name : ");
                String name = scanner.next();

                System.out.print("Please enter employee salary : ");
                float salary = scanner.nextFloat();

                System.out.print("Please enter employee address : ");
                String address = scanner.next();

                pst.setInt(1, id);
                pst.setString(2, name);
                pst.setFloat(3, salary);
                pst.setString(4, address);

                pst.addBatch();

                System.out.print("Would you like to add another employee? [Yes/No] : ");
                String choice = scanner.next();

                if (choice.equalsIgnoreCase("No")) break;
            }

            int[] rowCounts = pst.executeBatch();
            int totalRowsInserted = 0;

            for (int rowInserted : rowCounts) totalRowsInserted += rowInserted;

            System.out.println("Rows inserted : " + totalRowsInserted);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
