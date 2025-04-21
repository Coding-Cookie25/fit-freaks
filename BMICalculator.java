package Service;

public class BMICalculator {
    public static double calculateBMI(double heightCm, double weightKg) {
        double heightM = heightCm / 100;
        return weightKg / (heightM * heightM);
    }
}
