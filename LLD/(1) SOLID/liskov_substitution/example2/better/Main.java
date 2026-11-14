package liskov_substitution.example2.better;

import java.util.List;

/** Any Shape can be used wherever a Shape is expected, with no surprises. */
public class Main {

    public static void main(String[] args) {
        List<Shape> shapes = List.of(new Rectangle(5, 4), new Square(4));
        for (Shape shape : shapes) {
            System.out.println(shape.getClass().getSimpleName() + " area = " + shape.area());
        }
    }
}
