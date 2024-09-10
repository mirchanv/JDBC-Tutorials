package com.mirchanv.jdbc_preparedStatement;

import java.sql.*;
import java.util.Scanner;

public class PreparedStatementDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/durgadb";
        String username = "root";
        String password = "rootroot";

        try (Scanner scanner = new Scanner(System.in);
             Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pst = conn.prepareStatement("select * from emp1 where emp_salary < ?")) {

            System.out.print("Please enter salary range : ");
            float salary = scanner.nextFloat();

            pst.setFloat(1, salary);
            ResultSet rs = pst.executeQuery();

            if (rs.isBeforeFirst()) {
                ResultSetMetaData rsmd = rs.getMetaData();

                int columnCount = rsmd.getColumnCount();
                for (int i = 1; i <= columnCount ; i++) {
                    if (i != columnCount) {
                        System.out.print(rsmd.getColumnName(i)+"\t");
                        continue;
                    }
                    System.out.println(rsmd.getColumnName(i));
                }

                while (rs.next()) {
                    System.out.println(rs.getString(1) + "\t\t" +
                            rs.getString(2) + "\t\t" +
                            rs.getString(3) + "\t\t" +
                            rs.getString(4));
                }
            } else {
                System.out.println("No records found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
