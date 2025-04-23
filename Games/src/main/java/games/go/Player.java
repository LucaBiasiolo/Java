package games.go;

import games.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private PieceColor playerColor;
    private boolean hasPassed = false;
    private List<Stone> capturedStones = new ArrayList<>();

    public Player(String name, PieceColor playerColor) {
        this.name = name;
        this.playerColor = playerColor;
    }

    private void moveWithMatrixCoordinates(GoBoard board, int row, int column){
        if (board.getBoard()[row][column] == null){
            board.getBoard()[row][column] = new Stone(playerColor);
        } else{
            System.err.println("Intersection already occupied by another stone");
        }
    }

    public void moveWithBoardCoordinates(GoBoard board, int row, int column){
        moveWithMatrixCoordinates(board, row-1, column-1);
    }

    public String getName() {
        return name;
    }

    public boolean getPassed() {
        return hasPassed;
    }

    public void setPassed(boolean hasPassed) {
        this.hasPassed = hasPassed;
    }
}
