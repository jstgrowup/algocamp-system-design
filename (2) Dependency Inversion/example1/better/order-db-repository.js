// The abstraction: what OrderService needs from ANY database.
// (In Java this is `interface OrderDBRepository { void createOrder(Order o); }`.
// JS has no interfaces, so it is a base class whose method throws until overridden.)
//
// MySQL, Mongo, Postgres, ... all implement this one contract, so they are
// interchangeable from the service's point of view.
class OrderDBRepository {
  createOrder(order) {
    throw new Error("abstract");
  }
}

module.exports = { OrderDBRepository };
