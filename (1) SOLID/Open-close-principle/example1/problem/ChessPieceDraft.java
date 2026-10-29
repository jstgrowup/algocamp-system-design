package solid.ocp.example1.problem;

/**
 * First draft of the chess piece: ONE class that is meant to handle every piece.
 * (The Node file for this is index.js.)
 *
 * What are the things that are nice to have? A coordinate, a color, a name, move().
 *
 * It violates SRP because one class is doing the responsibility of every chess piece.
 * It violates OCP because tomorrow, if a piece has to do something else, we have to
 * touch this class again (an if (name == 'R') ... else if (name == 'B') ... chain).
 * SRP also gets violated if the same class has to check whether a piece is eligible
 * to move. And there is a castle method that only some pieces need.
 */
public class ChessPieceDraft {

    private final int[] coordinate; // {x, y}
    private final String color;
    private final char characterName; // 'R' rook, 'B' bishop, ...

    public ChessPieceDraft(int[] coordinate, String color, char characterName) {
        this.coordinate = coordinate;
        this.color = color;
        this.characterName = characterName;
    }
}
