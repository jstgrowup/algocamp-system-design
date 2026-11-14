package liskov_substitution.example1.better;

/** Base type only holds what EVERY bird can do, so every subclass can honor it. */
public abstract class Bird {

    public void eat() {
        System.out.println(getClass().getSimpleName() + " is eating");
    }
}
