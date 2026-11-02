package liskov_substitution.example2.better;

/** Sibling of Rectangle, not a child: it has its own invariant (one side) and promises nothing extra. */
public class Square implements Shape {

    private final int side;

    public Square(int side) {
        this.side = side;
    }

    @Override
    public int area() {
        return side * side;
    }
}
