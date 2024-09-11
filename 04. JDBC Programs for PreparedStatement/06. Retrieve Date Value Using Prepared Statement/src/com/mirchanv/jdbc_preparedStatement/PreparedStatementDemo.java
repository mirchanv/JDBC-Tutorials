package com.mirchanv.jdbc_preparedStatement;

import java.sql.*;

public class PreparedStatementDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/durgadb";
        String username = "root";
        String password = "rootroot";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pst = conn.prepareStatement("select * from students")) {

            ResultSet rs = pst.executeQuery();

            if (rs.isBeforeFirst()) {
                ResultSetMetaData rsmd = rs.getMetaData();

                int columnCount = rsmd.getColumnCount();
                for (int col = 1; col <= columnCount; col++) {
                    if (col != columnCount) {
                        System.out.print(rsmd.getColumnName(col) + "\t\t");
                        continue;
                    }
                    System.out.println(rsmd.getColumnName(col));
                }

                while (rs.next()) {
                    System.out.print(rs.getString(1) + "\t\t" +
                            rs.getString(2) + "\t\t" +
                            rs.getString(3) + "\n");
                }
            } else {
                System.out.println("No Employees found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
