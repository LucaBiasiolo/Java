package games.chess.pieces;

public class King extends ChessPiece{

    @Override
    public boolean isMoveValid(int startX, int startY, int endX, int endY) {
        // The King can move one square in any direction
        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);

        //special case for queen-side castling - king-side castling
        if (startX == 7 && endX == 7 && startY == 4 && (endY == 2 || endY == 6)) return true; // for white king
        if (startX == 0 && endX == 0 && startY == 4 && (endY == 2 || endY == 6)) return true; // for black king

        return (deltaX <= 1 && deltaY <= 1);
    }

    public King(boolean isWhite) {
        super("♔", "K", isWhite);
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
    public boolean isWhite() {
        return isWhite;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return "King{" +
                "isWhite=" + isWhite +
                '}';
    }
}
