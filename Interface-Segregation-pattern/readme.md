# ISP, LSP and the Strategy Pattern

Three ideas that show up together: two SOLID principles and one design pattern.

| | One-line meaning | The smell that tells you it's broken |
|---|---|---|
| **ISP** (Interface Segregation) | Don't force a class to have methods it doesn't need. | A method that just throws `"not supported"`, or an empty method. |
| **LSP** (Liskov Substitution) | A child must work anywhere its parent is used. | A child that throws, or breaks a rule the parent promised. |
| **Strategy** | Put each way of doing a job in its own class and swap them. | The same logic copy-pasted in many classes, or a growing `if/else` on a type. |

Every code block below runs as-is in Node.

---

## 1. Interface Segregation Principle (ISP)

**Definition (Robert C. Martin):** *No client should be forced to depend on methods it does not use.*

In plain words: many small, specific contracts are better than one big, general one. A class should only have to deal with the behaviour that makes sense for it.

### Example: a printer and a scanner

One fat contract for every machine:

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

`OldPrinter` is *forced* to carry `scan()` and `fax()`, so the only thing it can do is throw.

Split the contract and let each class pick what it supports. In JS a mixin (a function that takes a class and returns a subclass) is the native way to do that:

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

### How to spot it
- A method that only throws `"not supported"`.
- Empty overrides that exist just to satisfy the parent.
- Adding one feature forces edits to classes that don't care about it.

### In this repo
[example1](example1/) is the credit card version of this:
- [problem/](example1/problem/): `CreditCard` forces `doRefund`, `intlPayment` and `intlPinPayment` on every card. Diners can't refund and Rupay can't pay internationally, so they throw.
- [better/](example1/better/): the parent keeps only swipe, online and tap. Refund, UPI and intl PIN are separate capability mixins ([refund-compatible.js](example1/better/refund-compatible.js) and friends), and a card opts in by extending them.

---

## 2. Liskov Substitution Principle (LSP)

**Definition (Barbara Liskov):** *Objects of a subclass should be replaceable for objects of the parent class without breaking the program.*

In plain words: if code works with a `Parent`, it must keep working when you hand it any `Child`, **as-is**, with no special cases. A child may add behaviour, but it must keep every promise the parent made.

### The rules of thumb
- **Don't make the input stricter.** If the parent accepts any number, the child can't start rejecting some.
- **Don't make the result weaker.** If the parent guarantees something afterwards, the child must too.
- **Don't throw where the parent doesn't.** "Not supported" in an override is the classic violation.
- **Keep the parent's invariants.** The parent's rules about its own state must still hold.

### Example 1: Rectangle and Square (the classic)

A square *is a* rectangle in maths, but not in code:

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

`Square` can't stand in for `Rectangle`. The fix is to stop saying "a square is a rectangle" and share only what's true for both:

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

### Example 2: the bird that can't fly

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

Fix: don't promise flying to birds that can't. Put `fly()` only where it's true:

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

### How to spot it
- An override that throws, or silently does nothing.
- `if (x instanceof Penguin)` checks sprinkled in code that is supposed to handle any `Bird`.
- A child that tightens what it accepts (a Pawn that rejects a backwards move while the parent `move()` accepts anything).

### In this repo
- Chess: [Open-close-principle/example1](../Open-close-principle/example1/). `Pawn.move()` throws on a backward move, so code holding a `ChessPiece` can crash. The `better` version returns `true` or `false` instead of throwing.
- Credit cards: [problem/](example1/problem/). `Rupay.intlPayment()` and `Diners.doRefund()` throw, so they can't replace the parent.

---

## ISP and LSP together

They usually show up together and have the same fix:

- A child that has to throw "not supported" is an **LSP** violation (it can't replace the parent).
- The reason it was forced to have that method is an **ISP** violation (the parent promised too much).
- **Fix:** shrink the parent so it promises only what every child can do, and move the extras into optional capabilities.

That is exactly what `problem/` to `better/` does in [example1](example1/).

---

## 3. Strategy Pattern

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

```js
function shippingCost(type, weight) {
  if (type === "flat") return 50;
  if (type === "weight") return weight * 10;
  if (type === "free") return 0;
  throw new Error("unknown shipping type");
}
console.log(shippingCost("weight", 3)); // 30
```

After, with one class per way of doing it:

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

Adding "express shipping" is a new class. `Order` isn't edited.

### In JS a strategy can just be a function

You've already used the pattern: `Array.prototype.sort` takes a *comparison strategy*.

```js
const items = [{ price: 30 }, { price: 10 }, { price: 20 }];

const byPriceAsc = (a, b) => a.price - b.price;
const byPriceDesc = (a, b) => b.price - a.price;

console.log(items.sort(byPriceAsc).map((i) => i.price));  // [10, 20, 30]
console.log(items.sort(byPriceDesc).map((i) => i.price)); // [30, 20, 10]
```

Use a class when the strategy has state or several methods, and a plain function when it's one action.

### When to use it
- The same logic is copy-pasted across classes (for example two cards that refund the same way).
- You have `if/else` or `switch` on a "type" to choose behaviour.
- You want to change behaviour at runtime.

### Watch out for
- **Hard-wiring the strategy in the constructor** (`this.strategy = new X()`). It works, but the user of the strategy still picks the concrete class, so you can't swap or test it easily. Passing it in (`new Order(3, new FlatShipping())`) is more flexible.
- **Too many tiny classes for something that never changes.** If there's only ever one way, you don't need a strategy.

### In this repo
[best (SP)](example1/best%20(SP)/) pulls the refund logic out of the cards:
- [refund-strategy.js](example1/best%20(SP)/strategies/refund/refund-strategy.js): the common contract, `doRefund()`.
- `bank-account-`, `same-instrument-` and `wallet-refund-strategy.js`: one class per way to refund.
- Each card holds a `refundStrategy` and its `doRefund()` just calls it. Master, Visa and Amex share the same bank-account strategy instead of copy-pasting it.

---

## Quick checklist

Ask yourself:

1. Does any class have a method that only throws `"not supported"` or is empty? That's **ISP**, so split the parent.
2. Can I pass any child where the parent is expected without an `instanceof` check? If not, that's **LSP**.
3. Is the same logic copied in several classes, or an `if/else` on a type? That's a job for the **Strategy**.
