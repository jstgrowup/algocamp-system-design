// rules/Violation.java
// A broken rule, carrying the reason. Create it with Violation.of("message").
//
// Java makes the constructor private so `of()` is the only way in. JS can't make
// a constructor private, so by convention use `Violation.of(...)`.
class Violation {
  #message;

  constructor(message) {
    this.#message = message;
  }

  static of(message) {
    return new Violation(message);
  }

  get message() {
    return this.#message;
  }
}

module.exports = { Violation };
