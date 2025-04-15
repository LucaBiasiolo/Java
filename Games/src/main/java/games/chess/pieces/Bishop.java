package games.chess.pieces;

public class Bishop extends ChessPiece{

    public Bishop(boolean isWhite) {
        super("♗", "B", isWhite);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessPiece[][] board) {
        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);
        return deltaX == deltaY;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return "Bishop{" +
                "isWhite=" + isWhite +
                '}';
    }
}
