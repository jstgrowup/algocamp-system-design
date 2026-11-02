package single_responsibility.example5.problem;

/** Runner: shows why one class with three jobs hurts. */
public class Main {

    public static void main(String[] args) {
        Invoice invoice = new Invoice("Asha");
        invoice.addItem(new LineItem("Keyboard", 1, 1000));
        invoice.addItem(new LineItem("Mouse", 2, 250));

        invoice.print();
        invoice.saveToDatabase();

        // The pain:
        //  - Tax rule changes (GST 18% -> 12%)?   edit Invoice.calculateTotal()
        //  - Need a PDF instead of console print? edit Invoice.print()
        //  - Switch from SQL to MongoDB?          edit Invoice.saveToDatabase()
        // Three unrelated changes all touch the same file -> merge conflicts and regression risk.
        // Also: you cannot unit test the tax maths without dragging printing and DB code along.
    }
}
