package games.chess.pieces;

public class Knight extends ChessPiece{

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessPiece[][] board) {
        // The Knight moves in an L-shape: two squares in one direction and then one square perpendicular
        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);
        return (deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2);
    }

    public Knight(boolean isWhite) {
        super("♞", isWhite);
    }

    @Override
    public boolean isWhite() {
        return isWhite;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return "Knight{" +
                "isWhite=" + isWhite +
                '}';
    }
}
