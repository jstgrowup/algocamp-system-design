package single_responsibility.example5.better;

import java.util.ArrayList;
import java.util.List;

/** BETTER: Invoice only holds invoice data. It changes only if the data shape changes. */
public class Invoice {
    private final String customer;
    private final List<LineItem> items = new ArrayList<>();

    public Invoice(String customer) {
        this.customer = customer;
    }

    public void addItem(LineItem item) {
        items.add(item);
    }

    public String getCustomer() {
        return customer;
    }

    public List<LineItem> getItems() {
        return List.copyOf(items); // read-only view, callers cannot corrupt our list
    }
}
