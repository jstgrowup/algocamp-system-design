package strategy.example3.better;

public class AutoFare implements FareStrategy {
    @Override
    public double fare(double km, int waitMinutes) {
        return 25 + km * 11 + waitMinutes * 1;
    }
}
