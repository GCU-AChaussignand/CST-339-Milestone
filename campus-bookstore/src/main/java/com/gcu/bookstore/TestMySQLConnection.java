package com.gcu.bookstore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestMySQLConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:8889/campus_bookstore?useSSL=false&serverTimezone=UTC";
        String username = "root";
        String password = "root";

        System.out.println("🔍 Testing MySQL connection...");
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            System.out.println("✅ Successfully connected to MySQL!");
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SHOW TABLES;");
            System.out.println("📚 Tables in database:");
            while (rs.next()) {
                System.out.println(" - " + rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("❌ Connection failed!");
            e.printStackTrace();
        }
    }
}
