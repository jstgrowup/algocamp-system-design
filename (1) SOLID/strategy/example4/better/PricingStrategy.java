package strategy.example4.better;

/** Strategy interface: how to turn (vehicle, hours) into a fee. */
public interface PricingStrategy {
    double fee(VehicleType vehicle, int hours);
}
