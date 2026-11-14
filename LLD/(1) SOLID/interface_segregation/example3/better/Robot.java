package interface_segregation.example3.better;

/** Only Workable: no eat()/sleep() to fake. */
public class Robot implements Workable {

    @Override
    public void work() {
        System.out.println("Robot is working");
    }
}
