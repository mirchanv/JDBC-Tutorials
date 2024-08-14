package com.mirchanv.jdbc_crud;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UpdateRecordsDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/durgadb";
        String user = "root";
        String pwd = "rootroot";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             Connection conn = DriverManager.getConnection(url, user, pwd);
             Statement stmt = conn.createStatement()) {

            System.out.print("Please enter desired bonus amount : ");
            int bonusAmount = Integer.parseInt(br.readLine());

            System.out.print("Please enter min salary : ");
            float minSalary = Float.parseFloat(br.readLine());

            System.out.print("Please enter max salary : ");
            float maxSalary = Float.parseFloat(br.readLine());

            String query = "update emp1 set emp_salary = emp_salary + " + bonusAmount
                    + " where emp_salary >= " + minSalary + "and emp_salary <= " + maxSalary;

            int rowCount = stmt.executeUpdate(query);
            System.out.println(rowCount + " rows updated.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
