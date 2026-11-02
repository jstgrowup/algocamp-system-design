# SOLID so far (SRP, OCP, LSP, ISP) and the Strategy Pattern, in Java

Notes for the topics covered so far in the course. All examples are in Java.

## What's covered

| Topic | Type | Status |
|---|---|---|
| **S** - Single Responsibility Principle (SRP) | SOLID principle | Covered |
| **O** - Open/Closed Principle (OCP) | SOLID principle | Covered |
| **L** - Liskov Substitution Principle (LSP) | SOLID principle | Covered |
| **I** - Interface Segregation Principle (ISP) | SOLID principle | Covered |
| **D** - Dependency Inversion Principle (DIP) | SOLID principle | Started, see [(2) Dependency Inversion](<../(2) Dependency Inversion/>) |
| **Strategy** | Design pattern | Covered (the first design pattern in the course) |

Strategy is the only design pattern covered in this part. Later patterns: [(3) Prototype-pattern](<../(3) Prototype-pattern/>) and [(4) Builder-pattern](<../(4) Builder-pattern/>) (folders `builder_v1`..`builder_v6`).

## The big picture

| | One-line meaning | The smell that tells you it's broken |
|---|---|---|
| **SRP** | A class has one, and only one, reason to change. | A "monster class" that does many unrelated jobs. |
| **OCP** | Add new behaviour by adding code, not by editing working code. | An `if/else` or `switch` on a type that grows with every new type. |
| **LSP** | A child must work anywhere its parent is used. | A child that throws, or breaks a rule the parent promised. |
| **ISP** | Don't force a class to have methods it doesn't need. | A method that just throws `"not supported"`, or an empty method. |
| **Strategy** | Put each way of doing a job in its own class and swap them. | The same logic copy-pasted in many classes. |

How the course connects them:

```
SRP  ->  "SRP is not always enough"  ->  OCP  ->  LSP (inheritance must be safe)
                                                    ->  ISP (don't over-promise)
                                                         ->  duplication problem  ->  Strategy
```

---

## Java quick cheat sheet

You don't need to know Java to follow the examples. The few Java features used here:

| Java feature | What it is |
|---|---|
| `interface X { void run(); }` / `class A implements X` | A contract the compiler enforces. A class can implement many interfaces. |
| `abstract class` with `abstract void run();` | A parent that can't be created directly. Children must implement the abstract methods. |
| `@Override` | Compiler checks that the parent really has that method. |
| `System.out.println(x)` | Print a line. |
| `throw new IllegalArgumentException("x")` / `UnsupportedOperationException("x")` | Throw an error. |
| `int x = 5;` `final String name = "a";` | Types are written out. `final` = can't be reassigned. |
| `List<Integer> list = List.of(1, 2, 3);` | An immutable list. Use `new ArrayList<>(...)` for a mutable one. |
| `for (int x : xs)` | For-each loop. |
| `(a, b) -> a - b` | Lambda. |
| `private` | Hides a field or method from other classes. |
| `package` at the top, `import` for other packages | How files find each other. |
| `public static void main(String[] args)` | The entry point of a program. |

Three rules worth knowing:
1. **One public class per file, and the file name must match it** (`Main.java` holds `public class Main`). That is why the Java files in this repo are named like the class, for example `AmexCreditCard.java`.
2. **Everything is typed.** A method that takes a `double` can't be given a `String`. Mistakes show up when compiling, not when the program runs.
3. **Every class that is not `public` can sit in the same file.** The Java blocks below put several small classes in one file so each example is self-contained.

**Running the Java blocks:** save a block as `Main.java` and run `java Main.java` (Java 11 or newer, no separate compile step). The first class in the file must be the one with `main`, which is why `Main` comes first in every block. Java 17+ is needed for the `record` in the sorting example.

---

## 1. Single Responsibility Principle (SRP)

**Definition (Robert C. Martin):** *A class should have one, and only one, reason to change.*

In plain words: each class does one job. If two different people or concerns could ask you to change the same class (pricing rules *and* print layout *and* the database), the class has too many responsibilities. The course calls a class like that a **monster class** (often also called a "god class").

### Example: an invoice that does everything

Three reasons to change in one class: pricing, print format and storage.

```java
import java.util.List;

public class Main {
    public static void main(String[] args) {
        new Invoice(List.of(10.0, 20.0)).print();
    }
}

class Invoice {
    private final List<Double> prices;

    Invoice(List<Double> prices) {
        this.prices = prices;
    }

    double total() { // pricing rules change
        double sum = 0;
        for (double price : prices) {
            sum += price;
        }
        return sum;
    }

    void print() { // print format changes
        System.out.println("Invoice total: " + total());
    }

    void save() { // storage changes
        System.out.println("saved to database");
    }
}
```

