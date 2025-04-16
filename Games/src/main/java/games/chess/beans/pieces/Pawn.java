package games.chess.beans.pieces;

import games.chess.beans.ChessColor;
import games.chess.beans.ChessPiece;

public class Pawn extends ChessPiece {

    public Pawn(ChessColor color) {
        super("♙", null,color);
    }

    @Override
    public String getIcon() {
        return icon;
    }

    @Override
    public boolean isMoveValid(int startX, int startY, int endX, int endY) {
        // The Pawn can move forward one square, or two squares from its starting position
        // It can also capture diagonally
        int deltaX = endX - startX;
        int deltaY = Math.abs(endY - startY);

        // todo: add capture en-passant
        // the first case is ordinary movement, the second two movement forward and the third for diagonal capturing
        if (color.equals(ChessColor.WHITE)) {
            return (deltaX == -1 && deltaY == 0) || (deltaX == -2 && deltaY == 0 && startX == 6) || (deltaX == -1 && deltaY == 1);
        } else {
            return (deltaX == 1 && deltaY == 0) || (deltaX == 2 && deltaY == 0 && startX == 1 ) || (deltaX == 1 && deltaY == 1);
        }
    }
}
