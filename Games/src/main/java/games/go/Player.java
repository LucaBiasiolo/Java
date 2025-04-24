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

    private void placeStoneWithMatrixCoordinates(GoBoard board, int row, int column){
        // todo: check that player doesn't insert stones in places without liberties
        if(board.isStonePlaceable(row, column, playerColor)){
            board.getBoard()[row][column] = new Stone(playerColor);
        } else{
            System.err.println("Intersection already occupied by another stone");
        }
    }

    public void placeStoneWithBoardCoordinates(GoBoard board, int row, int column){
        placeStoneWithMatrixCoordinates(board, row-1, column-1);
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
