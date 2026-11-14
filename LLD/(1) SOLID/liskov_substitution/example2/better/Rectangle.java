package liskov_substitution.example2.better;

/** Immutable: width and height are fixed at construction, so no setter contract can be broken. */
public class Rectangle implements Shape {

    private final int width;
    private final int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int area() {
        return width * height;
    }
}
