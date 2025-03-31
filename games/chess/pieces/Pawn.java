package games.chess.pieces;

public class Pawn extends ChessPiece {

    public Pawn(String position, ChessColors color) {
        super(position, color);
    }

    public void move(String newPosition){
        // todo: if first move, can move up two cells
    }

    public void promote(ChessPiece pieceToPromoteTo){}

    public void capture(ChessPiece pieceToCapture){
        // todo: add also capture en-passant
    }
}
