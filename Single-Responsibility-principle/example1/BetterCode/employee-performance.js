// Single responsibility: performance reporting. Changes only when the report changes.
class EmployeePerformance {
  printPerformanceReport(employee) {
    // Code to print performance report
    console.log("Performance report of employee: " + employee.name);
  }
}

module.exports = EmployeePerformance;
