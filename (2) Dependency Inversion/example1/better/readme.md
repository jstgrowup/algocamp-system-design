# Better: one contract, many databases

**Fix:** `OrderDBRepository` is a common contract with `createOrder`. `MySqlRepository` and `MongoRepository` both implement it.

- Adding a database (Postgres, an API...) is a new class implementing the contract. No existing repository is edited.

**Still wrong:** `OrderService` still creates a concrete class (`new MySqlRepository()`), and it `require`s both repository files. To switch to Mongo you must still edit the service, so Dependency Inversion is not solved yet.

**What the code does**
- `order-db-repository.js`: the abstraction (a base class with an abstract `createOrder`).
- `mysql-repository.js`, `mongo-repository.js`: each implements `createOrder` with its own DB query.
- `order-service.js`: same business rules, now calling `this.repository.createOrder(order)`.
- `demo.js`: calls both repositories through the same contract, then runs the service, which is still stuck on MySQL.

**Next:** [best](../best/).
