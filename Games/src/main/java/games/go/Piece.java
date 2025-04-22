package games.go;

import games.PieceColor;

public class Piece {

    private PieceColor color;

    public Piece(PieceColor color) {
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
