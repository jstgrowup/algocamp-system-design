// impl/MaxAmountRule.java
// Rejects an expense that is more than the allowed amount.
const { ExpenseRule } = require("../rules/ExpenseRule");
const { Violation } = require("../rules/violation");

class MaxAmountRule extends ExpenseRule {
  #maxAmount;

  constructor(maxAmount) {
    super();
    this.#maxAmount = maxAmount;
  }

  // @Override
  check(expense) {
    if (expense.amountUsd > this.#maxAmount) {
      return Violation.of("Expense amount is greater than the max amount");
    }
    return null; // Optional.empty()
  }
}

module.exports = { MaxAmountRule };
