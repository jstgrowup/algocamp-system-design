We have to create an expense Policy rules engine
you are tasked with designning a system that evaluates Business expenses submitted by employees
managers can define policies to control expenses ensuring employees do not misuse corporate cards or exceed allowances
your goal is to build a rules engine that :

1. Evaluates individual expensees against a set of rules
2. Evaluates aggregated trip-level expenses againts a set of rules
3. Flags any violations clearly

`Dev Comments`-------

- `----------------`
  A List of xepenses where each expense is represented as a dictionary/map of string keys and values

- expense_id
- trip_id
- amount_usd
- expense_type (restaurant,entertainment)
- verndor_type
  A list of evaluate . Rules can be applied at

1. Expense level
2. Trip level

Basic Rules

1. No Restaurant expense can exceed $75
2. No airfare expenses are allowed
3. No entertainment expenses are allowed
4. No sinfle expense can exceed $250

Extended Rules

1. A trip cannot exceed $2000 in total expenses
2. Total meal expenses per trip cannot exceed $1000

Output Format

1. For each expenses return whether it is APPROVED or REJECTED with reasons for rejection
2. For each trip return whether it is OK or has VIOLATIONS with reasons

it should be extendible the code should be extendible

will be getting hte list of expenses
basic expectation is just lets take the list of expenses and will filter the expenses

the problem with the filter is we do like
for(expense of expenses ){
if(e follows)
else if (..)
else if (...)
}
this piece of code actually violates the OCP the open and closed priciple and tomorrow if we have more rules than we have to update this piece of code which is not good
and one rule can be very complex as well
because implementation of rule logic
so we are not following OCP and SRP both in the same time

Thought processes

1. Expense type --> Define a rule for each expense type

- Problem with this approach the same logic is that same loogic can be there for the different expense types
  thre will be many code duplicacy issue
  example - The rule can be applied on multiple expenses hence we should not tightly couple a rule to an expense

Just because we have different expense types doesnt. make sense to have different classes for each of the expense types

The rule can be applied on multiple expenses hence we cant tightly couple the rule to an expense at all

2. Having multiple expense classes at all
3. When we make a ecommerce app

Solution Will be having two interfaces Expense and the Rules
LEts say we have EntExpense , AirfareExpense and restaurantExpense
adn in the Rule we have Rule 1 , Rule 2, Rule 3
Now the EntExpense will be has the Rule 1 , Rule 2 this
the base class cant implement the Rule class
this is called strategy pattern

now having multiple expense classes is not good exactly
if there is any changes in the rule we change the rule strategy

The main issue is Testing because if there are 10 classes we have to write 10 tests and more tests and if we change one we have to change the Tests again

- We can have a Expense Rule Engine where we are passing the amount and the

models/Expense.java

<!-- Ideally made using builder pattern -->

public class Expense {
private final String expenseId
private final String tripId
private final Double amountUsd
private final ExpenseType expenseTyp
public Expense(string expenseId, String tripId , Double amountUsd, ExpenseType expenseType){
this.expenseId=expenseId
this.tripId=tripId
this.amountUsd=amoiuntUsd
this.expenseType=expenseType
}
public string getExpenseId(){
return expenseId
}
public String getTripId(){
return tripId
}
public Double getAmountUsd(){
return amountUsd
}
public ExpenseType getExpenseType(){
return expenseType
}

}

rules/ExpenseRule.java
public interface ExpenseRule{
Optional<Violation> check (Expense e)
}

rules/violation.java
public class Violation {
private final String message
private Violation (String message ){
thus.message = message
}
public static Violation of (String message){
return new Violation (message )
}
}
rules/TripRule.java
public interface TripRule {
Optional<List<Violation>> check (List<EXpense> expenses)
}

impl/DisallowRule.java
public class DisallowRule implements ExpenseRule {
@Override
public Optional<Violation> check (Expense e){
return Optional.of(Violation.of("Expense Type"+e.getExpenseType))
}
}

registry/RuleRegistry.java
public class RuleRegistry{
public static void getExpenseRuleRegistry(){
Map<ExpenseType ,List<ExpenseRUle>> registry = new HashMap();
registry.put(ExpenseType.Restaurant,List.of)(new DisalllowRule,new MaxAmountRule(maxAmount:75))
registry.put(ExpenseType.AIRFRAME,List.if(new DisallowRule))
}
public static void getAllExpenseRulesRegistry(){
return List.of(new MaxAmountRule(maxAmount:200))
}
}

impl/MaxAmountRule.java

public class MaxAMount implements ExpenseRule{
private final double maxAmount;
public MAxAmoiuntRule(double maxAmount){
this.maxAmount=maxAmount
}
@Override
public Optional<Violation> check (Expense e){
if(e.getAmountUsd()>maxAmount){
return Optional.of(Violation.of(message:"Expense amount is greater than teh max amount"))
}
return Optional.empty()
}
}
