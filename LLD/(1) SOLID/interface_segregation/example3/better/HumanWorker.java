package interface_segregation.example3.better;

public class HumanWorker implements Workable, Eatable, Sleepable {

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
