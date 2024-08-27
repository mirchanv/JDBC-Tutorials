package com.mirchanv.jdbc_resultsets;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class ScrollableResultSetDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/durgadb";
        String username = "root";
        String password = "rootroot";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {

            System.out.print("Please enter table name : ");
            String tableName = br.readLine();

            String query = String.format("select * from %s", tableName);

            ResultSet rs = stmt.executeQuery(query);

            if (rs.isBeforeFirst()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();

                getColumnNames(rsmd, columnCount);
                printForward(rs, columnCount);
                printBackward(rs, columnCount);
            } else {
                System.out.print("Table : " + tableName + " has no records!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getColumnNames(ResultSetMetaData rsmd, int columnCount) throws SQLException {
        for (int col = 1; col <= columnCount ; col++) {
            if (col != columnCount)
                System.out.print(rsmd.getColumnName(col).toUpperCase() + "\t");
            else
                System.out.print(rsmd.getColumnName(col).toUpperCase() + "\n");
        }
    }

    public static void printForward(ResultSet rs, int columnCount) throws SQLException {
        System.out.println("Records in forward direction");
        while (rs.next()) {
            for (int col = 1; col <= columnCount ; col++) {
                if (col != columnCount)
                    System.out.print(rs.getString(col) + "\t\t");
                else
                    System.out.print(rs.getString(col) + "\n");
            }
        }
        System.out.println();
    }

    public static void printBackward(ResultSet rs, int columnCount) throws SQLException {
        System.out.println("Records in backward direction");
        while (rs.previous()) {
            for (int col = 1; col <= columnCount ; col++) {
                if (col != columnCount)
                    System.out.print(rs.getString(col) + "\t\t");
                else
                    System.out.print(rs.getString(col) + "\n");
            }
        }
        System.out.println();
    }
}
