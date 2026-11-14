package interface_segregation.example3.problem;

/** Fat interface: assumes every worker eats and sleeps. True for humans, not for robots. */
public interface Worker {

    void work();

    void eat();

    void sleep();
}
