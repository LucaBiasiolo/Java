package games;

public enum PieceColor {
    BLACK("black"), WHITE("white");

    PieceColor(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }
}
