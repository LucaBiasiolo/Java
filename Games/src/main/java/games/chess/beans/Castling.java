package games.chess.beans;

public enum Castling {
    QUEENSIDE("0-0-0"), KINGSIDE("0-0");

    Castling(String algebraicNotation) {
        this.algebraicNotation = algebraicNotation;
    }

    private String algebraicNotation;

    public String getAlgebraicNotation() {
        return algebraicNotation;
    }
}
