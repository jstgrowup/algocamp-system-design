# SOLID so far (SRP, OCP, LSP, ISP) and the Strategy Pattern, in Node and Java

Notes for the topics covered so far in the course. Every example is shown twice: first in **Node (JavaScript)**, then in **Java**, since the course code is Java.

## What's covered

| Topic | Type | Status |
|---|---|---|
| **S** - Single Responsibility Principle (SRP) | SOLID principle | Covered |
| **O** - Open/Closed Principle (OCP) | SOLID principle | Covered |
| **L** - Liskov Substitution Principle (LSP) | SOLID principle | Covered |
| **I** - Interface Segregation Principle (ISP) | SOLID principle | Covered |
| **D** - Dependency Inversion Principle (DIP) | SOLID principle | Started, see [(2) Dependency Inversion](../%282%29%20Dependency%20Inversion/) |
| **Strategy** | Design pattern | Covered (the first design pattern in the course) |

Strategy is the only design pattern covered in this part.

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

## Java for Node developers (quick cheat sheet)

You don't need to know Java to follow the examples. Here is how the pieces you use in Node look in Java.

| In Node (JavaScript) | In Java |
|---|---|
| `class A extends B { }` | `class A extends B { }` (same idea) |
| No interfaces. A mixin or duck typing stands in. | `interface X { void run(); }` and `class A implements X`. The compiler enforces it. |
| "Abstract" = a method that throws `new Error("abstract")` | `abstract class` with `abstract void run();`. You can't forget to implement it, the compiler refuses. |
| `// @Override` comment | Real `@Override` annotation. The compiler errors if the parent has no such method. |
| `console.log(x)` | `System.out.println(x)` |
| `throw new Error("x")` | `throw new IllegalArgumentException("x")` or `UnsupportedOperationException("x")` |
| `const x = 5;` `let name = "a";` | Types are written: `int x = 5;` `String name = "a";` (`final` = can't reassign) |
| `[1, 2, 3]` | `List<Integer> list = List.of(1, 2, 3);` (immutable) or `new ArrayList<>(...)` |
| `for (const x of xs)` | `for (int x : xs)` |
| `x instanceof Y` | `x instanceof Y` (same) |
| `{ id: 1, name: "a" }` object literal | A class or a `record` |
| `(a, b) => a - b` | `(a, b) -> a - b` (lambda) |
| `#field` private | `private` keyword |
| `module.exports` / `require` | `package` at the top, `import` for other packages, `public class` per file |
| Run the file: `node app.js` | Needs `public static void main(String[] args)` |

Three rules worth knowing:
1. **One public class per file, and the file name must match it** (`Main.java` holds `public class Main`). That is why the Java files in this repo are named like the class, for example `AmexCreditCard.java`.
2. **Everything is typed.** A method that takes a `double` can't be given a `String`. Mistakes show up when compiling, not when the program runs.
3. **Every class that is not `public` can sit in the same file.** The Java blocks below put several small classes in one file so each example is self-contained.

**Running the Java blocks:** save a block as `Main.java` and run `java Main.java` (Java 11 or newer, no separate compile step). The first class in the file must be the one with `main`, which is why `Main` comes first in every block. Java 17+ is needed for the `record` in the sorting example.

*The Java blocks in this readme were written carefully but not compiled on the machine that produced them, so tell me if one doesn't build.*

---

## 1. Single Responsibility Principle (SRP)

**Definition (Robert C. Martin):** *A class should have one, and only one, reason to change.*

In plain words: each class does one job. If two different people or concerns could ask you to change the same class (pricing rules *and* print layout *and* the database), the class has too many responsibilities. The course calls a class like that a **monster class** (often also called a "god class").

### Example: an invoice that does everything

Three reasons to change in one class: pricing, print format and storage.

**Node**
```js
class Invoice {
  constructor(items) { this.items = items; }
  total() { return this.items.reduce((sum, i) => sum + i.price, 0); } // pricing rules change
  print() { console.log("Invoice total: " + this.total()); }          // print format changes
  save() { console.log("saved to database"); }                        // storage changes
}

new Invoice([{ price: 10 }, { price: 20 }]).print();
```

**Java**
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

**Node**
```js
class Invoice {
  constructor(items) { this.items = items; }
  total() { return this.items.reduce((sum, i) => sum + i.price, 0); }
}
class InvoicePrinter {
  print(invoice) { console.log("Invoice total: " + invoice.total()); }
}
class InvoiceRepository {
  save(invoice) { console.log("saved to database"); }
}

const invoice = new Invoice([{ price: 10 }, { price: 20 }]);
new InvoicePrinter().print(invoice);
new InvoiceRepository().save(invoice);
```

**Java**
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
- **Employee.** The problem is one class that holds data, computes salary, prints reports and saves: [Employee.js](Single-Responsibility-principle/example1/Problem/Employee.js) / [Employee.java](Single-Responsibility-principle/example1/Problem/Employee.java). [BetterCode](Single-Responsibility-principle/example1/BetterCode/) splits it into `employee.js` (data), `employee-salary.js`, `employee-performance.js` and `employee-repository.js`, each with a Java twin (`Employee.java`, `EmployeeSalary.java`, `EmployeePerformance.java`, `EmployeeRepository.java`).
- **UserManager.** The problem mixes validation, CRUD and activity logging: [index.js](Single-Responsibility-principle/example2/problem/index.js) / [UserManager.java](Single-Responsibility-principle/example2/problem/UserManager.java). In [bettercode](Single-Responsibility-principle/example2/bettercode/) the logging moves into `activity-logger.js` / `ActivityLogger.java`.

---

## 2. Open/Closed Principle (OCP)

**Definition (Bertrand Meyer, popularised by Robert C. Martin):** *Software entities should be open for extension but closed for modification.*

In plain words: when a new requirement arrives, you should be able to add new code instead of editing code that already works and is already tested. This is the course's "SRP is not always enough" moment: a class can have a single responsibility and still need editing every time a new variant shows up.

### Example: discounts

Every new customer type means editing this function:

**Node**
```js
function applyDiscount(customerType, amount) {
  if (customerType === "regular") return amount * 0.95;
  if (customerType === "vip") return amount * 0.8;
  throw new Error("unknown customer type");
}
console.log(applyDiscount("vip", 100)); // 80
```

**Java**
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

**Node**
```js
class RegularDiscount {
  apply(amount) { return amount * 0.95; }
}
class VipDiscount {
  apply(amount) { return amount * 0.8; }
}

function checkout(amount, discount) {
  return discount.apply(amount); // depends on "something with apply()", not on a type
}

console.log(checkout(100, new VipDiscount())); // 80

// New requirement: student discount. Add a class, edit nothing that exists.
class StudentDiscount {
  apply(amount) { return amount * 0.9; }
}
console.log(checkout(100, new StudentDiscount())); // 90
```

**Java**
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
- **Notifications.** The problem has a `switch` on the type, so every new channel edits `NotificationSender`: [notification-sender.ts](Single-Responsibility-principle/example4/problem/notification-sender.ts) / [NotificationSender.java](Single-Responsibility-principle/example4/problem/NotificationSender.java). The [better](Open-close-principle/example4/better/) version has a `Notification` interface and one class per channel (`.ts` and `.java`), so `NotificationSender` never changes.
- **Chess.** [Open-close-principle/example1](Open-close-principle/example1/) starts from one `ChessPiece` class meant to handle every piece with an `if (name == 'R') ... else if (name == 'B') ...` chain, which is the same OCP smell (see the comments in [index.js](Open-close-principle/example1/problem/index.js) / [ChessPieceDraft.java](Open-close-principle/example1/problem/ChessPieceDraft.java)). Making each piece its own subclass fixes it.

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

**Node**
```js
class Rectangle {
  constructor(w, h) { this.w = w; this.h = h; }
  setWidth(w) { this.w = w; }
  setHeight(h) { this.h = h; }
  area() { return this.w * this.h; }
}

class Square extends Rectangle {
  setWidth(w) { this.w = w; this.h = w; }  // keeps itself square...
  setHeight(h) { this.w = h; this.h = h; } // ...but changes the other side too
}

// Written against Rectangle: "set width 5, height 2 -> area 10"
function stretch(rect) {
  rect.setWidth(5);
  rect.setHeight(2);
  return rect.area();
}

console.log(stretch(new Rectangle(1, 1))); // 10
console.log(stretch(new Square(1, 1)));    // 4  <- surprise, LSP broken
```

**Java**
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

**Node**
```js
class Shape {
  area() { throw new Error("abstract"); }
}
class Rectangle extends Shape {
  constructor(w, h) { super(); this.w = w; this.h = h; }
  area() { return this.w * this.h; }
}
class Square extends Shape {
  constructor(side) { super(); this.side = side; }
  area() { return this.side * this.side; }
}

for (const shape of [new Rectangle(5, 2), new Square(3)]) {
  console.log(shape.area()); // 10, 9: any Shape works the same way
}
```

**Java**
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

**Node**
```js
class Bird {
  fly() { console.log("flying"); }
}
class Penguin extends Bird {
  fly() { throw new Error("penguins can't fly"); } // breaks the parent's promise
}

for (const bird of [new Bird(), new Penguin()]) {
  try {
    bird.fly();
  } catch (e) {
    console.log("LSP smell ->", e.message);
  }
}
```

**Java**
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

**Node**
```js
class Bird {
  eat() { console.log("eating"); }
}
class FlyingBird extends Bird {
  fly() { console.log("flying"); }
}
class Sparrow extends FlyingBird {}
class Penguin extends Bird {} // no fly() at all

new Sparrow().fly();
new Penguin().eat();
```

**Java**
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
- **Chess.** In the [problem](Open-close-principle/example1/problem/chess-piece.js) version, `Pawn.move()` throws on a backward move, so code holding a `ChessPiece` can crash ([ChessPiece.java](Open-close-principle/example1/problem/ChessPiece.java)). The [better](Open-close-principle/example1/better/chess-piece.js) version returns `true` or `false` instead of throwing ([ChessPiece.java](Open-close-principle/example1/better/ChessPiece.java)).
- **Credit cards.** In [problem/](Interface-Segregation-pattern/example1/problem/), `Rupay.intlPayment()` and `Diners.doRefund()` throw, so they can't replace the parent (each card has a `.js` and a `.java` file).

---

## 4. Interface Segregation Principle (ISP)

**Definition (Robert C. Martin):** *No client should be forced to depend on methods it does not use.*

In plain words: many small, specific contracts are better than one big, general one. A class should only have to deal with the behaviour that makes sense for it.

### Example: a printer and a scanner

One fat contract for every machine:

**Node**
```js
class Machine {
  print() { throw new Error("abstract"); }
  scan() { throw new Error("abstract"); }
  fax() { throw new Error("abstract"); }
}

class OldPrinter extends Machine {
  print() { console.log("printing"); }
  scan() { throw new Error("OldPrinter can't scan"); } // forced to have this
  fax() { throw new Error("OldPrinter can't fax"); }   // and this
}

try {
  new OldPrinter().scan();
} catch (e) {
  console.log("ISP smell ->", e.message);
}
```

**Java**
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

Split the contract and let each class pick what it supports. In Java this is exactly what `interface` and `implements` are for, because a class can implement as many interfaces as it wants. In JS a mixin (a function that takes a class and returns a subclass) is the native way to do the same.

**Node**
```js
const Scanner = (Base) =>
  class extends Base {
    scan() { throw new Error("abstract"); } // must be overridden by whoever opts in
  };

class Printer {
  print() { throw new Error("abstract"); }
}

class BasicPrinter extends Printer {
  print() { console.log("BasicPrinter printing"); }
}

class OfficeMachine extends Scanner(Printer) {
  print() { console.log("OfficeMachine printing"); }
  scan() { console.log("OfficeMachine scanning"); }
}

new BasicPrinter().print();   // no scan() exists, nothing to fake
new OfficeMachine().scan();
```

**Java**
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
The credit cards in [Interface-Segregation-pattern/example1](Interface-Segregation-pattern/example1/) are the real-world version of this:
- [problem/](Interface-Segregation-pattern/example1/problem/): `CreditCard` forces `doRefund`, `intlPayment` and `intlPinPayment` on every card. Diners can't refund and Rupay can't pay internationally, so they throw. Start with [credit-card.js](Interface-Segregation-pattern/example1/problem/credit-card.js) / [CreditCard.java](Interface-Segregation-pattern/example1/problem/CreditCard.java).
- [better/](Interface-Segregation-pattern/example1/better/): the parent keeps only swipe, online and tap. Refund, UPI and intl PIN are separate capabilities. In Node they are mixins ([refund-compatible.js](Interface-Segregation-pattern/example1/better/refund-compatible.js) and friends). In Java they are interfaces ([RefundCompatibleCreditCard.java](Interface-Segregation-pattern/example1/better/RefundCompatibleCreditCard.java) and friends), and a card opts in with `implements`.

---

## LSP and ISP together

They usually show up together and have the same fix:

- A child that has to throw "not supported" is an **LSP** violation (it can't replace the parent).
- The reason it was forced to have that method is an **ISP** violation (the parent promised too much).
- **Fix:** shrink the parent so it promises only what every child can do, and move the extras into optional capabilities.

That is exactly what `problem/` to `better/` does in [example1](Interface-Segregation-pattern/example1/).

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

**Node**
```js
function shippingCost(type, weight) {
  if (type === "flat") return 50;
  if (type === "weight") return weight * 10;
  if (type === "free") return 0;
  throw new Error("unknown shipping type");
}
console.log(shippingCost("weight", 3)); // 30
```

**Java**
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

**Node**
```js
class FlatShipping {
  cost(order) { return 50; }
}
class WeightShipping {
  cost(order) { return order.weight * 10; }
}
class FreeShipping {
  cost(order) { return 0; }
}

class Order {
  constructor(weight, shipping) {
    this.weight = weight;
    this.shipping = shipping; // the strategy
  }
  shippingCost() {
    return this.shipping.cost(this); // delegate, don't decide
  }
}

const order = new Order(3, new FlatShipping());
console.log(order.shippingCost()); // 50

order.shipping = new WeightShipping(); // swap at runtime
console.log(order.shippingCost()); // 30
```

**Java**
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

`Array.prototype.sort` in JS and `List.sort` in Java both take a *comparison strategy*, so you have already used the pattern. In JS a strategy can just be a function. In Java it is a `Comparator`, which you usually write as a lambda.

**Node**
```js
const items = [{ price: 30 }, { price: 10 }, { price: 20 }];

const byPriceAsc = (a, b) => a.price - b.price;
const byPriceDesc = (a, b) => b.price - a.price;

console.log(items.sort(byPriceAsc).map((i) => i.price));  // [10, 20, 30]
console.log(items.sort(byPriceDesc).map((i) => i.price)); // [30, 20, 10]
```

**Java**
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

Use a class when the strategy has state or several methods, and a plain function (or lambda) when it's one action.

### When to use it
- The same logic is copy-pasted across classes (for example two cards that refund the same way).
- You have `if/else` or `switch` on a "type" to choose behaviour.
- You want to change behaviour at runtime.

### Watch out for
- **Hard-wiring the strategy in the constructor** (`this.strategy = new X()`). It works, but the user of the strategy still picks the concrete class, so you can't swap or test it easily. Passing it in (`new Order(3, new FlatShipping())`) is more flexible. This is the Dependency Inversion idea, see [(2) Dependency Inversion](../%282%29%20Dependency%20Inversion/).
- **Too many tiny classes for something that never changes.** If there's only ever one way, you don't need a strategy.

### In this repo
[best (SP)](Interface-Segregation-pattern/example1/best%20%28SP%29/) pulls the refund logic out of the cards:
- The common contract, `doRefund()`: [refund-strategy.js](Interface-Segregation-pattern/example1/best%20%28SP%29/strategies/refund/refund-strategy.js) / [RefundStrategy.java](Interface-Segregation-pattern/example1/best%20%28SP%29/strategies/refund/RefundStrategy.java) (an `interface` in Java).
- `BankAccountRefundStrategy`, `SameInstrumentRefundStrategy` and `WalletRefundStrategy`: one class per way to refund (each with a `.js` and a `.java` file in the same folder).
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

Every Node file in these folders has a Java file next to it.

| Topic | Folder |
|---|---|
| SRP | [Single-Responsibility-principle](Single-Responsibility-principle/) |
| OCP | [Open-close-principle](Open-close-principle/) (the notification *problem* is in `Single-Responsibility-principle/example4`) |
| LSP | [Open-close-principle/example1](Open-close-principle/example1/) (chess) and [Interface-Segregation-pattern/example1/problem](Interface-Segregation-pattern/example1/problem/) |
| ISP | [Interface-Segregation-pattern/example1](Interface-Segregation-pattern/example1/) (`problem`, `better`) |
| Strategy | [Interface-Segregation-pattern/example1/best (SP)](Interface-Segregation-pattern/example1/best%20%28SP%29/) |
| DIP (next) | [(2) Dependency Inversion](../%282%29%20Dependency%20Inversion/) |
