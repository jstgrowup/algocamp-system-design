package single_responsibility.example5.better;

/** Only job: presentation. Changes when the layout changes (a PDF printer would be a sibling class). */
public class InvoicePrinter {

    public void print(Invoice invoice, double total) {
        System.out.println("Invoice for " + invoice.getCustomer());
        for (LineItem item : invoice.getItems()) {
            System.out.println("  " + item.name() + " x" + item.quantity() + " @ " + item.unitPrice());
        }
        System.out.println("  TOTAL (with tax): " + total);
    }
}
