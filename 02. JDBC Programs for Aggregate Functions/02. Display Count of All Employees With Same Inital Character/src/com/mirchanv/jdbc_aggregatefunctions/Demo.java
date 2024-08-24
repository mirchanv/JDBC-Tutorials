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

            System.out.print("Please enter initial character: ");
            String initialChar = br.readLine();

            // query to get number of employees starting with specified character
            String query = "select count(*) from emp1 where emp_name like '" + initialChar + "%'";

            // select query hence we use updateQuery
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                System.out.println("Employees starting with letter '" + initialChar + "' : " + rs.getString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

