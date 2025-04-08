package games.chess;

import games.chess.pieces.*;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private List<ChessPiece> pieces; // todo: play "Pick up the pieces" by Average White Band
    private boolean isWhite;

    public Player(String name, boolean isWhite) {
        this.name = name;
        this.isWhite = isWhite;
    }

    public void movePiece(ChessPiece piece, String newPosition){}

    public void castling(King king, Rook rook){} // arrocco
}
