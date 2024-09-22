package com.mirchanv.jdbc_blob;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.*;
import java.util.Scanner;

public class BinaryLargeObjectDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/durgadb";
        String username = "root";
        String password = "rootroot";

        try (Scanner scanner = new Scanner(System.in);
             Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pst = conn.prepareStatement("select * from emp2 where ID = ?")) {

            System.out.print("Please enter employee id: ");
            int empId = scanner.nextInt();

            pst.setInt(1, empId);
            ResultSet rs = pst.executeQuery();

            if(!rs.next()) {
                System.out.println("No records found for employee id : " + empId);
                return;
            }

            String filePath = "/Users/mirchanv/Desktop/Advanced JAVA/01. JDBC APPS/06. JDBC Programs for BLOB & CLOB/02. Retrieving BLOB Type From DB/received files/Employee_" + empId + ".jpeg";
            FileOutputStream fos = new FileOutputStream(filePath);

            InputStream inputStream = rs.getBinaryStream(5);
            int val = inputStream.read();
            while (val != -1) {
                fos.write(val);
                val = inputStream.read();
            }

            fos.close();

            System.out.println("Employee Id      : " + rs.getInt(1));
            System.out.println("Employee Name    : " + rs.getString(2));
            System.out.println("Employee Salary  : " + rs.getFloat(3));
            System.out.println("Employee Address : " + rs.getString(4));
            System.out.println("Employee Image   : " + filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
