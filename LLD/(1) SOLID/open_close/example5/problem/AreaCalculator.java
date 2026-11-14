package open_close.example5.problem;

/**
 * PROBLEM: violates the Open/Closed Principle.
 * Every new shape forces us to EDIT this tested class (add another else-if).
 * Forget one place and it fails at runtime, not at compile time.
 */
public class AreaCalculator {

    public double area(Object shape) {
        if (shape instanceof Circle c) {
            return Math.PI * c.radius() * c.radius();
        } else if (shape instanceof Rectangle r) {
            return r.width() * r.height();
        }
        // To support Triangle we must add: else if (shape instanceof Triangle t) { ... }
        throw new IllegalArgumentException("Unknown shape: " + shape);
    }
}
