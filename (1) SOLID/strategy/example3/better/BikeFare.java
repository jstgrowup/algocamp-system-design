package strategy.example3.better;

public class BikeFare implements FareStrategy {
    @Override
    public double fare(double km, int waitMinutes) {
        return 10 + km * 6;
    }
}
