package games.go;

import games.PieceColor;

public class GoBoard {

    //9x9, 13x13 or 19x19 crosses
    private final Piece[][] board;

    public GoBoard(int dimension) {
        board = new Piece[dimension][dimension];
    }

    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            // Print horizontal intersections and stones
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == null) {
                    System.out.print(" + ");
                } else {
                    System.out.print(board[i][j].getColor().equals(PieceColor.BLACK) ? " ○ " : " ● ");
                }
            }
            System.out.println();
        }
    }
}
