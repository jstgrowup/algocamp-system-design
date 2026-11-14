package strategy.example3.problem;

/**
 * PROBLEM: one switch holds every vehicle's pricing.
 * Add "Luxury" or "EV cab" -> edit this class (OCP), and every fare
 * change risks breaking the others (SRP).
 */
public class FareCalculator {

    public double fare(VehicleType type, double km, int waitMinutes) {
        switch (type) {
            case BIKE:
                return 10 + km * 6;                         // base + per km
            case AUTO:
                return 25 + km * 11 + waitMinutes * 1;      // + waiting charge
            case CAB:
                return Math.max(80, 40 + km * 15 + waitMinutes * 2); // minimum fare 80
            default:
                throw new IllegalArgumentException("Unknown vehicle: " + type);
        }
    }
}
