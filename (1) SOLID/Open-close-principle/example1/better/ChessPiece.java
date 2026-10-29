package solid.ocp.example1.better;

/**
 * The parent only promises what EVERY piece can do: move().
 * It never throws for a bad destination, it reports it with true/false, so every
 * child can replace the parent safely (LSP).
 * castle() lives only on the pieces that can castle, so no piece is forced to
 * carry behaviour it doesn't have (ISP). (In Java you could also put castle() in a
 * small interface such as Castler that only Rook and King implement.)
 */
abstract class ChessPiece {

    protected int x;
    protected int y;
    protected String color;

    ChessPiece(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    /** Returns true if the piece moved, false if the destination isn't allowed. */
    boolean move(int destX, int destY) {
        if (!canMoveTo(destX, destY)) {
            return false;
        }
        this.x = destX;
        this.y = destY;
        return true;
    }

    /** Each piece decides its own rule. */
    abstract boolean canMoveTo(int destX, int destY);
}

class Pawn extends ChessPiece {

    Pawn(int x, int y, String color) {
        super(x, y, color);
    }

    @Override
    boolean canMoveTo(int destX, int destY) {
        return destX >= this.x; // behind us: refuse, don't throw
    }
}

class Knight extends ChessPiece {

    Knight(int x, int y, String color) {
        super(x, y, color);
    }

    @Override
    boolean canMoveTo(int destX, int destY) {
        return true;
    }
}

class Rook extends ChessPiece {

    Rook(int x, int y, String color) {
        super(x, y, color);
    }

    @Override
    boolean canMoveTo(int destX, int destY) {
        return true;
    }

    void castle() {
        System.out.println("Rook castles");
    }
}

class King extends ChessPiece {

    King(int x, int y, String color) {
        super(x, y, color);
    }

    @Override
    boolean canMoveTo(int destX, int destY) {
        return true;
    }

    void castle() {
        System.out.println("King castles");
    }
}