Split it so each class has one reason to change:

```java
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Invoice invoice = new Invoice(List.of(10.0, 20.0));
        new InvoicePrinter().print(invoice);
        new InvoiceRepository().save(invoice);
    }
}

class Invoice {
    private final List<Double> prices;

    Invoice(List<Double> prices) {
        this.prices = prices;
    }

    double total() {
        double sum = 0;
        for (double price : prices) {
            sum += price;
        }
        return sum;
    }
}

class InvoicePrinter {
    void print(Invoice invoice) {
        System.out.println("Invoice total: " + invoice.total());
    }
}

class InvoiceRepository {
    void save(Invoice invoice) {
        System.out.println("saved to database");
    }
}
```

### How to spot it
- Ask "what would make me open this class?" and get more than one answer.
- A class name with "Manager", "Handler" or "Util" that keeps growing.
- Methods that use completely different sets of fields or dependencies.

### Good to know
- SRP is not only for classes. A function that validates, computes *and* saves has the same problem. Split it into smaller functions.
- It applies on the frontend too. A component that fetches data, formats it and renders it can be split into a hook (fetching), a helper (formatting) and a plain component (rendering).
- "One responsibility" is about one *reason to change*, not "one method". An `Employee` class with getters and setters is still a single responsibility: holding employee data.

### In this repo
- **Employee.** The problem is one class that holds data, computes salary, prints reports and saves: [Employee.java](single_responsibility/example1/problem/Employee.java). [better](single_responsibility/example1/better/) splits it into `Employee` (data), `EmployeeSalary`, `EmployeePerformance` and `EmployeeRepository`.
- **UserManager.** The problem mixes validation, CRUD and activity logging: [UserManager.java](single_responsibility/example2/problem/UserManager.java). In [better](single_responsibility/example2/better/) the logging moves into `ActivityLogger.java`. Each folder has a `Main.java` you can run.

---

## 2. Open/Closed Principle (OCP)

**Definition (Bertrand Meyer, popularised by Robert C. Martin):** *Software entities should be open for extension but closed for modification.*

In plain words: when a new requirement arrives, you should be able to add new code instead of editing code that already works and is already tested. This is the course's "SRP is not always enough" moment: a class can have a single responsibility and still need editing every time a new variant shows up.

### Example: discounts

Every new customer type means editing this function:

```java
public class Main {
    public static void main(String[] args) {
        System.out.println(applyDiscount("vip", 100)); // 80.0
    }

    static double applyDiscount(String customerType, double amount) {
        switch (customerType) {
            case "regular":
                return amount * 0.95;
            case "vip":
                return amount * 0.8;
            default:
                throw new IllegalArgumentException("unknown customer type");
        }
    }
}
```

Make each discount its own class behind the same method. The checkout never changes:

```java
public class Main {
    public static void main(String[] args) {
        System.out.println(checkout(100, new VipDiscount()));     // 80.0
        System.out.println(checkout(100, new StudentDiscount())); // 90.0
    }

    // depends on the Discount interface, not on a concrete type
    static double checkout(double amount, Discount discount) {
        return discount.apply(amount);
    }
}

interface Discount {
    double apply(double amount);
}

class RegularDiscount implements Discount {
    public double apply(double amount) {
        return amount * 0.95;
    }
}

class VipDiscount implements Discount {
    public double apply(double amount) {
        return amount * 0.8;
    }
}

// New requirement: student discount. Add a class, edit nothing that exists.
class StudentDiscount implements Discount {
    public double apply(double amount) {
        return amount * 0.9;
    }
}
```

### How to check if your code follows OCP
Ask: *"If a new type or option arrives tomorrow, which existing files do I have to open?"*
- If the answer is "the same class or function again", it's closed for extension.
- Smells: an `if/else` or `switch` on a type string or enum, `instanceof` chains, a method that has to list every variant.

### Good to know
- Depend on an abstraction (a shared method or interface), not on concrete types. The sender in the repo's notification example takes a list of `Notification` objects and doesn't care which kinds.
- You can't be closed against everything. Pick the changes that are *likely* (new notification channels, new customer types) and design for those.
- The Strategy pattern, later in these notes, is one of the main tools for getting OCP.

