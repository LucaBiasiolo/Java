package games.chess.beans.pieces;

import games.PieceColor;
import games.chess.beans.ChessPiece;

public class Rook extends ChessPiece {

    public Rook(PieceColor color) {
        super("♖", "R",color);
    }

    @Override
    public boolean isMoveValid(int startX, int startY, int endX, int endY) {
        // The Rook can move any number of squares vertically or horizontally
        return (startX == endX || startY == endY);
    }

    public String getIcon() {
        return icon;
    }
}
