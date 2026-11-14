package single_responsibility.example6.better;

/** Only job: the borrowing rules. Changes when library policy changes. */
public class BorrowingPolicy {
    private final int maxBooks;

    public BorrowingPolicy(int maxBooks) {
        this.maxBooks = maxBooks;
    }

    public boolean canBorrow(Member member) {
        return member.getActiveLoans() < maxBooks;
    }
}