### In this repo
- **Notifications.** The problem has a `switch` on the type, so every new channel edits `NotificationSender`: [NotificationSender.java](single_responsibility/example4/problem/NotificationSender.java). The [better](open_close/example4/better/) version has a `Notification` interface and one class per channel, so `NotificationSender` never changes.
- **Chess.** [open_close/example1](open_close/example1/) starts from one `ChessPiece` class meant to handle every piece with an `if (name == 'R') ... else if (name == 'B') ...` chain, which is the same OCP smell (see the comments in [ChessPieceDraft.java](open_close/example1/problem/ChessPieceDraft.java)). Making each piece its own subclass fixes it.

---

## 3. Liskov Substitution Principle (LSP)

**Definition (Barbara Liskov):** *Objects of a subclass should be replaceable for objects of the parent class without breaking the program.*

In plain words: if code works with a `Parent`, it must keep working when you hand it any `Child`, **as-is**, with no special cases. A child may add behaviour, but it must keep every promise the parent made.

### The rules of thumb
- **Don't make the input stricter.** If the parent accepts any number, the child can't start rejecting some.
- **Don't make the result weaker.** If the parent guarantees something afterwards, the child must too.
- **Don't throw where the parent doesn't.** "Not supported" in an override is the classic violation.
- **Keep the parent's invariants.** The parent's rules about its own state must still hold.

### Example 1: Rectangle and Square (the classic)

A square *is a* rectangle in maths, but not in code:

```java
public class Main {
    public static void main(String[] args) {
        System.out.println(stretch(new Rectangle(1, 1))); // 10
        System.out.println(stretch(new Square(1)));       // 4  <- surprise, LSP broken
    }

    // Written against Rectangle: "set width 5, height 2 -> area 10"
    static int stretch(Rectangle rect) {
        rect.setWidth(5);
        rect.setHeight(2);
        return rect.area();
    }
}

class Rectangle {
    protected int w;
    protected int h;

    Rectangle(int w, int h) {
        this.w = w;
        this.h = h;
    }

    void setWidth(int w) { this.w = w; }

    void setHeight(int h) { this.h = h; }

    int area() { return w * h; }
}

class Square extends Rectangle {
    Square(int side) {
        super(side, side);
    }

    @Override
    void setWidth(int w) { // keeps itself square...
        this.w = w;
        this.h = w;
    }

    @Override
    void setHeight(int h) { // ...but changes the other side too
        this.w = h;
        this.h = h;
    }
}
```

`Square` can't stand in for `Rectangle`. The fix is to stop saying "a square is a rectangle" and share only what's true for both:

```java
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Shape> shapes = List.of(new Rectangle(5, 2), new Square(3));
        for (Shape shape : shapes) {
            System.out.println(shape.area()); // 10, 9: any Shape works the same way
        }
    }
}

abstract class Shape {
    abstract int area();
}

class Rectangle extends Shape {
    private final int w;
    private final int h;

    Rectangle(int w, int h) {
        this.w = w;
        this.h = h;
    }

    @Override
    int area() { return w * h; }
}

class Square extends Shape {
    private final int side;

    Square(int side) {
        this.side = side;
    }

    @Override
    int area() { return side * side; }
}
```

### Example 2: the bird that can't fly

```java
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Bird> birds = List.of(new Bird(), new Penguin());
        for (Bird bird : birds) {
            try {
                bird.fly();
            } catch (UnsupportedOperationException e) {
                System.out.println("LSP smell -> " + e.getMessage());
            }
        }
    }
}

class Bird {
    void fly() {
        System.out.println("flying");
    }
}

class Penguin extends Bird {
    @Override
    void fly() { // breaks the parent's promise
        throw new UnsupportedOperationException("penguins can't fly");
    }
}
```

Fix: don't promise flying to birds that can't. Put `fly()` only where it's true:

```java
public class Main {
    public static void main(String[] args) {
        new Sparrow().fly();
        new Penguin().eat();
    }
}

class Bird {
    void eat() {
        System.out.println("eating");
    }
}

class FlyingBird extends Bird {
    void fly() {
        System.out.println("flying");
    }
}

class Sparrow extends FlyingBird {
}

class Penguin extends Bird { // no fly() at all
}
```

### How to spot it
- An override that throws, or silently does nothing.
- `if (x instanceof Penguin)` checks sprinkled in code that is supposed to handle any `Bird`.
- A child that tightens what it accepts (a Pawn that rejects a backwards move while the parent `move()` accepts anything).

