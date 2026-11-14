package strategy.example4.better;

/** Added later: pass holders park free. Nothing existing was edited. */
public class MonthlyPassPricing implements PricingStrategy {
    @Override
    public double fee(VehicleType vehicle, int hours) {
        return 0;
    }
}
