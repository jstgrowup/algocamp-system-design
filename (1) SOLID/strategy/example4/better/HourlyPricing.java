package strategy.example4.better;

public class HourlyPricing implements PricingStrategy {
    @Override
    public double fee(VehicleType vehicle, int hours) {
        double rate = vehicle == VehicleType.CAR ? 20 : 10;
        return rate * hours;
    }
}