### In this repo
- **Chess.** In the [problem](open_close/example1/problem/ChessPiece.java) version, `Pawn.move()` throws on a backward move, so code holding a `ChessPiece` can crash. The [better](open_close/example1/better/ChessPiece.java) version returns `true` or `false` instead of throwing. Run each folder's `Main.java` to see it.
- **Credit cards.** In [problem/](interface_segregation/example1/problem/), `Rupay.intlPayment()` and `Diners.doRefund()` throw, so they can't replace the parent.

---

## 4. Interface Segregation Principle (ISP)

**Definition (Robert C. Martin):** *No client should be forced to depend on methods it does not use.*

In plain words: many small, specific contracts are better than one big, general one. A class should only have to deal with the behaviour that makes sense for it.

### Example: a printer and a scanner

One fat contract for every machine:

```java
public class Main {
    public static void main(String[] args) {
        try {
            new OldPrinter().scan();
        } catch (UnsupportedOperationException e) {
            System.out.println("ISP smell -> " + e.getMessage());
        }
    }
}

abstract class Machine {
    abstract void print();

    abstract void scan();

    abstract void fax();
}

class OldPrinter extends Machine {
    @Override
    void print() {
        System.out.println("printing");
    }

    @Override
    void scan() { // forced to have this
        throw new UnsupportedOperationException("OldPrinter can't scan");
    }

    @Override
    void fax() { // and this
        throw new UnsupportedOperationException("OldPrinter can't fax");
    }
}
```

`OldPrinter` is *forced* to carry `scan()` and `fax()`, so the only thing it can do is throw.

Split the contract and let each class pick what it supports. In Java this is exactly what `interface` and `implements` are for, because a class can implement as many interfaces as it wants.

```java
public class Main {
    public static void main(String[] args) {
        new BasicPrinter().print(); // no scan() exists, nothing to fake
        new OfficeMachine().scan();
    }
}

interface Printable {
    void print();
}

interface Scannable {
    void scan();
}

class BasicPrinter implements Printable {
    public void print() {
        System.out.println("BasicPrinter printing");
    }
}

class OfficeMachine implements Printable, Scannable { // opts in to both
    public void print() {
        System.out.println("OfficeMachine printing");
    }

    public void scan() {
        System.out.println("OfficeMachine scanning");
    }
}
```

### How to spot it
- A method that only throws `"not supported"`.
- Empty overrides that exist just to satisfy the parent.
- Adding one feature forces edits to classes that don't care about it.

### In this repo
The credit cards in [interface_segregation/example1](interface_segregation/example1/) are the real-world version of this:
- [problem/](interface_segregation/example1/problem/): `CreditCard` forces `doRefund`, `intlPayment` and `intlPinPayment` on every card. Diners can't refund and Rupay can't pay internationally, so they throw. Start with [CreditCard.java](interface_segregation/example1/problem/CreditCard.java).
- [better/](interface_segregation/example1/better/): the parent keeps only swipe, online and tap. Refund, UPI and intl PIN are separate interfaces ([RefundCompatibleCreditCard.java](interface_segregation/example1/better/RefundCompatibleCreditCard.java) and friends), and a card opts in with `implements`.

---

## LSP and ISP together

They usually show up together and have the same fix:

