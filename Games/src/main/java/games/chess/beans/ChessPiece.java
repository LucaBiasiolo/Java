package games.chess.beans;

public abstract class ChessPiece {

    protected String icon;
    protected String letter;
    protected ChessColor color;

    public abstract boolean isMoveValid(int startX, int startY, int endX, int endY);

    public ChessPiece(String icon, String letter, ChessColor color) {
        this.icon = icon;
        this.letter = letter;
        this.color = color;
    }

    public ChessColor getColor() {
        return color;
    }

    public String getIcon(){
        return icon;
    }

    @Override
    public String toString() {
        return color.getDescription() + " " + this.getClass().getSimpleName();
    }

    public String getLetter(){
        return letter;
    }
}
