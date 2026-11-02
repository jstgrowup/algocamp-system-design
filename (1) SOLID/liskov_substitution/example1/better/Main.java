package liskov_substitution.example1.better;

import java.util.List;

/** Each method asks for exactly the type it needs; no try/catch and no instanceof checks. */
public class Main {

    public static void main(String[] args) {
        // Every Bird can be substituted where a Bird is expected.
        for (Bird bird : List.of(new Sparrow(), new Ostrich(), new Penguin())) {
            bird.eat();
        }

        // Only Flyables are accepted here, so an Ostrich cannot even be passed (compile-time safety).
        letThemFly(List.of(new Sparrow()));
    }

    static void letThemFly(List<? extends Flyable> flyers) {
        flyers.forEach(Flyable::fly);
    }
}
