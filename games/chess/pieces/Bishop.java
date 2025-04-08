package games.chess.pieces;

public class Bishop extends ChessPiece{

    private String icon = "♗";

    public Bishop(int xPosition, int yPosition, boolean isWhite) {
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
        // The Bishop can move diagonally any number of squares
        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);
        return deltaX == deltaY;
    }

    public String getIcon() {
        return icon;
    }
}
