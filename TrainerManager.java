package Service;

import Interface.ItrainerManager;
import model.Trainer;
import connection.connection;

import java.sql.*;
import java.util.List;

public class TrainerManager implements ItrainerManager {

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
        String sql = "SELECT * FROM trainers";

        try (Connection conn = connection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\n👥 All Trainers:");
            while (rs.next()) {
                int id = rs.getInt("trainer_id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                String specialization = rs.getString("specialization");
                int experience = rs.getInt("years_of_experience");
                double rating = rs.getDouble("rating");
                double hourlyRate = rs.getDouble("hourly_rate");

                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age +
                        ", Gender: " + gender + ", Specialization: " + specialization +
                        ", Experience: " + experience + " years, Rating: " + rating +
                        "/5, Hourly Rate: ₹" + hourlyRate);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching trainers: " + e.getMessage());
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
		// TODO Auto-generated method stub
		return null;
	}
}
