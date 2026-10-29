package solid.srp.example1.better;

/**
 * Single responsibility: hold employee data. Nothing else.
 *
 * Salary, reports and storage now live in their own classes, so every class has
 * exactly one reason to change.
 */
public class Employee {

    private final int id;
    private final String name;
    private final String address;
    private String department;

    public Employee(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getEmployeeId() {
        return id;
    }

    public String getEmployeeName() {
        return name;
    }

    public String getEmployeeAddress() {
        return address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
