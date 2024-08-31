package com.mirchanv.jdbc_resultset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdatableResultSetDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/durgadb";
        String username = "root";
        String password = "rootroot";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stmt.executeQuery("select * from emp1")) {

            while (true) {
                // move cursor for new entry to be added (or after last record)
                rs.moveToInsertRow();

                System.out.print(">>> Please enter employee ID : ");
                int empId = Integer.parseInt(br.readLine());

                System.out.print(">>> Please enter employee name : ");
                String empName = br.readLine();

                System.out.print(">>> Please enter employee salary : ");
                float empSalary = Float.parseFloat(br.readLine());

                System.out.print(">>> Please enter employee Address : ");
                String empAddress = br.readLine();

                rs.updateInt(1, empId);
                rs.updateString(2, empName);
                rs.updateFloat(3, empSalary);
                rs.updateString(4, empAddress);

                // insert row into database
                rs.insertRow();

                System.out.println("STATUS : " + Status.SUCCESS + "\n");
                System.out.print(">>> Would you like to add another employee? [Yes/No] : ");

                String choice = br.readLine();

                if (choice.equalsIgnoreCase("No")) {
                    System.out.println("STATUS : " + Status.QUIT);
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("STATUS : " + Status.FAIL);
            e.printStackTrace();
        }
    }
}
