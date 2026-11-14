package interface_segregation.example3.better;

/** Small role interface: only implement it if this worker really can work. */
public interface Workable {

    void work();
}
