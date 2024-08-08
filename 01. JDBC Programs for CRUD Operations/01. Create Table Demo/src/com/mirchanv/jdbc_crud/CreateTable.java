package com.mirchanv.jdbc_crud;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTable {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/durgadb";
        String user = "root";
        String pwd = "rootroot";

        // Establish connection between java application and database
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             Connection con = DriverManager.getConnection(url, user, pwd)) {

            System.out.println("Please enter table name: ");
            String tableName = br.readLine();

            // Create statement object
            Statement stmt = con.createStatement();

            // Write MySQL query
            // NOTE: we are using the executeUpdate() because our SQL Query is NON-SELECT query
            stmt.executeLargeUpdate("CREATE TABLE " + tableName + " (emp_id int(5) primary key, " +
                    "emp_name char(15), " +
                    "emp_salary float(5), " +
                    "emp_address char(10))");

            System.out.println("Table : " + tableName + " created!");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}