package com.mirchanv.jdbc_crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class InsertDynamically {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/durgadb";
        String user = "root";
        String pwd = "rootroot";

        try (Scanner scanner = new Scanner(System.in);
             Connection con = DriverManager.getConnection(url, user, pwd);
             Statement stmt = con.createStatement()) {

            while(true) {
                System.out.print("Enter value for column [ENO] : ");
                int eno = scanner.nextInt();

                System.out.print("Enter value for column [ENAME] : ");
                String ename = scanner.next();

                System.out.print("Enter value for column [ESAL] : ");
                float esal = scanner.nextFloat();

                System.out.print("Enter value for column [EADDR] : ");
                String eaddr = scanner.next();

                String query = "insert into emp1 values(" + eno + ", '" + ename + "', " + esal + ", '" + eaddr + "')";
                System.out.println(query);
                if (stmt.executeUpdate(query) == 1) {
                    System.out.println("Row inserted successfully");
                } else {
                    System.out.println("Row not inserted successfully");
                }

                System.out.print("Would you like to add another row? [yes/No] : ");
                String userResponse = scanner.next();

                if (userResponse.equalsIgnoreCase("yes")) {
                    continue;
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
