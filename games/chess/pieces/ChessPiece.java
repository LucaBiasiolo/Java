package games.chess.pieces;

public abstract class ChessPiece {

    protected String icon;
    protected int xPosition;
    protected int yPosition;
    protected boolean isWhite;

    public abstract boolean isValidMove(int startX, int startY, int endX, int endY, ChessPiece[][] board);

    public ChessPiece(int xPosition, int yPosition, boolean isWhite) {
        this.isWhite = isWhite;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public abstract int getXPosition();

    public abstract int getYPosition();

    public abstract boolean isWhite();

    public abstract String getIcon();

    @Override
    public abstract String toString();
}
