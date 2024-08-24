package com.mirchanv.jdbc_crud;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.sql.*;

public class SendToHtml {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/durgadb";
        String username = "root";
        String password  = "rootroot";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             FileOutputStream fos = new FileOutputStream("Output.html")) {

            System.out.print("Please enter table name : ");
            String tableName = br.readLine();

            String query = "select * from " + tableName;

            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();

            if (!rs.isBeforeFirst()) {
                System.out.println("Table : " + tableName + " does not contain any records.");
                return;
            }

            int columnCount = rsmd.getColumnCount();

            StringBuilder html = new StringBuilder();
            html.append("<html>");
            html.append("<head>");
            html.append("<style>");
            html.append("table, th, td { border: 1px solid white border-collapse: collapse; }");
            html.append("th, td { background-color: #96D4D4; }");
            html.append("</style>");
            html.append("</head>");
            html.append("<body>");
            html.append("<table align=center style= width:75%>");
            html.append("<tr>");

            for (int col = 1; col <= columnCount ; col++) {
                html.append("<th>").append(rsmd.getColumnName(col).toUpperCase()).append("</th>");
            }

            html.append("</tr>");

            while (rs.next()) {
                html.append("<tr>");
                for (int col = 1; col <= columnCount; col++) {
                    html.append("<td>").append(rs.getString(col)).append("</td>");
                    if(col == columnCount) {
                        html.append("</tr>");
                    }
                }
            }

            html.append("</body>");
            html.append("</html>");

            // writing results to html page
            fos.write(html.toString().getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
