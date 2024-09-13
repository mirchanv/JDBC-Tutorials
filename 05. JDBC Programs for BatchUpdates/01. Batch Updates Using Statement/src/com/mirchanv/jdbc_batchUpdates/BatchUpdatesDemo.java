package com.mirchanv.jdbc_batchUpdates;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BatchUpdatesDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/durgadb";
        String username = "root";
        String password = "rootroot";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {

            stmt.addBatch("insert into emp1 values(444, 'Anil', 30000, 'Pune')");
            stmt.addBatch("update emp1 set emp_salary = emp_salary + 5000");
            stmt.addBatch("delete from emp1 where emp_id = 333");

            int[] rowCounts = stmt.executeBatch();

            for (int rowsManipulated : rowCounts) {
                System.out.println("Rows Manipulated : " + rowsManipulated);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
