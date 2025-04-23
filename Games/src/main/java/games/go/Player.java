package games.go;

import games.PieceColor;

public class Player {

    private String name;
    private PieceColor playerColor;
    private boolean hasPassed = false;
    private int score = 0;

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
