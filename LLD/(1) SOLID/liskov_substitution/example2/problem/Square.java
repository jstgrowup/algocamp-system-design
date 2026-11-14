package liskov_substitution.example2.problem;

/**
 * "A square is a rectangle" in maths, so we extend it. To keep the square valid
 * we make both setters change both sides, which breaks the Rectangle contract
 * (postcondition of setWidth: height is unchanged) -> LSP violation.
 */
public class Square extends Rectangle {

    @Override
    public void setWidth(int width) {
        this.width = width;
        this.height = width;
    }

    @Override
    public void setHeight(int height) {
        this.width = height;
        this.height = height;
    }
}
