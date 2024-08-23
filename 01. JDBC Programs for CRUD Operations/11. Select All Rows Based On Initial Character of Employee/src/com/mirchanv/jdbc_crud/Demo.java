package com.mirchanv.jdbc_crud;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class Demo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/durgadb";
        String username = "root";
        String password = "rootroot";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {

            System.out.print("Please enter initial character of employee : " );
            String initalChar  = br.readLine() + "%";

            String query = String.format("select * from emp1 where emp_name like '%s'", initalChar);

            ResultSet rs = stmt.executeQuery(query);

            if (!rs.isBeforeFirst()) {
                System.out.println("No employees found!");
                return;
            }

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            for (int col = 1; col <= columnCount; col++) {
                System.out.print(rsmd.getColumnName(col) + "\t");
            }
            System.out.println();

            while (rs.next()) {
                // directly getting all cols as String type
                for (int col = 1; col <= columnCount; col++) {
                    System.out.print(rs.getString(col) + "\t\t");
                }
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}