package com.mirchanv.jdbc_crud;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DropTableDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/durgadb";
        String user = "root";
        String pwd = "rootroot";

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Connection conn = DriverManager.getConnection(url, user, pwd);
            Statement stmt = conn.createStatement()) {

            System.out.print("Please enter table name you want to delete : ");
            String tableName = br.readLine();

            String query = "drop table " + tableName;

            stmt.executeUpdate(query);
            System.out.println("Table " + tableName + " dropped successfully.");

        } catch (Exception e) {
            System.out.println("Table does not exists, could not be dropped!");
            e.printStackTrace();
        }
    }
}
