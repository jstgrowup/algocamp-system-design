public class Main {
    public static void main(String[] args) {
        // 1. create an Email with the normal constructor
        Email e1 = new Email("a@x.com", "b@y.com", "Hello", "Welcome!");

        // 2. make a copy with the copy constructor
        Email e2 = new Email(e1);
        e2.displayEmail();

        // 3. change only the copy: the original must stay the same
        e2.setSubject("Hello from the copy");
        e1.displayEmail();
        e2.displayEmail();
    }
}
