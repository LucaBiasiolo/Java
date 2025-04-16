package games.chess.beans.pieces;

import games.chess.beans.ChessColor;
import games.chess.beans.ChessPiece;

public class Knight extends ChessPiece {

    @Override
    public boolean isMoveValid(int startX, int startY, int endX, int endY) {
        // The Knight moves in an L-shape: two squares in one direction and then one square perpendicular
        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);
        return (deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2);
    }

    public Knight(ChessColor color) {
        super("♞", "N", color);
    }


    public String getIcon() {
        return icon;
    }
}
