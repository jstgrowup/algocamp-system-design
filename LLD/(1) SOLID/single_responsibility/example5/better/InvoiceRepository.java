package single_responsibility.example5.better;

/** Only job: persistence. Changes when the storage technology changes (DBA / infra team). */
public class InvoiceRepository {

    public void save(Invoice invoice, double total) {
        // pretend this is JDBC code
        System.out.println("INSERT INTO invoices (customer, total) VALUES ('" + invoice.getCustomer() + "', " + total + ")");
    }
}
