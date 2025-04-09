package games.chess.pieces;

public class Rook extends ChessPiece{

    public Rook(boolean isWhite) {
        super("♖", isWhite);
    }

    @Override
    public boolean isWhite() {
        return isWhite;
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessPiece[][] board) {
        // The Rook can move any number of squares vertically or horizontally
        return (startX == endX || startY == endY);
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return "Rook{" +
                "isWhite=" + isWhite +
                '}';
    }
}
