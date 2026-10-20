// Parent only promises what EVERY piece can do: move(). It never throws for a
// bad destination, it reports it -> children are substitutable (LSP).
// castle() lives only on pieces that can castle -> no forced behaviour (ISP).
class ChessPiece {
  constructor(x, y, color) {
    this.x = x;
    this.y = y;
    this.color = color;
  }

  // Returns true if the piece moved, false if the destination isn't allowed.
  move(destX, destY) {
    if (!this.canMoveTo(destX, destY)) return false;
    this.x = destX;
    this.y = destY;
    return true;
  }

  canMoveTo(destX, destY) {
    throw new Error("abstract");
  }
}

class Pawn extends ChessPiece {
  canMoveTo(destX, destY) {
    return destX >= this.x; // behind us: refuse, don't throw
  }
}

class Knight extends ChessPiece {
  canMoveTo(destX, destY) {
    return true;
  }
}

class Rook extends ChessPiece {
  canMoveTo(destX, destY) {
    return true;
  }

  castle() {
    console.log("Rook castles");
  }
}

class King extends ChessPiece {
  canMoveTo(destX, destY) {
    return true;
  }

  castle() {
    console.log("King castles");
  }
}

module.exports = { ChessPiece, Pawn, Knight, Rook, King };
