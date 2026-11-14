package single_responsibility.example5.problem;

import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Invoice has THREE reasons to change:
 *  1. business rules  (tax / discount maths)      -> finance team
 *  2. presentation    (how the invoice is printed) -> design / UX team
 *  3. persistence     (where it is stored)         -> DBA / infra team
 * That breaks the Single Responsibility Principle.
 */
public class Invoice {
    private final String customer;
    private final List<LineItem> items = new ArrayList<>();

    public Invoice(String customer) {
        this.customer = customer;
    }

    public void addItem(LineItem item) {
        items.add(item);
    }

    // Responsibility 1: calculation
    public double calculateTotal() {
        double subtotal = 0;
        for (LineItem item : items) {
            subtotal += item.quantity() * item.unitPrice();
        }
        return subtotal * 1.18; // 18% tax hard-coded inside the data class
    }

    // Responsibility 2: presentation
    public void print() {
        System.out.println("Invoice for " + customer);
        for (LineItem item : items) {
            System.out.println("  " + item.name() + " x" + item.quantity() + " @ " + item.unitPrice());
        }
        System.out.println("  TOTAL (with tax): " + calculateTotal());
    }

    // Responsibility 3: persistence
    public void saveToDatabase() {
        // pretend this is JDBC code
        System.out.println("INSERT INTO invoices (customer, total) VALUES ('" + customer + "', " + calculateTotal() + ")");
    }
}
