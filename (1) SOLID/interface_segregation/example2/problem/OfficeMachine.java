package interface_segregation.example2.problem;

/** A real multifunction machine: needs everything in Machine, so no problem here. */
public class OfficeMachine implements Machine {

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
