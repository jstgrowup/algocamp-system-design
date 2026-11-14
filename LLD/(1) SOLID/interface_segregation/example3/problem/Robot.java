package interface_segregation.example3.problem;

/** ISP violation: Robot is forced to implement eat() and sleep() that make no sense for it. */
public class Robot implements Worker {

    @Override
    public void work() {
        System.out.println("Robot is working");
    }

    @Override
    public void eat() {
        throw new UnsupportedOperationException("Robot cannot eat");
    }

    @Override
    public void sleep() {
        throw new UnsupportedOperationException("Robot cannot sleep");
    }
}
