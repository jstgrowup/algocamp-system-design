// Expense.java
// Ideally made using builder pattern
//
// An expense is just data: it holds values and knows no rules.
// Java's `private final` fields + getters become JS private fields (#) + getters,
// so `expense.expenseId` reads the value and nothing can reassign it.
class Expense {
  #expenseId;
  #tripId;
  #amountUsd;
  #expenseType;

  constructor(expenseId, tripId, amountUsd, expenseType) {
    this.#expenseId = expenseId;
    this.#tripId = tripId;
    this.#amountUsd = amountUsd;
    this.#expenseType = expenseType;
  }

  get expenseId() {
    return this.#expenseId;
  }

  get tripId() {
    return this.#tripId;
  }

  get amountUsd() {
    return this.#amountUsd;
  }

  get expenseType() {
    return this.#expenseType;
  }
}

module.exports = { Expense };
