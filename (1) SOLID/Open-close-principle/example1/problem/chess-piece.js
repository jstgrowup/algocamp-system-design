// Second design: abstract parent + subclasses. Fixes the if/else chain,
// but the parent contract is now too wide (ISP) and children break it (LSP).
class ChessPiece {
  constructor(x, y, color) {
    this.x = x;
    this.y = y;
    this.color = color;
  }

  move(destX, destY) {
    throw new Error("abstract");
  }

  // Forced on every piece, even those that can never castle -> ISP violation
  castle() {
    throw new Error("abstract");
  }
}

class Pawn extends ChessPiece {
  move(destX, destY) {
    // Child tightens the parent's contract -> LSP violation
    if (destX < this.x) throw new Error("Illegal argument: destX is behind the pawn");
    this.x = destX;
    this.y = destY;
  }

  castle() {
    throw new Error("Pawn cannot castle"); // forced to implement behaviour it doesn't have
  }
}

class Knight extends ChessPiece {
  move(destX, destY) {
    this.x = destX;
    this.y = destY;
  }

  castle() {
    throw new Error("Knight cannot castle");
  }
}

class Rook extends ChessPiece {
  move(destX, destY) {
    this.x = destX;
    this.y = destY;
  }

  castle() {
    console.log("Rook castles");
  }
}

class King extends ChessPiece {
  move(destX, destY) {
    this.x = destX;
    this.y = destY;
  }

  castle() {
    console.log("King castles");
  }
}

module.exports = { ChessPiece, Pawn, Knight, Rook, King };
