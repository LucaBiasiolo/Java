package games.chess.pieces;

public class Pawn extends ChessPiece {

    public Pawn(boolean isWhite) {
        super("♙", "P",isWhite);
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
    public boolean isMoveValid(int startX, int startY, int endX, int endY) {
        // The Pawn can move forward one square, or two squares from its starting position
        // It can also capture diagonally
        int deltaX = endX - startX;
        int deltaY = Math.abs(endY - startY);

        // todo: add capture en-passant
        // the first case is ordinary movement, the second two movement forward and the third for diagonal capturing
        if (isWhite) {
            return (deltaX == -1 && deltaY == 0) || (deltaX == -2 && deltaY == 0 && startX == 6) || (deltaX == -1 && deltaY == 1);
        } else {
            return (deltaX == 1 && deltaY == 0) || (deltaX == 2 && deltaY == 0 && startX == 1 ) || (deltaX == 1 && deltaY == 1);
        }
    }

    @Override
    public String toString() {
        return "Pawn{" +
                "isWhite=" + isWhite +
                '}';
    }
}
