package games.go;

import games.PieceColor;

public class Stone {

    private final PieceColor color;

    public Stone(PieceColor color) {
        this.color = color;
    }

    public PieceColor getColor() {
        return color;
    }

    @Override
    public String toString() {
        if(color.equals(PieceColor.WHITE)){
            return "●";
        } else{
            return "○";
        }
    }
}
