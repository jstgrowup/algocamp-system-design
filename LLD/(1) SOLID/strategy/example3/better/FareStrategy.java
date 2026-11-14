package strategy.example3.better;

/** Strategy interface: how to price one ride. */
public interface FareStrategy {
    double fare(double km, int waitMinutes);
}
