const { loadMovementRules } = require("./movement-rules");

// BETTER (Prototype pattern): build one pawn, clone the rest.
// `new Pawn(...)` does the expensive setup. clone() makes a cheap copy without it.
class Pawn {
  constructor(x, y, color) {
    this.rules = loadMovementRules(); // expensive
    this.x = x;
    this.y = y;
    this.color = color;
  }

  // `overrides` sets what differs on the copy, for pawns the position.
  clone(overrides = {}) {
    const proto = Object.getPrototypeOf(this); // keeps Pawn's methods
    const copy = Object.assign(Object.create(proto), this, overrides); // x, y, color
    copy.rules = { ...this.rules }; // its own rules, so changing one pawn doesn't change the others
    return copy;
  }
}

module.exports = { Pawn };
