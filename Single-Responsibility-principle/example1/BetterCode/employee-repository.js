// Single responsibility: employee persistence. Changes only when data storage changes.
class EmployeeRepository {
  updateEmployeeData(employee) {
    // Code to update employee data
    console.log("Employee data updated successfully");
  }

  fetchEmployeeData(id) {
    console.log("employee detail here ");
  }
}

module.exports = EmployeeRepository;
