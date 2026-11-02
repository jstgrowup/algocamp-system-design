package single_responsibility.example5.better;

/** Only job: business maths. Changes when tax/discount rules change (finance team). */
public class InvoiceCalculator {
    private final double taxRate;

    public InvoiceCalculator(double taxRate) {
        this.taxRate = taxRate; // injected, so tests and other countries can use a different rate
    }

    public double total(Invoice invoice) {
        double subtotal = 0;
        for (LineItem item : invoice.getItems()) {
            subtotal += item.quantity() * item.unitPrice();
        }
        return subtotal * (1 + taxRate);
    }
}
