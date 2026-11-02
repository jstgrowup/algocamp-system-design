package strategy.example3.better;

/** Added later: a new vehicle type is just a new class, nothing else is edited. */
public class LuxuryFare implements FareStrategy {
    @Override
    public double fare(double km, int waitMinutes) {
        return 150 + km * 30 + waitMinutes * 5;
    }
}
