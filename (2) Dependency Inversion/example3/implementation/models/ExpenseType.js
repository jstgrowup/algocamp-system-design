// ExpenseType.java (enum)
// JS has no enum keyword, so a frozen object does the job: ExpenseType.RESTAURANT
// gives "RESTAURANT", and Object.freeze stops anyone adding or changing types at runtime.
const ExpenseType = Object.freeze({
  RESTAURANT: "RESTAURANT",
  AIRFARE: "AIRFARE",
  ENTERTAINMENT: "ENTERTAINMENT",
});

module.exports = { ExpenseType };