- A child that has to throw "not supported" is an **LSP** violation (it can't replace the parent).
- The reason it was forced to have that method is an **ISP** violation (the parent promised too much).
- **Fix:** shrink the parent so it promises only what every child can do, and move the extras into optional capabilities.

That is exactly what `problem/` to `better/` does in [example1](interface_segregation/example1/).

---

## 5. Strategy Pattern

**Definition (Gang of Four):** *Define a family of algorithms, put each in its own class, and make them interchangeable.* The object using them doesn't care which one it has.

In plain words: when the *same job* can be done in *several ways*, don't hard-code the ways with `if/else` or copy them into each class. Make each way a small object with the same method, and give the user of it whichever one it needs.

```
        uses                 interface
  Context ------------>  Strategy.doJob()
 (Order, Card ...)        /      |       \
                    StrategyA  StrategyB  StrategyC
```

### Example: shipping cost

Before, with a type check that has to be edited for every new option:

```java
public class Main {
    public static void main(String[] args) {
        System.out.println(shippingCost("weight", 3)); // 30.0
    }

    static double shippingCost(String type, double weight) {
        switch (type) {
            case "flat":
                return 50;
            case "weight":
                return weight * 10;
            case "free":
                return 0;
            default:
                throw new IllegalArgumentException("unknown shipping type");
        }
    }
}
```

After, with one class per way of doing it:

```java
public class Main {
    public static void main(String[] args) {
        Order order = new Order(3, new FlatShipping());
        System.out.println(order.shippingCost()); // 50.0

        order.setShipping(new WeightShipping()); // swap at runtime
        System.out.println(order.shippingCost()); // 30.0
    }
}

// The strategy contract
interface ShippingStrategy {
    double cost(Order order);
}

class FlatShipping implements ShippingStrategy {
    public double cost(Order order) {
        return 50;
    }
}

class WeightShipping implements ShippingStrategy {
    public double cost(Order order) {
        return order.getWeight() * 10;
    }
}

class FreeShipping implements ShippingStrategy {
    public double cost(Order order) {
        return 0;
    }
}

class Order {
    private final double weight;
    private ShippingStrategy shipping; // the strategy

    Order(double weight, ShippingStrategy shipping) {
        this.weight = weight;
        this.shipping = shipping;
    }

    double getWeight() {
        return weight;
    }

    void setShipping(ShippingStrategy shipping) {
        this.shipping = shipping;
    }

    double shippingCost() {
        return shipping.cost(this); // delegate, don't decide
    }
}
```

Adding "express shipping" is a new class. `Order` isn't edited.

### Strategies you already use

`List.sort` takes a *comparison strategy* (a `Comparator`, usually written as a lambda), so you have already used the pattern.

```java
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    record Item(int price) {}

    public static void main(String[] args) {
        List<Item> items = new ArrayList<>(List.of(new Item(30), new Item(10), new Item(20)));

        Comparator<Item> byPriceAsc = (a, b) -> a.price() - b.price();
        Comparator<Item> byPriceDesc = (a, b) -> b.price() - a.price();

        items.sort(byPriceAsc);
        System.out.println(items); // prices 10, 20, 30
        items.sort(byPriceDesc);
        System.out.println(items); // prices 30, 20, 10
    }
}
```

Use a class when the strategy has state or several methods, and a lambda when it's one action.

### When to use it
- The same logic is copy-pasted across classes (for example two cards that refund the same way).
- You have `if/else` or `switch` on a "type" to choose behaviour.
- You want to change behaviour at runtime.

### Watch out for
- **Hard-wiring the strategy in the constructor** (`this.strategy = new X()`). It works, but the user of the strategy still picks the concrete class, so you can't swap or test it easily. Passing it in (`new Order(3, new FlatShipping())`) is more flexible. This is the Dependency Inversion idea, see [(2) Dependency Inversion](<../(2) Dependency Inversion/>).
- **Too many tiny classes for something that never changes.** If there's only ever one way, you don't need a strategy.

### In this repo
[best](interface_segregation/example1/best/) pulls the refund logic out of the cards:
- The common contract, `doRefund()`: [RefundStrategy.java](interface_segregation/example1/best/strategies/refund/RefundStrategy.java) (an `interface`).
- `BankAccountRefundStrategy`, `SameInstrumentRefundStrategy` and `WalletRefundStrategy`: one class per way to refund, in the same folder.
- Each card holds a `refundStrategy` and its `doRefund()` just calls it. Master, Visa and Amex share the same bank-account strategy instead of copy-pasting it.

---

## Quick checklist

Ask yourself:

1. Does this class have more than one reason to change? That's **SRP**, so split it.
2. When a new type arrives, do I edit an existing `if/else` or `switch`? That's **OCP**, so move each case into its own class.
3. Can I pass any child where the parent is expected without an `instanceof` check? If not, that's **LSP**.
4. Does any class have a method that only throws `"not supported"` or is empty? That's **ISP**, so split the parent.
5. Is the same logic copied in several classes, or chosen by an `if/else` on a type? That's a job for the **Strategy**.

## Where the examples live

Every example folder has a `problem` and a `better` version, and the OCP/SRP ones have a runnable `Main.java`.

| Topic | Folder |
|---|---|
| SRP | [single_responsibility](single_responsibility/) |
| OCP | [open_close](open_close/) (the notification *problem* is in `single_responsibility/example4`) |
| LSP | [open_close/example1](open_close/example1/) (chess) and [interface_segregation/example1/problem](interface_segregation/example1/problem/) |
| ISP | [interface_segregation/example1](interface_segregation/example1/) (`problem`, `better`) |
| Strategy | [interface_segregation/example1/best](interface_segregation/example1/best/), notes in [strategy](strategy/) |
| DIP (next) | [(2) Dependency Inversion](<../(2) Dependency Inversion/>) |
