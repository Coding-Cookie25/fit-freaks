package Interface;

public interface IAuthService {
    boolean login(String username, String password);
    boolean signup(String username, String email, String password, int age, String gender, double height, double weight);
}
