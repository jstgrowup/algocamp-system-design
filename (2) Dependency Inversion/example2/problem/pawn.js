// PROBLEM 1: creating many pawns from scratch.   Run: node pawn.js
//
// A chess board needs 8 pawns per side. Each one has its own position, so each
// is configured when it is created (we can't just loop and stamp out copies).
//
// What is wrong
// -------------
// Building a Pawn is expensive (here: loading its movement rules). Every
// `new Pawn(...)` repeats that same expensive work, even though all pawns of a
// colour start with identical rules. Only x, y and colour differ.
const SETUP_MS = 25; // pretend loading the rules takes a while

class Pawn {
  static expensiveSetups = 0;

  constructor(x, y, color) {
    this.rules = Pawn.loadMovementRules(); // expensive, repeated for every pawn
    this.x = x;
    this.y = y;
    this.color = color;
  }

  static loadMovementRules() {
    Pawn.expensiveSetups++;
    console.log(`  loading movement rules (setup #${Pawn.expensiveSetups})...`);
    const end = Date.now() + SETUP_MS;
    while (Date.now() < end) {} // busy wait, stands in for real loading work
    return { forwardOnly: true, firstMoveTwoSquares: true, capturesDiagonally: true };
  }
}

// The repetition: 8 pawns, 8 identical expensive setups.
const start = Date.now();
const pawn1 = new Pawn(0, 1, "white");
const pawn2 = new Pawn(1, 1, "white");
const pawn3 = new Pawn(2, 1, "white");
const pawn4 = new Pawn(3, 1, "white");
const pawn5 = new Pawn(4, 1, "white");
const pawn6 = new Pawn(5, 1, "white");
const pawn7 = new Pawn(6, 1, "white");
const pawn8 = new Pawn(7, 1, "white");

console.log(`8 pawns created, ${Pawn.expensiveSetups} expensive setups, took ~${Date.now() - start}ms`);
console.log("The rules are identical for every pawn, only x, y and colour differ.");
