package liskov_substitution.example2.problem;

/** resize() is correct for every real Rectangle but gives a surprising answer for a Square. */
public class Main {

    public static void main(String[] args) {
        check(new Rectangle());
        check(new Square());
    }

    // Written against Rectangle only: width 5 x height 4 must be 20.
    static void check(Rectangle r) {
        r.setWidth(5);
        r.setHeight(4);
        int expected = 5 * 4;
        String result = r.getArea() == expected ? "OK" : "BROKEN";
        System.out.println(r.getClass().getSimpleName() + ": expected " + expected
                + ", got " + r.getArea() + " -> " + result);
    }
}
