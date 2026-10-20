// Single responsibility: hold employee data. Nothing else.
class Employee {
  constructor(id, name, address) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.department = undefined;
  }
}

module.exports = Employee;
// We have segregated the classes in a way such that their is one core responsibility every class has hence ther eis only one reason to change the logic in a class
