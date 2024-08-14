package com.mirchanv.jdbc_crud;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class SelectAllRowsDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/durgadb";
        String user = "root";
        String pwd = "rootroot";

        try (Connection conn = DriverManager.getConnection(url, user, pwd);
             Statement stmt = conn.createStatement()) {

            // Obtaining the ResultSet object from our query
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp1");

            // Creating ResultSetMetaData object in order to obtain column names of the returned ResultSet object
            ResultSetMetaData rsmd = rs.getMetaData();

            // Getting all column names and displaying it
            System.out.println(rsmd.getColumnName(1) + "\t" + rsmd.getColumnName(2) + "\t" + rsmd.getColumnName(3) + "\t" + rsmd.getColumnName(4));

            // While ResultSet cursor has an entry next, we will go ahead and print the data for each column
            while (rs.next()) {
                System.out.print(rs.getInt(1) + "\t\t"
                        + rs.getString(2) + "\t\t"
                        + rs.getFloat(3) + "\t\t"
                        + rs.getString(4) + "\n");
            }

        } catch (Exception e) {
            System.out.println("Table does not exist!");
            e.printStackTrace();
        }
    }
}
