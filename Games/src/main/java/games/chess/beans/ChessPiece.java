package games.chess.beans;

import games.PieceColor;

public abstract class ChessPiece {

    protected String icon;
    protected String letter;
    protected PieceColor color;

    public abstract boolean isMoveValid(int startX, int startY, int endX, int endY);

    public ChessPiece(String icon, String letter, PieceColor color) {
        this.icon = icon;
        this.letter = letter;
        this.color = color;
    }

    public PieceColor getColor() {
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
