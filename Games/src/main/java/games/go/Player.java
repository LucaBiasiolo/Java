package games.go;

import games.PieceColor;

public class Player {

    private String name;
    private PieceColor playerColor;

    public Player(String name, PieceColor playerColor) {
        this.name = name;
        this.playerColor = playerColor;
    }

    private void moveWithMatrixCoordinates(GoBoard board, int row, int column){
        if (board.getBoard()[row][column] == null){
            board.getBoard()[row][column] = new Piece(playerColor);
        }
    }

    public void moveWithBoardCoordinates(GoBoard board, int row, int column){
        moveWithMatrixCoordinates(board, row-1, column-1);
    }
}
