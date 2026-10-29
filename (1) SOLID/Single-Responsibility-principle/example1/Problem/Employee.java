package solid.srp.example1.problem;

/**
 * PROBLEM: a "monster class" that does too many things.
 *
 * This one class is responsible for:
 *   1. holding employee data
 *   2. printing the performance report
 *   3. computing the salary
 *   4. saving / loading the employee (storage)
 *
 * Single Responsibility Principle (SRP): a class should have one, and only one,
 * reason to change. Here there are four reasons, so a change in any one of them
 * means editing this class.
 */
public class Employee {

    private int id;
    private String name;
    private String address;
    private String department; // not set by the constructor, like the Node version

    public Employee(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    // Reason to change #1: the report format changes
    public void printPerformanceReport() {
        // Code to print performance report
        System.out.println("Performance report of employee: " + name);
    }

    // Reason to change #2: the tax slab / pay rules change
    public double computeSalary() {
        // Code to compute salary
        return 1000.0;
    }

    // Reason to change #3: the data storage requirements change
    public void updateEmployeeData() {
        // Code to update employee data
        System.out.println("Employee data updated successfully");
    }

    // Reason to change #3 again (storage)
    public void fetchEmployeeData() {
        System.out.println("employee detail here ");
    }

    // Because the class does too many things, there are too many reasons to update it.
    // SRP can be applied to functional code as well, not only to classes.
}
