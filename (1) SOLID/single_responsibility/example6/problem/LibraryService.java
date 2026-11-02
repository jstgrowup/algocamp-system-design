package single_responsibility.example6.problem;

/**
 * PROBLEM: one class mixes three unrelated jobs.
 *  1. borrowing rules   (max books)        -> library policy
 *  2. fine calculation  (per-day charge)   -> finance
 *  3. notifications     (email sending)    -> communication / infra
 * Violates the Single Responsibility Principle.
 */
public class LibraryService {

    // Responsibility 1: borrowing rules
    public void borrow(Member member, String book) {
        if (member.getActiveLoans() >= 3) {
            throw new IllegalStateException(member.getName() + " already has 3 books");
        }
        member.addLoan();
        sendEmail(member, "You borrowed '" + book + "'"); // service also talks to the mail system
    }

    // Responsibility 2: fine calculation
    public void giveBack(Member member, String book, int daysLate) {
        member.removeLoan();
        double fine = daysLate * 2.0; // 2 per day, hard-coded
        sendEmail(member, "You returned '" + book + "'. Fine: " + fine);
    }

    // Responsibility 3: notifications
    private void sendEmail(Member member, String body) {
        // pretend this is SMTP code
        System.out.println("EMAIL to " + member.getEmail() + ": " + body);
    }
}
