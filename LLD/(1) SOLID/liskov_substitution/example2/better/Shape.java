package liskov_substitution.example2.better;

/** The only thing callers can rely on for every shape. No setters, so nothing to contradict. */
public interface Shape {

    int area();
}
