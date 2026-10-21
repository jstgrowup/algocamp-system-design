# Best: the repository is injected

**Fix:** `OrderService` no longer creates or names a database. It receives an `OrderDBRepository` through its constructor:

```js
new OrderService(new MongoRepository()); // or new MySqlRepository()
```

Both the service and the repositories now depend on the same abstraction, so the dependency is inverted. Switching databases is one line where the service is created, and `order-service.js` is never edited.

**What the code does**
- `order-service.js`: its only `require` is `OrderDBRepository`. It rejects anything that isn't one.
- `order-db-repository.js`, `mysql-repository.js`, `mongo-repository.js`: unchanged from `better`.
- `demo.js`: the one place that knows the concrete databases. It picks one at runtime (`DB=mongo node demo.js`), and also runs the service with an in-memory fake repository to show it can be tested without a database.
