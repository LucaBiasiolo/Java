package games.chess.pieces;

public class King extends ChessPiece{

    private String icon = "♔";

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessPiece[][] board) {
        // The King can move one square in any direction
        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);
        return (deltaX <= 1 && deltaY <= 1);
    }

    public King(int xPosition, int yPosition, boolean isWhite) {
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

    public String getIcon() {
        return icon;
    }
}
