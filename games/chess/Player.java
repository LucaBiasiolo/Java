package games.chess;

import games.chess.pieces.*;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private List<ChessPiece> pieces; // todo: play "Pick up the pieces" by Average White Band
    private ChessColors color;

    public Player(String name, ChessColors color) {
        this.name = name;
        this.color = color;
        this.pieces = createPieces(color);
    }

    private List<ChessPiece> createPieces(ChessColors color) {
        List<ChessPiece> pieces = new ArrayList<>(16);
        if (color.equals(ChessColors.WHITE)) {
            pieces.add(new Pawn("a2", color));
            pieces.add(new Pawn("b2", color));
            pieces.add(new Pawn("c2", color));
            pieces.add(new Pawn("d2", color));
            pieces.add(new Pawn("e2", color));
            pieces.add(new Pawn("f2", color));
            pieces.add(new Pawn("g2", color));
            pieces.add(new Pawn("h2", color));
            pieces.add(new Rook("a1", color));
            pieces.add(new Knight("b1", color));
            pieces.add(new Bishop("c1", color));
            pieces.add(new Queen("d1", color));
            pieces.add(new King("e1", color));
            pieces.add(new Bishop("f1", color));
            pieces.add(new Knight("g1", color));
            pieces.add(new Rook("h1", color));
        } else {
            pieces.add(new Pawn("a7", color));
            pieces.add(new Pawn("b7", color));
            pieces.add(new Pawn("c7", color));
            pieces.add(new Pawn("d7", color));
            pieces.add(new Pawn("e7", color));
            pieces.add(new Pawn("f7", color));
            pieces.add(new Pawn("g7", color));
            pieces.add(new Pawn("h7", color));
            pieces.add(new Rook("a8", color));
            pieces.add(new Knight("b8", color));
            pieces.add(new Bishop("c8", color));
            pieces.add(new Queen("d8", color));
            pieces.add(new King("e8", color));
            pieces.add(new Bishop("f8", color));
            pieces.add(new Knight("g8", color));
            pieces.add(new Rook("h8", color));
        }
        return pieces;
    }

    public void movePiece(ChessPiece piece, String newPosition){}

    public void castling(King king, Rook rook){} // arrocco
}
