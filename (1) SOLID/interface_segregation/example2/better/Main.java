package interface_segregation.example2.better;

/** Clients depend only on the capability they use. */
public class Main {

    public static void main(String[] args) {
        BasicPrinter basic = new BasicPrinter();
        OfficeMachine office = new OfficeMachine();

        printReport(basic);
        printReport(office);
        // scanContract(basic); // does not compile: BasicPrinter is not a Scanner (error caught early)
        scanContract(office);
    }

    static void printReport(Printer printer) {
        printer.print("report.pdf");
    }

    static void scanContract(Scanner scanner) {
        scanner.scan("contract.pdf");
    }
}
