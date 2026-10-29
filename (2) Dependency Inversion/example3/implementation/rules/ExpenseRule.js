// rules/ExpenseRule.java
// public interface ExpenseRule { Optional<Violation> check(Expense e); }
//
// JS has no interfaces, so this is a base class whose method throws until a
// rule overrides it. JS also has no Optional: check() returns a Violation when
// the rule is broken, and null when the expense follows the rule.
class ExpenseRule {
  check(expense) {
    throw new Error("abstract");
  }
}

module.exports = { ExpenseRule };
