package liskov_substitution.example1.better;

/** No fly() here: nothing to throw, nothing to fake. */
public class Ostrich extends Bird {

    public void run() {
        System.out.println("Ostrich is running");
    }
}
