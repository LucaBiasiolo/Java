package games.chess.pieces;

public abstract class ChessPiece {

    private String position;
    private ChessColors color;

    public void move(String newPosition){
        this.position = newPosition;
    }

    public void capture(ChessPiece pieceToCapture){

    }

    public ChessPiece(String position, ChessColors color) {
        this.position = position;
        this.color = color;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public ChessColors getColor() {
        return color;
    }

    public void setColor(ChessColors color) {
        this.color = color;
    }
}
