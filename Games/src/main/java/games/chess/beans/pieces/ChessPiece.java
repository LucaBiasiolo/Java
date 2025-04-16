package games.chess.beans.pieces;

public abstract class ChessPiece {

    protected String icon;
    protected String letter;
    protected boolean isWhite;

    public abstract boolean isMoveValid(int startX, int startY, int endX, int endY);

    public ChessPiece(String icon, String letter, boolean isWhite) {
        this.icon = icon;
        this.letter = letter;
        this.isWhite = isWhite;
    }

    public boolean isWhite(){
        return isWhite;
    }

    public String getIcon(){
        return icon;
    }

    @Override
    public abstract String toString();

    public String getLetter(){
        return letter;
    }
}
