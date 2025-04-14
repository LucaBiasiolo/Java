package games.checkers;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Piece {
    private final boolean isRed;
    private boolean isKing;
    private final Circle visualRepresentation;

    public Piece(boolean isRed) {
        this.isRed = isRed;
        this.isKing = false;
        this.visualRepresentation = new Circle(35); // Example radius
        this.visualRepresentation.setFill(isRed ? Color.CRIMSON : Color.DARKGRAY);
    }

    public boolean isRed() {
        return isRed;
    }

    public boolean isKing() {
        return isKing;
    }

    public void promoteToKing() {
        isKing = true;
        visualRepresentation.setStroke(Color.GOLD);
        visualRepresentation.setStrokeWidth(3);
    }

    public Circle getVisualRepresentation() {
        return visualRepresentation;
    }
}