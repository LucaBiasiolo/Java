package games.checkers;

import games.PieceColor;

public class Piece {
    private PieceColor color;
    private boolean isKing;

    public Piece(PieceColor color) {
        this.color = color;
    }

    public boolean isKing() {
        return isKing;
    }

    public void setKing(boolean king) {
        isKing = king;
    }

    public PieceColor getColor() {
        return color;
    }

    public void setColor(PieceColor color) {
        this.color = color;
    }
}