package model;

import connection.connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestUserInsertion {
    public static void main(String[] args) {
        // Get database connection
        Connection conn = connection.getConnection();

        if (conn != null) {
            // Insert a user into the 'users' table
            String insertQuery = "INSERT INTO users (username, email, password_hash, age, gender, height_cm, weight_kg) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
                // Set values for the new user
                stmt.setString(1, "john_doe");
                stmt.setString(2, "john.doe@example.com");
                stmt.setString(3, "hashed_password_123"); // Ideally, use a hashed password
                stmt.setInt(4, 25);  // Age
                stmt.setString(5, "male");  // Gender
                stmt.setDouble(6, 175.5);  // Height in cm
                stmt.setDouble(7, 70.2);   // Weight in kg

                // Execute the insert query
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("✅ User inserted successfully!");
                } else {
                    System.out.println("❌ Insertion failed!");
                }

            } catch (SQLException e) {
                System.out.println("❌ Error occurred during insertion: " + e.getMessage());
            }
        } else {
            System.out.println("❌ Database connection failed.");
        }
    }
}
