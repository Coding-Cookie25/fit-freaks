package model;

public class User {
    public int id;
    public String username;
    public String email;
    public String password;
    public int age;
    public String gender;
    public double heightCm;
    public double weightKg;

    public User(String username, String email, String password, int age, String gender, double heightCm, double weightKg) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.heightCm = heightCm;
        this.weightKg = weightKg;
    }
}
