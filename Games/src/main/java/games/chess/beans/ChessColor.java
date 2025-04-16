package games.chess.beans;

public enum ChessColor {
    BLACK("black"), WHITE("white");

    ChessColor(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }
}
