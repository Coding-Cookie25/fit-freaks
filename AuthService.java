package Service;

import Interface.IAuthService;
import model.User;
import java.sql.*;
import connection.connection;
import org.mindrot.jbcrypt.BCrypt;

public class AuthService implements IAuthService {

    @Override
    public boolean login(String username, String password) {
        try (Connection conn = connection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT password_hash FROM users WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password_hash");
                if (BCrypt.checkpw(password, hashedPassword)) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean signup(String username, String email, String password, int age, String gender, double height, double weight) {
        try (Connection conn = connection.getConnection()) {
            // ✅ Hash the password before storing
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            PreparedStatement ps = conn.prepareStatement("INSERT INTO users (username, email, password_hash, age, gender, height_cm, weight_kg) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, hashedPassword);
            ps.setInt(4, age);
            ps.setString(5, gender);
            ps.setDouble(6, height);
            ps.setDouble(7, weight);

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Signup error: " + e.getMessage());
            return false;
        }
    }
}
