package com.mirchanv.jdbc_aggregatefunctions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Demo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/durgadb";
        String username = "root";
        String password = "rootroot";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {

            System.out.print("Enter begin salary : " );
            float beginSalary = Float.parseFloat(br.readLine());

            System.out.print("Enter end salary : " );
            float endSalary = Float.parseFloat(br.readLine());

            String query = String.format("select count(*) from emp1 where emp_salary >= %f and emp_salary <= %f", beginSalary, endSalary);

            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                System.out.println("Number of employees having salary between " + beginSalary + " to " + endSalary + " : " + rs.getString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
