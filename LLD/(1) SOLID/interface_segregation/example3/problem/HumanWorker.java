package interface_segregation.example3.problem;

public class HumanWorker implements Worker {

    @Override
    public void work() {
        System.out.println("Human is working");
    }

    @Override
    public void eat() {
        System.out.println("Human is eating lunch");
    }

    @Override
    public void sleep() {
        System.out.println("Human is sleeping");
    }
}
