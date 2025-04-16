package games.chess.beans.pieces;

import games.chess.beans.ChessColor;
import games.chess.beans.ChessPiece;

public class Queen extends ChessPiece {

    public Queen(ChessColor color) {
        super("♕","Q", color);
    }

    @Override
    public boolean isMoveValid(int startX, int startY, int endX, int endY) {
        // The Queen can move any number of squares in any direction
        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);
        return (deltaX == 0 || deltaY == 0 || deltaX == deltaY);
    }

    public String getIcon() {
        return icon;
    }
}
