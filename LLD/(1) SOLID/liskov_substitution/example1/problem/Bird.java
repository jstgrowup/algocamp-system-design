package liskov_substitution.example1.problem;

/**
 * Parent contract: "every Bird can fly()".
 * That sounds natural ("a bird is a bird") but it is a promise that not all birds can keep.
 */
public class Bird {

    public void fly() {
        System.out.println(getClass().getSimpleName() + " is flying");
    }
}
