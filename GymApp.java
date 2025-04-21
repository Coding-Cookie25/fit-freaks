import Service.AuthService;
import Service.BMICalculator;
import Service.TrainerManager;
import Interface.IAuthService;
import Interface.ItrainerManager;
import model.Trainer;

import java.io.Console;
import java.util.Scanner;

public class GymApp {
    public static void main(String[] args) {
        IAuthService authService = new AuthService();
        ItrainerManager trainerManager = new TrainerManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n==== FitFreaks Gym ====");
            System.out.println("1. Signup\n2. Login\n3. Add Trainer\n4. View Trainers\n5. BMI Calculator\n6. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Clear buffer

            switch (choice) {
                case 1:  // Signup
                    System.out.print("Username: ");
                    String user = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Password: ");
                    String pass = getPassword();  // Secure password input
                    System.out.print("Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Gender: ");
                    String gender = sc.nextLine();
                    System.out.print("Height (cm): ");
                    double height = sc.nextDouble();
                    System.out.print("Weight (kg): ");
                    double weight = sc.nextDouble();
                    if (authService.signup(user, email, pass, age, gender, height, weight)) {
                        System.out.println("✅ Signup successful!");
                    } else {
                        System.out.println("❌ Signup failed!");
                    }
                    break;

                case 2:  // Login
                    System.out.print("Username: ");
                    String uname = sc.nextLine();
                    System.out.print("Password: ");
                    String pwd = getPassword();  // Secure password input
                    if (authService.login(uname, pwd)) {
                        System.out.println("✅ Login successful!");
                    } else {
                        System.out.println("❌ Login failed!");
                    }
                    break;

                case 3:  // Add Trainer
                    System.out.print("Trainer ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Name: ");
                    String tname = sc.nextLine();
                    System.out.print("Age: ");
                    int tage = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Gender: ");
                    String tgender = sc.nextLine();
                    System.out.print("Specialization: ");
                    String spec = sc.nextLine();
                    System.out.print("Years of Experience: ");
                    int exp = sc.nextInt();
                    System.out.print("Rating (out of 5): ");
                    double rating = sc.nextDouble();
                    System.out.print("Hourly Rate: ");
                    double rate = sc.nextDouble();

                    Trainer trainer = new Trainer(id, tname, tage, tgender, spec, exp, rating, rate);
                    trainerManager.addTrainer(trainer);
                    break;

                case 4:  // View Trainers
                    trainerManager.viewAllTrainers();
                    break;

                case 5:  // BMI Calculator
                    System.out.print("Height (cm): ");
                    double h = sc.nextDouble();
                    System.out.print("Weight (kg): ");
                    double w = sc.nextDouble();
                    double bmi = BMICalculator.calculateBMI(h, w);
                    System.out.println("Your BMI is: " + bmi);
                    break;

                case 6:  // Exit
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Method for securely getting the password input
    private static String getPassword() {
        Console console = System.console();
        if (console != null) {
            char[] passwordArray = console.readPassword("Password: ");
            return new String(passwordArray);
        } else {
            // Fallback for IDEs that do not support console input
            Scanner sc = new Scanner(System.in);
            System.out.print("Password: ");
            return sc.nextLine();
        }
    }
}
