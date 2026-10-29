// rules/TripRule.java
// public interface TripRule { Optional<List<Violation>> check(List<Expense> expenses); }
//
// A rule that looks at all the expenses of a trip together. JS has no interfaces,
// so this is a base class whose method throws until a rule overrides it.
// Optional<List<Violation>> becomes: an array of Violations when the trip breaks
// the rule, or null when it doesn't.
class TripRule {
  check(expenses) {
    throw new Error("abstract");
  }
}

module.exports = { TripRule };
