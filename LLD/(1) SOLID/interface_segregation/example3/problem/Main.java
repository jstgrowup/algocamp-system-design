package interface_segregation.example3.problem;

import java.util.List;

/** The lunch break only needs eat(), but it receives "Workers" and the Robot blows up. */
public class Main {

    public static void main(String[] args) {
        List<Worker> workers = List.of(new HumanWorker(), new Robot());

        for (Worker worker : workers) {
            worker.work();
        }

        for (Worker worker : workers) {
            try {
                worker.eat();
            } catch (UnsupportedOperationException e) {
                System.out.println(worker.getClass().getSimpleName() + " failed at lunch: " + e.getMessage());
            }
        }
    }
}
