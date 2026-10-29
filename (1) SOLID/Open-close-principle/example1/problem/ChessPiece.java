package solid.ocp.example1.problem;

/**
 * Second design: an abstract parent + one subclass per piece.
 * This fixes the big if/else chain of the first draft (see ChessPieceDraft.java),
 * but the parent's contract is now too wide (ISP) and children break it (LSP).
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

    abstract void move(int destX, int destY);

    // Forced on every piece, even those that can never castle -> ISP violation
    abstract void castle();
}

class Pawn extends ChessPiece {

    Pawn(int x, int y, String color) {
        super(x, y, color);
    }

    @Override
    void move(int destX, int destY) {
        // Child tightens the parent's contract -> LSP violation
        if (destX < this.x) {
            throw new IllegalArgumentException("Illegal argument: destX is behind the pawn");
        }
        this.x = destX;
        this.y = destY;
    }

    @Override
    void castle() {
        // forced to implement behaviour it doesn't have
        throw new UnsupportedOperationException("Pawn cannot castle");
    }
}

class Knight extends ChessPiece {

    Knight(int x, int y, String color) {
        super(x, y, color);
    }

    @Override
    void move(int destX, int destY) {
        this.x = destX;
        this.y = destY;
    }

    @Override
    void castle() {
        throw new UnsupportedOperationException("Knight cannot castle");
    }
}

class Rook extends ChessPiece {

    Rook(int x, int y, String color) {
        super(x, y, color);
    }

    @Override
    void move(int destX, int destY) {
        this.x = destX;
        this.y = destY;
    }

    @Override
    void castle() {
        System.out.println("Rook castles");
    }
}

class King extends ChessPiece {

    King(int x, int y, String color) {
        super(x, y, color);
    }

    @Override
    void move(int destX, int destY) {
        this.x = destX;
        this.y = destY;
    }

    @Override
    void castle() {
        System.out.println("King castles");
    }
}
