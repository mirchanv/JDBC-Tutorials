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

            System.out.print("Please enter the name of the table : ");
            String tableName = br.readLine();

            String query = "select * from " + tableName;

            ResultSet rs = stmt.executeQuery(query);

            ResultSetMetaData rsmd = rs.getMetaData();

            int columnCount = rsmd.getColumnCount();

            for (int col = 1; col <= columnCount; col++) {
                System.out.print(rsmd.getColumnName(col) + "\t");
            }
            System.out.println();

            while (rs.next()) {
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
