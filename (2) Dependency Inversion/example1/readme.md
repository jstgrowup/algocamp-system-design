# Example 1: Order service and database

An `OrderService` creates orders and saves them through a repository. The three folders show the same code in three steps.

| Folder              | Service picks the DB itself?      | Adding a new DB                 | Switching DB                          |
| ------------------- | --------------------------------- | ------------------------------- | ------------------------------------- |
| [problem](problem/) | Yes, `new MySqlRepository()`      | Edit the service                | Edit the service                      |
| [better](better/)   | Yes, still names a concrete class | New class, no repository edited | Edit the service                      |
| [best](best/)       | No, it is handed in               | New class                       | One line where the service is created |

Run any step with `node demo.js` inside its folder.
