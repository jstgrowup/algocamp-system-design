package open_close.example1.better;

/** Runner: the same move() call works for every piece and never throws. */
public class Main {

    public static void main(String[] args) {
        ChessPiece[] pieces = {
            new King(4, 0, "white"), new Knight(1, 0, "white"), new Pawn(2, 1, "white"), new Rook(0, 0, "white")
        };

        for (ChessPiece piece : pieces) {
            System.out.println(piece.getClass().getSimpleName() + " move(1, 1) -> " + piece.move(1, 1));
            // castle() exists only on pieces that can castle, so check the type first
            // (instanceof is the Java version of JS's typeof piece.castle === "function").
            if (piece instanceof King king) {
                king.castle();
            } else if (piece instanceof Rook rook) {
                rook.castle();
            }
        }
    }
}
