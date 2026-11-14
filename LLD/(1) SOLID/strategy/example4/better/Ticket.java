package strategy.example4.better;

/** Context: a ticket is given a pricing strategy and asks it for the fee on exit. */
public class Ticket {

    private final VehicleType vehicle;
    private PricingStrategy pricing;

    public Ticket(VehicleType vehicle, PricingStrategy pricing) {
        this.vehicle = vehicle;
        this.pricing = pricing;
    }

    // Swap at runtime, e.g. the customer shows a monthly pass at the exit gate.
    public void setPricing(PricingStrategy pricing) {
        this.pricing = pricing;
    }

    public double close(int hours) {
        return pricing.fee(vehicle, hours);
    }
}
