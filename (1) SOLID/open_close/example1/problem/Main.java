package open_close.example1.problem;

/**
 * Runner: holds every piece as a ChessPiece and uses it "as is".
 * Pawn.move() and Knight/Pawn.castle() throw, so the children break the parent's contract (LSP).
 * (ChessPieceDraft is the first one-class-for-all draft; it has nothing to run.)
 */
public class Main {

    public static void main(String[] args) {
        ChessPiece[] pieces = {
            new King(4, 0, "white"), new Knight(1, 0, "white"), new Pawn(2, 1, "white"), new Rook(0, 0, "white")
        };

        for (ChessPiece piece : pieces) {
            String name = piece.getClass().getSimpleName();
            try {
                piece.move(1, 1);
            } catch (RuntimeException e) {
                System.out.println(name + " broke the parent contract: " + e.getMessage());
            }
            try {
                piece.castle();
            } catch (RuntimeException e) {
                System.out.println(name + " broke the parent contract: " + e.getMessage());
            }
        }
    }
}
