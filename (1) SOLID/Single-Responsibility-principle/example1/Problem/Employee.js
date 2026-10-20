class Employee {
  constructor(id, name, address) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.department = undefined;
  }
  printPerformanceReport() {
    // Code to print performance report
    //
    console.log("Performance report of employee: " + this.name);
  }
  computeSalary() {
    // Code to compute salary
    // if the Tax slab changes we have t update this function
    return 1000.0;
  }
  updateEmployeeData() {
    // Code to update employee data
    // If the data storage requirements change we might need to update this function
    console.log("Employee data updated successfully");
  }
  fetchEmployeeData() {
    console.log("employee detail here ");
  }
}
// Because the above class is doing too many things there are too many response to update the code present in the class
// Single responsibility principle states that there should one and only one reason to change a piece of code
// It can be applicicbale for the functional code as well
