package com.mirchanv.jdbc_crud;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class Demo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/durgadb";
        String user = "root";
        String pwd = "rootroot";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             Connection conn = DriverManager.getConnection(url, user, pwd);
             Statement stmt = conn.createStatement()) {

            System.out.println("Do you want order of salaries in [ASC|DESC] ? : ");

            String order = br.readLine();

            String query = "select * from emp1 order by emp_salary ";

            if (order.equalsIgnoreCase("ASC")) {
                query = query + "ASC";
            } else if (order.equalsIgnoreCase("DESC")) {
                query = query + "DESC";
            } else {
                System.out.println("Invalid order entered!");
                System.exit(0);
            }

            ResultSet rs = stmt.executeQuery(query);

            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println(rsmd.getColumnName(1) + "\t" + rsmd.getColumnName(2) + "\t" + rsmd.getColumnName(3) + "\t" + rsmd.getColumnName(4));

            while (rs.next()) {
                System.out.print(rs.getInt(1) + "\t\t"
                        + rs.getString(2) + "\t\t"
                        + rs.getFloat(3) + "\t\t"
                        + rs.getString(4) + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
