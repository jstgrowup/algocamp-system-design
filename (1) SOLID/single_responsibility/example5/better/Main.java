package single_responsibility.example5.better;

/** Runner: each class has one reason to change; Main just wires them together. */
public class Main {

    public static void main(String[] args) {
        Invoice invoice = new Invoice("Asha");
        invoice.addItem(new LineItem("Keyboard", 1, 1000));
        invoice.addItem(new LineItem("Mouse", 2, 250));

        double total = new InvoiceCalculator(0.18).total(invoice);
        new InvoicePrinter().print(invoice, total);
        new InvoiceRepository().save(invoice, total);

        // Tax change -> only InvoiceCalculator. New layout -> only InvoicePrinter. New DB -> only InvoiceRepository.
    }
}
