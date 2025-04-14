package games.chess.pieces;

public class Queen extends ChessPiece{

    public Queen(boolean isWhite) {
        super("♕","Q", isWhite);
    }

    @Override
    public boolean isWhite() {
        return isWhite;
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessPiece[][] board) {
        // The Queen can move any number of squares in any direction
        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);
        return (deltaX == 0 || deltaY == 0 || deltaX == deltaY);
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return "Queen{" +
                "isWhite=" + isWhite +
                '}';
    }
}
