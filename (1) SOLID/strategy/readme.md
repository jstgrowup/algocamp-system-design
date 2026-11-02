1. Issue is with the code duplication lets say we have the same refund logic for two cards than what ?

- Master card and lets say Rupay has the same refund logic
- Strategy Pattern is the answer

Problems Walkthrough

- If we want to add teh UPI method in the Credit card class but not all teh credit cards supports the UPI
- Rest of the methods will be overridden
- If tomorrow tjust like rupay we want the upimethod() in the mastercard will be duplicating the functions
- This is wrong
- This is a common copy paste logic
- Similarly we have refund and lets say Visa supports refund and all the cards supports refund only Diners doesnt
- we cant put hte method in the base abstract class as well because in that case we need to again override in all the places and diner will through an exception
- Soln:
- Will be having an interface (class) for the UPICompatible and another RefundCompatible so now the Rupay will implement the UPICompatible inteface and similarly for the Visa they will implement the Refund compatible interface
- Problem again dorefund() cant be very very different and there will be abstraction in the refund as well there will be
- Let the refund logic in. a credit card can be custom and can be having diff possible implementations but things become more interesting if we consider that there is a group of CC whoch support one type of refund algo and then there is another group of CC which support a different algo
- Lets say the Visa and mastercard they support the same instrument refund and the amex and rupay supports wallet refund
- the dorefund method in visa and mastercard class will be end to end same and the dorefund method in rupay and amex class will be end to end same . why ? because visa and mastercard supports exact same algo and rupay as well as amex support exact same algo
- This is a classic case of code duplication
- The most fundamental way to resolve is by writing functions
- Because functions help us to implement DRY principle
- Now where should that common function reside ?
- but the issue is that in Java we have to write this in some Class
- we cant write in a visa or mastercard or any card class
- So the ideal way is that we put these functions into the
- Classes can represent behaiviours
- There will be two seperate classes one for the
