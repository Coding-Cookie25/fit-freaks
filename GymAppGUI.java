import Service.AuthService;
import Service.BMICalculator;
import Service.TrainerManagerImp1;
import Interface.IAuthService;
import Interface.ItrainerManager;
import model.Trainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class GymAppGUI extends JFrame {
    private IAuthService authService;
    private ItrainerManager trainerManager;

    public GymAppGUI() {
        authService = new AuthService();
        trainerManager = new TrainerManagerImp1();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("FitFreaks Gym");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new GridLayout(0, 1, 10, 10));

        JLabel titleLabel = new JLabel("FitFreaks Gym", SwingConstants.CENTER);
        titleLabel.setForeground(Color.ORANGE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel);

        JButton signupButton = new JButton("Signup");
        signupButton.setBackground(Color.ORANGE);
        signupButton.setForeground(Color.BLACK);
        signupButton.addActionListener(new SignupAction());
        panel.add(signupButton);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(Color.ORANGE);
        loginButton.setForeground(Color.BLACK);
        loginButton.addActionListener(new LoginAction());
        panel.add(loginButton);

        JButton addTrainerButton = new JButton("Add Trainer");
        addTrainerButton.setBackground(Color.ORANGE);
        addTrainerButton.setForeground(Color.BLACK);
        addTrainerButton.addActionListener(new AddTrainerAction());
        panel.add(addTrainerButton);

        JButton viewTrainersButton = new JButton("View Trainers");
        viewTrainersButton.setBackground(Color.ORANGE);
        viewTrainersButton.setForeground(Color.BLACK);
        viewTrainersButton.addActionListener(new ViewTrainersAction());
        panel.add(viewTrainersButton);

        JButton bmiCalculatorButton = new JButton("BMI Calculator");
        bmiCalculatorButton.setBackground(Color.ORANGE);
        bmiCalculatorButton.setForeground(Color.BLACK);
        bmiCalculatorButton.addActionListener(new BMICalculatorAction());
        panel.add(bmiCalculatorButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(Color.ORANGE);
        exitButton.setForeground(Color.BLACK);
        exitButton.addActionListener(e -> System.exit(0));
        panel.add(exitButton);

        add(panel, BorderLayout.CENTER);
    }

    private class SignupAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel signupPanel = new JPanel(new GridLayout(0, 2));
            JTextField usernameField = new JTextField();
            JTextField emailField = new JTextField();
            JPasswordField passwordField = new JPasswordField();
            JTextField ageField = new JTextField();
            JTextField genderField = new JTextField();
            JTextField heightField = new JTextField();
            JTextField weightField = new JTextField();

            signupPanel.add(new JLabel("Username:"));
            signupPanel.add(usernameField);
            signupPanel.add(new JLabel("Email:"));
            signupPanel.add(emailField);
            signupPanel.add(new JLabel("Password:"));
            signupPanel.add(passwordField);
            signupPanel.add(new JLabel("Age:"));
            signupPanel.add(ageField);
            signupPanel.add(new JLabel("Gender:"));
            signupPanel.add(genderField);
            signupPanel.add(new JLabel("Height (cm):"));
            signupPanel.add(heightField);
            signupPanel.add(new JLabel("Weight (kg):"));
            signupPanel.add(weightField);

            int result = JOptionPane.showConfirmDialog(null, signupPanel, "Signup", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    String user = usernameField.getText();
                    String email = emailField.getText();
                    String pass = new String(passwordField.getPassword());
                    int age = Integer.parseInt(ageField.getText());
                    String gender = genderField.getText();
                    double height = Double.parseDouble(heightField.getText());
                    double weight = Double.parseDouble(weightField.getText());

                    String encryptedPassword = encryptPassword(pass);

                    if (authService.signup(user, email, encryptedPassword, age, gender, height, weight)) {
                        JOptionPane.showMessageDialog(null, "✅ Signup successful!");
                    } else {
                        JOptionPane.showMessageDialog(null, "❌ Signup failed!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "❌ Invalid input. Please try again.");
                }
            }
        }
    }

    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel loginPanel = new JPanel(new GridLayout(0, 2));
            JTextField usernameField = new JTextField();
            JPasswordField passwordField = new JPasswordField();

            loginPanel.add(new JLabel("Username:"));
            loginPanel.add(usernameField);
            loginPanel.add(new JLabel("Password:"));
            loginPanel.add(passwordField);

            int result = JOptionPane.showConfirmDialog(null, loginPanel, "Login", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String uname = usernameField.getText();
                String pwd = new String(passwordField.getPassword());

                String encryptedPassword = encryptPassword(pwd);

                if (authService.login(uname, encryptedPassword)) {
                    JOptionPane.showMessageDialog(null, "✅ Login successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "❌ Login failed!");
                }
            }
        }
    }

    private class AddTrainerAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel trainerPanel = new JPanel(new GridLayout(0, 2));
            JTextField nameField = new JTextField();
            JTextField ageField = new JTextField();
            JTextField genderField = new JTextField();
            JTextField specializationField = new JTextField();
            JTextField experienceField = new JTextField();
            JTextField ratingField = new JTextField();
            JTextField hourlyRateField = new JTextField();

            trainerPanel.add(new JLabel("Name:"));
            trainerPanel.add(nameField);
            trainerPanel.add(new JLabel("Age:"));
            trainerPanel.add(ageField);
            trainerPanel.add(new JLabel("Gender:"));
            trainerPanel.add(genderField);
            trainerPanel.add(new JLabel("Specialization:"));
            trainerPanel.add(specializationField);
            trainerPanel.add(new JLabel("Years of Experience:"));
            trainerPanel.add(experienceField);
            trainerPanel.add(new JLabel("Rating (out of 5):"));
            trainerPanel.add(ratingField);
            trainerPanel.add(new JLabel("Hourly Rate:"));
            trainerPanel.add(hourlyRateField);

            int result = JOptionPane.showConfirmDialog(null, trainerPanel, "Add Trainer", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    String tname = nameField.getText();
                    int tAge = Integer.parseInt(ageField.getText());
                    String tGender = genderField.getText();
                    String specialization = specializationField.getText();
                    int experience = Integer.parseInt(experienceField.getText());
                    double rating = Double.parseDouble(ratingField.getText());
                    double hourlyRate = Double.parseDouble(hourlyRateField.getText());

                    Trainer newTrainer = new Trainer(0, tname, tAge, tGender, specialization, experience, rating, hourlyRate);
                    trainerManager.addTrainer(newTrainer);
                    JOptionPane.showMessageDialog(null, "✅ Trainer added successfully!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "❌ Invalid input. Please try again.");
                }
            }
        }
    }

    private class ViewTrainersAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Trainer> trainersList = trainerManager.getAllTrainers();
            if (trainersList.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No trainers available.");
            } else {
                StringBuilder message = new StringBuilder();
                for (Trainer t : trainersList) {
                    message.append(t.toString()).append("\n\n");
                }
                JOptionPane.showMessageDialog(null, message.toString(), "Trainers", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private class BMICalculatorAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel bmiPanel = new JPanel(new GridLayout(0, 2));
            JTextField heightField = new JTextField();
            JTextField weightField = new JTextField();

            bmiPanel.add(new JLabel("Height (cm):"));
            bmiPanel.add(heightField);
            bmiPanel.add(new JLabel("Weight (kg):"));
            bmiPanel.add(weightField);

            int result = JOptionPane.showConfirmDialog(null, bmiPanel, "BMI Calculator", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    double height = Double.parseDouble(heightField.getText());
                    double weight = Double.parseDouble(weightField.getText());
                    double bmi = BMICalculator.calculateBMI(height, weight);
                    JOptionPane.showMessageDialog(null, "Your BMI is: " + bmi);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "❌ Invalid input. Please enter numeric values.");
                }
            }
        }
    }

    private String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedPassword) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GymAppGUI app = new GymAppGUI();
            app.setVisible(true);
        });
    }
}
