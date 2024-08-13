package com.mirchanv.jdbc_crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class DynamicTable {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/durgadb";
        String user = "root";
        String pwd = "rootroot";

        try(Scanner scanner = new Scanner(System.in);
            Connection con = DriverManager.getConnection(url, user, pwd);
            Statement stmt = con.createStatement()) {

            System.out.print("Please enter table name: ");
            String tableName = scanner.next();

            String query = "CREATE TABLE " + tableName + "(";
            String primaryKeyCols = "";
            int primaryKeyCounter = 0;

            while(true) {
                System.out.print("Enter column name: ");
                String columnName = scanner.next();

                System.out.print("Enter column type: ");
                String columnType = scanner.next();

                System.out.print("Enter column size: ");
                String columnSize = scanner.next();

                query = query + columnName + " " + columnType + " (" + columnSize + "), ";

                System.out.print("Is this a primary key [Yes/No] ? ");
                String isPrimaryKey = scanner.next();

                if (isPrimaryKey.equalsIgnoreCase("Yes")) {
                    primaryKeyCounter++;
                    if (primaryKeyCounter == 1) {
                        primaryKeyCols = columnName;
                    } else {
                        primaryKeyCols = primaryKeyCols + "," + columnName;
                    }
                }
                System.out.print("Would you like to add another column [Yes/No] ? ");
                String userResponse = scanner.next();

                if (userResponse.equalsIgnoreCase("Yes")) {
                    continue;
                } else {
                    query = query + " primary key(" + primaryKeyCols + "))";
                    break;
                }
            }
            stmt.executeUpdate(query);
            System.out.println("Table " + tableName + " has been created successfully.");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
