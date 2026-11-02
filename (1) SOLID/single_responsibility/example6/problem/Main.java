package single_responsibility.example6.problem;

/** Runner: shows what you must touch when a requirement changes. */
public class Main {

    public static void main(String[] args) {
        LibraryService library = new LibraryService();
        Member member = new Member("Ravi", "ravi@example.com");

        library.borrow(member, "Clean Code");
        library.borrow(member, "Refactoring");
        library.giveBack(member, "Clean Code", 5);

        // The pain:
        //  - Limit changes 3 -> 5 for premium members?  edit LibraryService.borrow()
        //  - Fine becomes 5/day, capped at 100?         edit LibraryService.giveBack()
        //  - Switch email to SMS or push?               edit LibraryService.sendEmail()
        //  - Unit testing the fine maths means dealing with the e-mail side effect.
    }
}
