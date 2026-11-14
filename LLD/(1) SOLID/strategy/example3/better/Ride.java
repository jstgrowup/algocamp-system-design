package strategy.example3.better;

/** Context: a ride is priced by whichever FareStrategy it was booked with. */
public class Ride {

    private final double km;
    private final int waitMinutes;
    private final FareStrategy fareStrategy;

    public Ride(double km, int waitMinutes, FareStrategy fareStrategy) {
        this.km = km;
        this.waitMinutes = waitMinutes;
        this.fareStrategy = fareStrategy;
    }

    public double fare() {
        return fareStrategy.fare(km, waitMinutes);
    }
}
