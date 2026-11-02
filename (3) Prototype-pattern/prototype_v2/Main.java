package prototype_v2;

public class Main {
    public static void main(String[] args) {
        // 1. create an Email with the normal constructor
        Email e1 = new Email("a@x.com", "b@y.com", "Hello", "Welcome!");

        // 2. make a copy with the copy constructor
        Email e2 = e1.copy();
        e2.displayEmail();

        // 3. same idea with the child class: create p1, then p2 with p1.copy()
        PremiumEmail p1 = new PremiumEmail("a@x.com", "b@y.com", "Hello", "Welcome!", "cc@x.com", "bcc@x.com");
        PremiumEmail p2 = p1.copy(); // copy() returns a PremiumEmail, so no cast is needed
        System.out.println("p2 cc = " + p2.getCc() + ", bcc = " + p2.getBcc());
    }
}
