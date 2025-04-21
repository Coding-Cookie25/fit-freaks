package model;

public class Trainer {
    public int trainerId;           // Changed id to trainerId for better clarity
    public String name;
    public int age;
    public String gender;
    public String specialization;
    public int yearsOfExperience;
    public double rating;
    public double hourlyRate;
	
    // Constructor
    public Trainer(int trainerId, String name, int age, String gender, String specialization,
                   int yearsOfExperience, double rating, double hourlyRate) {
        this.trainerId = trainerId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.specialization = specialization;
        this.yearsOfExperience = yearsOfExperience;
        this.rating = rating;
        this.hourlyRate = hourlyRate;
    }

    // Getters and Setters (optional)
    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public String toString() {
        return "Trainer [trainerId=" + trainerId + ", name=" + name + ", age=" + age + ", gender=" + gender
                + ", specialization=" + specialization + ", yearsOfExperience=" + yearsOfExperience + ", rating="
                + rating + ", hourlyRate=" + hourlyRate + "]";
    }

	public Object getExperience() {
		// TODO Auto-generated method stub
		return null;
	}
}
