package single_responsibility.example6.better;

/** Runner: build the collaborators once, then each rule can change independently. */
public class Main {

    public static void main(String[] args) {
        LibraryService library = new LibraryService(new BorrowingPolicy(3), new FineCalculator(2.0), new EmailNotifier());
        Member member = new Member("Ravi", "ravi@example.com");

        library.borrow(member, "Clean Code");
        library.borrow(member, "Refactoring");
        library.giveBack(member, "Clean Code", 5);

        // Limit to 5? new BorrowingPolicy(5). Different fine? new FineCalculator(5.0). SMS? new notifier class.
    }
}
