package com.mirchanv.jdbc_callablestatement;

import java.sql.*;
import java.util.Scanner;

public class CallableStatementDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/durgadb";
        String username = "root";
        String password = "rootroot";

        try (Scanner scanner = new Scanner(System.in);
             Connection conn = DriverManager.getConnection(url, username, password);
             CallableStatement cst = conn.prepareCall("{call getEmpSalary(?, ?)}")) {

            System.out.print("please enter employee id : ");
            int id = scanner.nextInt();
            cst.setInt(1, id);
            cst.registerOutParameter(2, Types.FLOAT);
            cst.execute();
            System.out.println("Salary for employee with id: " + id + " -> " + cst.getFloat(2));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
