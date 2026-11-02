package open_close.example5.better;

import java.util.List;

/** Runner: Triangle was added without touching AreaCalculator, Circle or Rectangle. */
public class Main {

    public static void main(String[] args) {
        List<Shape> shapes = List.of(new Circle(2), new Rectangle(3, 4), new Triangle(3, 4));

        for (Shape shape : shapes) {
            System.out.println(shape.getClass().getSimpleName() + " area: " + shape.area());
        }
        System.out.println("Total area: " + new AreaCalculator().totalArea(shapes));
    }
}
