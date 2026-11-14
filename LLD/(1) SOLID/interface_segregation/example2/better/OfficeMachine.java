package interface_segregation.example2.better;

/** Composes the small interfaces it supports; adding a new capability later does not touch BasicPrinter. */
public class OfficeMachine implements Printer, Scanner, Fax, Stapler {

    @Override
    public void print(String doc) {
        System.out.println("OfficeMachine prints " + doc);
    }

    @Override
    public void scan(String doc) {
        System.out.println("OfficeMachine scans " + doc);
    }

    @Override
    public void fax(String doc) {
        System.out.println("OfficeMachine faxes " + doc);
    }

    @Override
    public void staple(String doc) {
        System.out.println("OfficeMachine staples " + doc);
    }
}
