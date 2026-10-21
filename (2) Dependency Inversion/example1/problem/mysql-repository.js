// Repository layer: ONLY database-level queries live here. No business rules.
// Everything that knows "we are on MySQL" is kept inside this class.
class MySqlRepository {
  save(order) {
    // In real code: connection.query("INSERT INTO orders ...", [...])
    console.log(`MySQL: INSERT INTO orders (id, customer, total) VALUES (${order.id}, '${order.customer}', ${order.total})`);
    return order;
  }
}

module.exports = { MySqlRepository };
