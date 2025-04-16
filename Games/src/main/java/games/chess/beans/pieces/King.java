package games.chess.beans.pieces;

import games.chess.beans.ChessColor;
import games.chess.beans.ChessPiece;

public class King extends ChessPiece {

    @Override
    public boolean isMoveValid(int startX, int startY, int endX, int endY) {
        // The King can move one square in any direction
        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);

        //special case for queen-side castling - king-side castling
        if (startX == 7 && endX == 7 && startY == 4 && (endY == 2 || endY == 6)) return true; // for white king
        if (startX == 0 && endX == 0 && startY == 4 && (endY == 2 || endY == 6)) return true; // for black king

        return (deltaX <= 1 && deltaY <= 1);
    }

    public King(ChessColor color) {
        super("♔", "K", color);
    }

    public String getIcon() {
        return icon;
    }
}
