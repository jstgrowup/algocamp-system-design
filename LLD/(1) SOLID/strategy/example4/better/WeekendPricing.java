package strategy.example4.better;

/** Hourly pricing with a 50% weekend surcharge. Reuses HourlyPricing (composition). */
public class WeekendPricing implements PricingStrategy {

    private final PricingStrategy base = new HourlyPricing();

    @Override
    public double fee(VehicleType vehicle, int hours) {
        return base.fee(vehicle, hours) * 1.5;
    }
}
