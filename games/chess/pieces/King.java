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

    public boolean isInCheck(){
        // Check if the King is in check

        return false;
    }

    public boolean isInCheckMate(){
        // Check if the King is in checkmate
        // This is a placeholder implementation
        return false;
    }

    public boolean isInStaleMate(){
        // Check if the King is in stalemate
        // This is a placeholder implementation
        return false;
    }

    public boolean isInDraw(){
        // Check if the King is in draw
        // This is a placeholder implementation
        return false;
    }

    public void castling(Rook rook){
        // Perform castling with the Rook
        // This is a placeholder implementation
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

    @Override
    public String toString() {
        return "King{" +
                "xPosition=" + xPosition +
                ", yPosition=" + yPosition +
                ", isWhite=" + isWhite +
                '}';
    }
}
