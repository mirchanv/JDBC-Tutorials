package com.mirchanv.jdbc_resultset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdatableResultSetDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/durgadb";
        String username = "root";
        String password = "rootroot";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stmt.executeQuery("select * from emp1")) {

            System.out.print("Please enter begin salary : ");
            float beginSal = Float.parseFloat(br.readLine());

            System.out.print("Please enter end salary : ");
            float endSal = Float.parseFloat(br.readLine());

            while(rs.next()) {
                int empId = rs.getInt(1);
                float empSal = rs.getFloat(3);

                if (empSal >= beginSal && empSal <= endSal) {
                    rs.updateFloat(3, empSal + 5000);
                }

                // updating the row in the database
                rs.updateRow();

                System.out.println("Salary for employee : " + empId + " has been updated!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
