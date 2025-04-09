package games.chess.pieces;

public abstract class ChessPiece {

    protected String icon;
    protected boolean isWhite;

    public abstract boolean isValidMove(int startX, int startY, int endX, int endY, ChessPiece[][] board);

    public ChessPiece(String icon, boolean isWhite) {
        this.icon = icon;
        this.isWhite = isWhite;
    }

    public abstract boolean isWhite();

    public abstract String getIcon();

    @Override
    public abstract String toString();
}
