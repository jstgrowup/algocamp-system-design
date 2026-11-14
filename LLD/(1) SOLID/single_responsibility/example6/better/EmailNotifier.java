package single_responsibility.example6.better;

/** Only job: sending messages. Swapping to SMS later means a new class, not an edit of the rules. */
public class EmailNotifier {

    public void notify(Member member, String body) {
        // pretend this is SMTP code
        System.out.println("EMAIL to " + member.getEmail() + ": " + body);
    }
}
