package Service;

import Interface.ItrainerManager;
import model.Trainer;
import connection.connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainerManagerImp1 implements ItrainerManager {

    @Override
    public void addTrainer(Trainer trainer) {
        String sql = "INSERT INTO trainers (trainer_id, name, age, gender, specialization, years_of_experience, rating, hourly_rate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, trainer.getTrainerId());
            ps.setString(2, trainer.getName());
            ps.setInt(3, trainer.getAge());
            ps.setString(4, trainer.getGender());
            ps.setString(5, trainer.getSpecialization());
            ps.setInt(6, trainer.getYearsOfExperience());
            ps.setDouble(7, trainer.getRating());
            ps.setDouble(8, trainer.getHourlyRate());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Trainer added successfully.");
            } else {
                System.out.println("❌ Failed to add trainer.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error adding trainer: " + e.getMessage());
        }
    }

    @Override
    public void updateTrainer(int id, String name, String specialization) {
        String sql = "UPDATE trainers SET name = ?, specialization = ? WHERE trainer_id = ?";

        try (Connection conn = connection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, specialization);
            ps.setInt(3, id);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Trainer updated successfully.");
            } else {
                System.out.println("❌ Trainer not found.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error updating trainer: " + e.getMessage());
        }
    }

    @Override
    public void viewAllTrainers() {
        List<Trainer> trainers = getAllTrainers();
        if (trainers.isEmpty()) {
            System.out.println("❌ No trainers available.");
        } else {
            System.out.println("\n👥 All Trainers:");
            for (Trainer trainer : trainers) {
                System.out.println(trainer);
            }
        }
    }

    @Override
    public Trainer getTrainerById(int id) {
        String sql = "SELECT * FROM trainers WHERE trainer_id = ?";

        try (Connection conn = connection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                String specialization = rs.getString("specialization");
                int experience = rs.getInt("years_of_experience");
                double rating = rs.getDouble("rating");
                double hourlyRate = rs.getDouble("hourly_rate");

                return new Trainer(id, name, age, gender, specialization, experience, rating, hourlyRate);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching trainer: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Trainer> getAllTrainers() {
        List<Trainer> trainers = new ArrayList<>();
        String sql = "SELECT * FROM trainers";

        try (Connection conn = connection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("trainer_id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                String specialization = rs.getString("specialization");
                int experience = rs.getInt("years_of_experience");
                double rating = rs.getDouble("rating");
                double hourlyRate = rs.getDouble("hourly_rate");

                Trainer trainer = new Trainer(id, name, age, gender, specialization, experience, rating, hourlyRate);
                trainers.add(trainer);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching trainers: " + e.getMessage());
        }
        return trainers; // Return the list of trainers
    }
}
