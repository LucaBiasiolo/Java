package games.chess.beans.pieces;

import games.PieceColor;
import games.chess.beans.ChessPiece;

public class Bishop extends ChessPiece {

    public Bishop(PieceColor color) {
        super("♗", "B", color);
    }

    @Override
    public boolean isMoveValid(int startX, int startY, int endX, int endY) {
        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);
        return deltaX == deltaY;
    }
}
