// impl/DisallowRule.java
// Rejects an expense just for being of a certain type (the registry decides which types).
const { ExpenseRule } = require("../rules/ExpenseRule");
const { Violation } = require("../rules/violation");

class DisallowRule extends ExpenseRule {
  // @Override
  check(expense) {
    return Violation.of("Expense Type " + expense.expenseType);
  }
}

module.exports = { DisallowRule };
