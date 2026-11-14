// Every Java program starts at `public static void main`. Run this class to run the program.
// Java comes from a typed, class-based world, so we first look at WHY classes exist:
// without them we need one parallel array per attribute.
public class Main {
    public static void main(String[] args) {
        // Storing 5 students WITHOUT a class: one array per attribute.
        // Index i across all arrays is "student i" - nothing in the code enforces that.
        int[] rollNumbers = new int[5];
        String[] names = new String[5];
        String[] marks = new String[5];
        // Problem: adding a new attribute means adding another array and keeping them in sync.
        // A class bundles the attributes into one blueprint (see Student below).
        Student s = new Student();
        s.name = "Aditya";
        s.rollNumber = 1;
        System.out.println(s.name + " has roll number " + s.rollNumber);
    }

    // A class is a blueprint, like a Car design that needs an engine and seats.
    // From one blueprint we create many objects (a Maruti, a Ferrari, a Porsche).
    // This is a static nested class so main can create it directly with `new Student()`.
    static class Student {
        String name;
        int rollNumber;
        float marks;
    }
}
