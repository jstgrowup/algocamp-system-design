package solid.srp.example1.better;

/**
 * Single responsibility: employee persistence (storage).
 * Changes only when the way we store data changes.
 */
public class EmployeeRepository {

    public void updateEmployeeData(Employee employee) {
        // Code to update employee data
        System.out.println("Employee data updated successfully");
    }

    public void fetchEmployeeData(int id) {
        System.out.println("employee detail here ");
    }
}
