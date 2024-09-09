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
             PreparedStatement pst = conn.prepareStatement("delete from emp1 where emp_salary > ?")) {

            System.out.print("Please enter salary range : ");
            float salary = scanner.nextFloat();

            pst.setFloat(1, salary);

            int rowCount = pst.executeUpdate();
            System.out.println("Records deleted : " + rowCount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
