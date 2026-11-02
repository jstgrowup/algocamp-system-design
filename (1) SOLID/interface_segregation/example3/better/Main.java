package interface_segregation.example3.better;

import java.util.List;

/** Each method depends only on the small interface it needs. */
public class Main {

    public static void main(String[] args) {
        HumanWorker human = new HumanWorker();
        Robot robot = new Robot();

        startShift(List.of(human, robot)); // both can work
        lunchBreak(List.of(human));        // only Eatable; lunchBreak(List.of(robot)) won't compile
        nightBreak(List.of(human));
    }

    static void startShift(List<? extends Workable> workers) {
        workers.forEach(Workable::work);
    }

    static void lunchBreak(List<? extends Eatable> eaters) {
        eaters.forEach(Eatable::eat);
    }

    static void nightBreak(List<? extends Sleepable> sleepers) {
        sleepers.forEach(Sleepable::sleep);
    }
}
