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
             PreparedStatement pst = conn.prepareStatement("update emp1 set emp_salary = emp_salary + ? where emp_salary < ?")) {

            System.out.print("Please enter bonus amount : ");
            float bonus = scanner.nextFloat();

            System.out.print("Please enter salary range : ");
            float salary = scanner.nextFloat();

            pst.setFloat(1, bonus);
            pst.setFloat(2, salary);

            int rowCount = pst.executeUpdate();
            System.out.println("Records Updated : " + rowCount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
