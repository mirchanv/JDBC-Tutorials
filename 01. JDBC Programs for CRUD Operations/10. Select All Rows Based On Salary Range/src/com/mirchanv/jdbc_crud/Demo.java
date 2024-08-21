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

            System.out.print("Please enter begin salary : ");
            float beginSal = Float.parseFloat(br.readLine());

            System.out.print("Please enter end salary : ");
            float endSal = Float.parseFloat(br.readLine());

            String query = String.format("select * from emp1 where emp_salary >= %f and emp_salary <= %f", beginSal, endSal);

            ResultSet rs = stmt.executeQuery(query);

            // rs.isBeforeFirst() is true only if the cursor is before the first row.
            // if rs contain no rows, rs.isBeforeFirst() is false.
            if (!rs.isBeforeFirst()) {
                System.out.println("No data found");
                return;
            }

            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println(rsmd.getColumnName(1) + "\t" + rsmd.getColumnName(2) + "\t" + rsmd.getColumnName(3) + "\t" + rsmd.getColumnName(4));

            while (rs.next()) {
                System.out.println(rs.getInt(1) + "\t\t"
                        + rs.getString(2) + "\t\t"
                        + rs.getFloat(3) + "\t\t"
                        + rs.getString(4));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
