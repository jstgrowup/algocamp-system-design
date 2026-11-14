package strategy.example3.better;

public class CabFare implements FareStrategy {
    @Override
    public double fare(double km, int waitMinutes) {
        return Math.max(80, 40 + km * 15 + waitMinutes * 2); // minimum fare 80
    }
}
