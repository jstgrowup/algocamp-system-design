package liskov_substitution.example1.problem;

/**
 * LSP violation, the silent kind: no exception, but the postcondition
 * "after fly() the bird is in the air" is no longer true. Often harder to spot than a throw.
 */
public class Penguin extends Bird {

    @Override
    public void fly() {
        // do nothing
    }
}
