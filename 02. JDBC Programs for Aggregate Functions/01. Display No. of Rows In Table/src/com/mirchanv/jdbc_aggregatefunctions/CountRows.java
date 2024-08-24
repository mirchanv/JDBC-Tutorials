package com.mirchanv.jdbc_aggregatefunctions;

import com.mysql.cj.protocol.Resultset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CountRows {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/durgadb";
        String username = "root";
        String password = "rootroot";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {

            System.out.print("Please enter table name : ");
            String tableName = br.readLine();

            // query to get number of rows/records in given table
            String query = "select count(*) from " + tableName;

            // select query hence we use updateQuery
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                System.out.println("Number of records in " + tableName + " : " + rs.getString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
