package Interface;

import model.Trainer;
import java.util.List;

public interface ItrainerManager1 {
    boolean addTrainer(Trainer trainer);
    List<Trainer> getAllTrainers();
    Trainer getTrainerById(int id);
    boolean removeTrainer(int id);
    boolean updateTrainer(Trainer trainer);
    int getTrainerCount();
}
