package games.chess.pieces;

public class Pawn extends ChessPiece {

    private final String icon = "♙";

    public Pawn(int xPosition, int yPosition, boolean isWhite) {
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
    public String getIcon() {
        return icon;
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, ChessPiece[][] board) {
        // The Pawn can move forward one square, or two squares from its starting position
        // It can also capture diagonally
        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);

        // the last case is needed to allow the pawn to move diagonally (when capturing)
        if (isWhite) {
            return (deltaY == 1 && deltaX == 0) || (deltaY == 2 && deltaX == 0 && startY == 1) || (deltaY == 1 && deltaX == 1);
        } else {
            return (deltaY == 1 && deltaX == 0) || (deltaY == 2 && deltaX == 0 && startY == 6) || (deltaY == 1 && deltaX == 1);
        }
    }

    public void promote(ChessPiece pieceToPromoteTo){
        // Logic for promoting the pawn to a different piece (e.g., Queen, Rook, Bishop, Knight)
        // This would typically involve replacing the Pawn object with the new piece object on the board
        // For simplicity, we can just set the position of the new piece to the Pawn's position
        // and remove the Pawn from the board.
    }
}
