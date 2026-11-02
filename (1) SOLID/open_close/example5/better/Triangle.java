package open_close.example5.better;

/** New shape = new file. Nothing that already exists is modified (open for extension). */
public record Triangle(double base, double height) implements Shape {
    @Override
    public double area() {
        return 0.5 * base * height;
    }
}
