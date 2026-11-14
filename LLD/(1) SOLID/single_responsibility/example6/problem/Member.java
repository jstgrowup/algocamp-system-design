package single_responsibility.example6.problem;

/** A library member: plain data plus a loan counter. */
public class Member {
    private final String name;
    private final String email;
    private int activeLoans;

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getActiveLoans() { return activeLoans; }
    public void addLoan() { activeLoans++; }
    public void removeLoan() { activeLoans--; }
}
