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
