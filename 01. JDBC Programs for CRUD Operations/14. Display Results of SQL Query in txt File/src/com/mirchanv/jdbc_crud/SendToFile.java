package com.mirchanv.jdbc_crud;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.sql.*;

public class SendToFile {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/durgadb";
        String username = "root";
        String password = "rootroot";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             FileOutputStream fos = new FileOutputStream("Output.txt")) {

            System.out.print("SQL Query > ");
            String query = br.readLine();

            StringBuilder data = new StringBuilder("SQL Query > ").append(query).append("\n");

            if (stmt.execute(query)) {
                ResultSet rs = stmt.getResultSet();
                ResultSetMetaData rsmd = rs.getMetaData();

                int columnCount = rsmd.getColumnCount();

                for (int col = 1; col <= columnCount ; col++) {
                    if (col != columnCount)
                        data.append(rsmd.getColumnName(col)).append("\t");
                    else
                        data.append(rsmd.getColumnName(col)).append("\n");
                }

                while (rs.next()) {
                    for (int col = 1; col <= columnCount ; col++) {
                        if (col != columnCount)
                            data.append(rs.getString(col)).append("\t\t");
                        else
                            data.append(rs.getString(col)).append("\n");
                    }
                }

            } else {
                int rowCount = stmt.getUpdateCount();
                data.append("Records Manipulated : ").append(rowCount);
            }

            // write data to txt file
            fos.write(new String(data).getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
