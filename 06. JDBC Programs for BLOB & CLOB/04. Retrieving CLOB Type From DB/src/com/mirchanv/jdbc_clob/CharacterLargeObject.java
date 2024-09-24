package com.mirchanv.jdbc_clob;

import java.io.FileWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class CharacterLargeObject {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/durgadb";
        String username = "root";
        String password = "rootroot";

        try (Scanner scanner = new Scanner(System.in);
             Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pst = conn.prepareStatement("select * from user_manuals where MODEL = ?")) {

            System.out.print("Please enter name of model: ");
            String model = scanner.nextLine();

            System.out.print("Please enter destination and filename to store the retrieved user manual: ");
            String filePath = scanner.nextLine();
            FileWriter fileWriter = new FileWriter(filePath);

            pst.setString(1, model);

            ResultSet rs = pst.executeQuery();
            rs.next();

            Reader reader = rs.getCharacterStream("USER_MANUAL");
            int val = reader.read();
            while(val != -1){
                fileWriter.write(val);
                val = reader.read();
            }
            System.out.println("Model           : " + rs.getString("MODEL"));
            System.out.println("Brand           : " + rs.getString("BRAND"));
            System.out.println("User Manual     : " + filePath);

            fileWriter.close();

            } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
