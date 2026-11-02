package strategy.example4.problem;

/**
 * PROBLEM: the lot computes fees itself with nested if/else on pricing type
 * AND vehicle type. A new scheme (say "monthly pass") = edit here (OCP),
 * and the lot mixes slot management with money rules (SRP).
 */
public class ParkingLot {

    public double fee(PricingType pricing, VehicleType vehicle, int hours) {
        double rate = vehicle == VehicleType.CAR ? 20 : 10; // per hour
        if (pricing == PricingType.HOURLY) {
            return rate * hours;
        } else if (pricing == PricingType.FLAT) {
            return vehicle == VehicleType.CAR ? 100 : 50;   // flat per day
        } else if (pricing == PricingType.WEEKEND) {
            return rate * hours * 1.5;                      // 50% surcharge
        }
        throw new IllegalArgumentException("Unknown pricing: " + pricing);
    }
}
