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

            System.out.print("SQL Query > ");
            String query = br.readLine();

            if (stmt.execute(query)) {
                ResultSet rs = stmt.getResultSet();
                ResultSetMetaData rsmd = rs.getMetaData();

                int columnCount = rsmd.getColumnCount();

                for (int col = 1; col <= columnCount; col++) {
                    if (col != columnCount) {
                        System.out.print(rsmd.getColumnName(col) + "\t");
                        continue;
                    }
                    System.out.println(rsmd.getColumnName(col));
                }

                while (rs.next()) {
                    for (int col = 1; col <= columnCount; col++) {
                        if (col != columnCount) {
                            System.out.print(rs.getString(col) + "\t\t");
                            continue;
                        }
                        System.out.println(rsmd.getColumnName(col));
                    }
                }
            } else {
                System.out.println("Records manipulated : " + stmt.getUpdateCount());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
