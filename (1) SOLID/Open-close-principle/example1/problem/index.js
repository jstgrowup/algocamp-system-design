// Will Make the pieces of the chess
// We gonna make the pieces of the chess
// Will write a class for chess Piece
// What are the things that are nice to have ?
class ChessPiece {
  constructor(coordinate, color, characterName) {
    this.coordinate = coordinate;
    this.color = color;
    this.characterName = characterName;
  }
  // It violates the SRP because one class is doing all the responsibility of every chess piece
  // And it violates the OCP because tomorrow if i want a piece to do something else than we  have to touch this Chess class again
  // SRP also gets violated if we have to check if the piece is eligible to move or not
  // There is a castle this method
}
