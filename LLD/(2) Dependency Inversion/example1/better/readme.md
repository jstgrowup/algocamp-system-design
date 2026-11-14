# Better: one contract, many databases

**Fix:** `OrderDBRepository` is a common contract with `createOrder`. `MySqlRepository` and `MongoRepository` both implement it.

- Adding a database (Postgres, an API...) is a new class implementing the contract. No existing repository is edited.

**Still wrong:** `OrderService` still creates a concrete class (`new MySqlRepository()`). To switch to Mongo you must still edit the service, so Dependency Inversion is not solved yet.

**What the code does**
- `OrderDBRepository`: the abstraction (an interface with `createOrder`).
- `MySqlRepository`, `MongoRepository`: each implements `createOrder` with its own DB query.
- `OrderService`: same business rules, now calling `repository.createOrder(order)`.
- `Main`: calls both repositories through the same contract, then runs the service, which is still stuck on MySQL.

**Next:** [best](../best/).
