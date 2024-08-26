package com.mirchanv.jdbc_aggregatefunctions;

import java.sql.*;

public class Demo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/durgadb";
        String username = "root";
        String password = "rootroot";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {

            String query = "select * from emp1 where emp_salary in (select max(emp_salary) from emp1)";

            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                ResultSetMetaData rsmd = rs.getMetaData();

                int columnCount = rsmd.getColumnCount();

                for (int col = 1; col <= columnCount; col++) {
                    if (col != columnCount)
                        System.out.print(rsmd.getColumnName(col).toUpperCase() + "\t");
                    else
                        System.out.print(rsmd.getColumnName(col).toUpperCase() + "\n");
                }

                while (rs.next()) {
                    for (int col = 1; col <= columnCount; col++) {
                        if (col != columnCount)
                            System.out.print(rs.getString(col) + "\t\t");
                        else
                            System.out.print(rs.getString(col) + "\n");
                    }
                }

            } else {
                System.out.println("Employee table has no records!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
