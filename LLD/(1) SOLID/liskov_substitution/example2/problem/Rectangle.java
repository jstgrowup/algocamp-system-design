package liskov_substitution.example2.problem;

/** Mutable rectangle. Implicit contract: setWidth() changes ONLY the width, setHeight() ONLY the height. */
public class Rectangle {

    protected int width;
    protected int height;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }
}
