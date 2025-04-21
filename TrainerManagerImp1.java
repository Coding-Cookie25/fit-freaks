package Service;

import Interface.ItrainerManager1;
import model.Trainer;

import java.util.ArrayList;
import java.util.List;

public class TrainerManagerImp1 implements ItrainerManager1 {
    private List<Trainer> trainers;

    public TrainerManagerImp1() {
        this.trainers = new ArrayList<>();
    }

    @Override
    public void addTrainer(Trainer trainer) {
        trainers.add(trainer);
    }

    @Override
    public List<Trainer> getAllTrainers() {
        return trainers;
    }
}
