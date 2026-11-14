package single_responsibility.example6.better;

/** Only job: fine maths. Pure function, no side effects, trivial to unit test. */
public class FineCalculator {
    private final double perDay;

    public FineCalculator(double perDay) {
        this.perDay = perDay;
    }

    public double fineFor(int daysLate) {
        return Math.max(0, daysLate) * perDay;
    }
}
