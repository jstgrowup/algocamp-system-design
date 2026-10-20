const { Pawn, Knight, Rook, King } = require("./chess-piece");

const pieces = [new King(4, 0, "white"), new Knight(1, 0, "white"), new Pawn(2, 1, "white"), new Rook(0, 0, "white")];

for (const piece of pieces) {
  // Same call works for every piece, none throws.
  console.log(`${piece.constructor.name} move(1, 1) ->`, piece.move(1, 1));
  // Only pieces that can castle even have castle().
  if (typeof piece.castle === "function") piece.castle();
}
