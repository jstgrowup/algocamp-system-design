# Problem: the service creates its own database class

**Problem:** `OrderService` does `new MySqlRepository()` in its constructor. The high-level class (business rules) depends on a low-level, concrete one (MySQL). That breaks the Dependency Inversion Principle.

- Switching to Mongo means editing `OrderService`, even though no business rule changed.
- `createOrder` can't be tested without MySQL, because the service builds the real repository itself.

**What the code does**
- `mysql-repository.js`: the repository layer, which only runs the DB query (`save`).
- `order-service.js`: `createOrder` checks there is at least one item, computes the total, then calls the repository.
- `demo.js`: creates an order and shows the empty-order rejection.

**Not solved here.** See [better](../better/).
