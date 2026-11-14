package single_responsibility.example6.better;

/**
 * BETTER: LibraryService only coordinates the workflow (borrow/return).
 * The rules, the maths and the mail are delegated to collaborators passed in via the constructor.
 */
public class LibraryService {
    private final BorrowingPolicy policy;
    private final FineCalculator fines;
    private final EmailNotifier notifier;

    public LibraryService(BorrowingPolicy policy, FineCalculator fines, EmailNotifier notifier) {
        this.policy = policy;
        this.fines = fines;
        this.notifier = notifier;
    }

    public void borrow(Member member, String book) {
        if (!policy.canBorrow(member)) {
            throw new IllegalStateException(member.getName() + " reached the borrowing limit");
        }
        member.addLoan();
        notifier.notify(member, "You borrowed '" + book + "'");
    }

    public void giveBack(Member member, String book, int daysLate) {
        member.removeLoan();
        notifier.notify(member, "You returned '" + book + "'. Fine: " + fines.fineFor(daysLate));
    }
}
