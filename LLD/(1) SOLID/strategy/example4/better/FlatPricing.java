package strategy.example4.better;

/** One flat fee per day, no matter how many hours. */
public class FlatPricing implements PricingStrategy {
    @Override
    public double fee(VehicleType vehicle, int hours) {
        return vehicle == VehicleType.CAR ? 100 : 50;
    }
}
