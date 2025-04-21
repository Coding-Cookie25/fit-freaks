package Interface;

import model.Trainer;
import java.util.List;

public interface ItrainerManager {
    void addTrainer(Trainer trainer);
    void updateTrainer(int id, String name, String specialty);
    void viewAllTrainers();
    Trainer getTrainerById(int id);
}