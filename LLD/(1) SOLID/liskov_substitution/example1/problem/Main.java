package liskov_substitution.example1.problem;

import java.util.List;

/** Code written against Bird works for Sparrow but breaks for Ostrich and Penguin. */
public class Main {

    public static void main(String[] args) {
        List<Bird> birds = List.of(new Sparrow(), new Ostrich(), new Penguin());

        for (Bird bird : birds) {
            String name = bird.getClass().getSimpleName();
            try {
                bird.fly(); // the caller only knows "Bird", yet has to be afraid of subclasses
                System.out.println(name + ".fly() returned (but did it really fly?)");
            } catch (UnsupportedOperationException e) {
                System.out.println(name + " broke the Bird contract: " + e.getMessage());
            }
        }
    }
}
