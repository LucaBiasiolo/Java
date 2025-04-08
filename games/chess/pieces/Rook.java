package games.chess.pieces;

public class Rook extends ChessPiece{

    private String icon = "♖";

    public Rook(int xPosition, int yPosition, boolean isWhite) {
        super(xPosition, yPosition, isWhite);
    }

    @Override
    public int getXPosition() {
        return xPosition;
    }

    @Override
    public int getYPosition() {
        return yPosition;
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
}
