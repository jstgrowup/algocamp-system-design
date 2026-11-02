# Best: the repository is injected

**Fix:** `OrderService` no longer creates or names a database. It receives an `OrderDBRepository` through its constructor:

```java
new OrderService(new MongoRepository()); // or new MySqlRepository()
```

Both the service and the repositories now depend on the same abstraction, so the dependency is inverted. Switching databases is one line where the service is created, and `OrderService` is never edited.

**What the code does**
- `OrderService`: its only dependency is the `OrderDBRepository` interface. It rejects a `null` repository.
- `OrderDBRepository`, `MySqlRepository`, `MongoRepository`: unchanged from `better`.
- `Main`: the one place that knows the concrete databases. It picks one at runtime (`java -cp <out> example1.best.Main mongo`), and also runs the service with an in-memory fake repository (a lambda) to show it can be tested without a database.
