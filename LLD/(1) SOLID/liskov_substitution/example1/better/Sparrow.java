package liskov_substitution.example1.better;

public class Sparrow extends Bird implements Flyable {

    @Override
    public void fly() {
        System.out.println("Sparrow is flying");
    }
}
