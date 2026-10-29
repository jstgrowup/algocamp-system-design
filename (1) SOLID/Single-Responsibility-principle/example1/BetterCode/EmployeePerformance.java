package solid.srp.example1.better;

/**
 * Single responsibility: performance reporting.
 * Changes only when the report changes.
 */
public class EmployeePerformance {

    public void printPerformanceReport(Employee employee) {
        // Code to print performance report
        System.out.println("Performance report of employee: " + employee.getEmployeeName());
    }
}
