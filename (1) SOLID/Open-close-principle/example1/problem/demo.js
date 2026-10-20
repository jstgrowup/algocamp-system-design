const { ChessPiece, Pawn, Knight, Rook, King } = require("./chess-piece");

// Every child is held in a ChessPiece variable and used "as is" (Liskov).
/** @type {ChessPiece[]} */
const pieces = [new King(4, 0, "white"), new Knight(1, 0, "white"), new Pawn(2, 1, "white"), new Rook(0, 0, "white")];

for (const piece of pieces) {
  for (const action of [() => piece.move(1, 1), () => piece.castle()]) {
    try {
      action();
    } catch (e) {
      console.log(`${piece.constructor.name} broke the parent contract: ${e.message}`);
    }
  }
}
