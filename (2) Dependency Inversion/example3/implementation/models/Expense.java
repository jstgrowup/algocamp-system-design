package di.example3.models;

/**
 * An expense is just data: it holds values and knows no rules.
 * The fields are `private final`, so once an Expense is created it can't change,
 * and the getters are the only way to read it.
 *
 * Ideally made using the builder pattern (an Expense has several fields of
 * different types, so a builder avoids a long constructor call).
 */
public class Expense {

    private final String expenseId;
    private final String tripId;
    private final Double amountUsd;
    private final ExpenseType expenseType;

    public Expense(String expenseId, String tripId, Double amountUsd, ExpenseType expenseType) {
        this.expenseId = expenseId;
        this.tripId = tripId;
        this.amountUsd = amountUsd;
        this.expenseType = expenseType;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public String getTripId() {
        return tripId;
    }

    public Double getAmountUsd() {
        return amountUsd;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }
}
