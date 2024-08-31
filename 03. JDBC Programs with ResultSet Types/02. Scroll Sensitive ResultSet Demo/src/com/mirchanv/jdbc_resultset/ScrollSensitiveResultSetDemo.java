package com.mirchanv.jdbc_resultset;

import java.sql.*;

public class ScrollSensitiveResultSetDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/durgadb";
        String username = "root";
        String password = "rootroot";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stmt.executeQuery("select * from emp1")) {

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            // state before any updates to database
            System.out.println("Current state of table emp1");
            getColumnsHeaders(rsmd, columnCount);
            showTableRecords(rs, columnCount);

            System.out.println("\nApplication is paused, please perform updates on the database and commit changes.");
            System.in.read();

            // state after performing updates to database
            System.out.println("After state of table emp1");
            rs.beforeFirst();
            getColumnsHeaders(rsmd, columnCount);
            showTableRecords(rs, columnCount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getColumnsHeaders(ResultSetMetaData rsmd, int columnCount) throws SQLException {
        for (int col = 1; col <= columnCount ; col++) {
            if (col != columnCount) {
                System.out.print(rsmd.getColumnName(col).toUpperCase() + "\t");
                continue;
            }
            System.out.print(rsmd.getColumnName(col).toUpperCase() + "\n");
        }
    }

    private static void showTableRecords(ResultSet rs, int columnCount) throws SQLException {
        while (rs.next()) {
            rs.refreshRow();
            for (int col = 1; col <= columnCount ; col++) {
                if (col != columnCount) {
                    System.out.print(rs.getString(col) + "\t\t");
                    continue;
                }
                System.out.println(rs.getString(col));
            }
        }
    }
}
