package open_close.example5.problem;

/** Runner: the calculator works for old shapes and breaks for the new one until we edit it. */
public class Main {

    public static void main(String[] args) {
        AreaCalculator calculator = new AreaCalculator();

        System.out.println("Circle area:    " + calculator.area(new Circle(2)));
        System.out.println("Rectangle area: " + calculator.area(new Rectangle(3, 4)));

        try {
            System.out.println("Triangle area:  " + calculator.area(new Triangle(3, 4)));
        } catch (IllegalArgumentException e) {
            // Pain: the only fix is to open AreaCalculator and modify it (and re-test every shape).
            System.out.println("Triangle failed: " + e.getMessage());
        }
    }
}
