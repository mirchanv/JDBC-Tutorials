package com.mirchanv.jdbc_crud;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DeleteRecordsDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/durgadb";
        String user = "root";
        String pwd = "rootroot";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             Connection conn = DriverManager.getConnection(url, user, pwd);
             Statement stmt = conn.createStatement()) {

            System.out.print("Please enter salary : ");
            float salary = Float.parseFloat(br.readLine());

            String query = "delete from emp1 where emp_salary < " + salary;

            int rowCount = stmt.executeUpdate(query);
            System.out.println(rowCount + " rows deleted.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
