package com.mirchanv.jdbc_blob;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class BinaryLargeObjectDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/durgadb";
        String username = "root";
        String password = "rootroot";

        try (Scanner scanner = new Scanner(System.in);
             Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pst = conn.prepareStatement("insert into emp2 values(?, ?, ?, ?, ?)")) {

            FileInputStream fis;

            while (true) {
                System.out.print("Please enter employee id : ");
                int id = scanner.nextInt();

                System.out.print("Please enter employee name : ");
                String name = scanner.next();

                System.out.print("Please enter employee salary : ");
                float salary = scanner.nextFloat();

                System.out.print("Please enter employee address : ");
                String address = scanner.next();

                System.out.print("Please enter full file path of the image location : ");
                String image = scanner.next();

                pst.setInt(1, id);
                pst.setString(2, name);
                pst.setFloat(3, salary);
                pst.setString(4, address);

                File imgFile = new File(image);
                fis = new FileInputStream(imgFile);

                pst.setBinaryStream(5, fis, imgFile.length());

                int rowCount = pst.executeUpdate();

                if (rowCount == 1) {
                    System.out.println("Employee : " + id + " successfully inserted!");
                } else {
                    System.out.println("Employee could not be added!");
                }

                System.out.print("Do you want to add another employee? [Yes/No] : ");
                String choice = scanner.next();

                if (choice.equalsIgnoreCase("No")) break;
            }

            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
