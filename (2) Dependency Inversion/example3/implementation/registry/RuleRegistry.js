// registry/RuleRegistry.java
// Says which rules apply to which expense type, and which apply to every expense.
const { ExpenseType } = require("../models/ExpenseType");
const { DisallowRule } = require("../impl/DisallowRule");
const { MaxAmountRule } = require("../impl/MaxAmountRule");

class RuleRegistry {
  // Map<ExpenseType, List<ExpenseRule>>
  static getExpenseRuleRegistry() {
    const registry = new Map();
    registry.set(ExpenseType.RESTAURANT, [new DisallowRule(), new MaxAmountRule(75)]);
    registry.set(ExpenseType.AIRFARE, [new DisallowRule()]);
    return registry;
  }

  // Rules applied to every expense, whatever its type
  static getAllExpenseRulesRegistry() {
    return [new MaxAmountRule(200)];
  }
}

module.exports = { RuleRegistry };
