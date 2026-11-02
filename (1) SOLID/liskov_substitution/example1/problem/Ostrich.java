package liskov_substitution.example1.problem;

/** LSP violation: the child refuses a behaviour the parent promised (throws instead of flying). */
public class Ostrich extends Bird {

    @Override
    public void fly() {
        throw new UnsupportedOperationException("Ostrich cannot fly");
    }
}
