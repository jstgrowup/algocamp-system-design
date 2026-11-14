package open_close.example5.better;

import java.util.List;

/** BETTER: depends only on the Shape abstraction, so it is closed for modification. */
public class AreaCalculator {

    public double totalArea(List<Shape> shapes) {
        double total = 0;
        for (Shape shape : shapes) {
            total += shape.area(); // polymorphism replaces the if/else chain
        }
        return total;
    }
}
