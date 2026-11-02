package interface_segregation.example2.problem;

import java.util.List;

/** The caller treats every Machine the same, so BasicPrinter blows up or lies. */
public class Main {

    public static void main(String[] args) {
        for (Machine machine : List.<Machine>of(new OfficeMachine(), new BasicPrinter())) {
            String name = machine.getClass().getSimpleName();
            try {
                machine.print("report.pdf");
                machine.scan("contract.pdf");
                machine.fax("invoice.pdf");
                machine.staple("report.pdf");
                System.out.println(name + " finished all four operations");
            } catch (UnsupportedOperationException e) {
                System.out.println(name + " failed: " + e.getMessage());
            }
        }
    }
}
