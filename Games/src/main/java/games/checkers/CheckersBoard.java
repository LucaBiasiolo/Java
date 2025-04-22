package games.checkers;

import games.PieceColor;

/*
 * This game follows international checkers' rules (10x10 board)
 *  */
public class CheckersBoard {

    private Piece[][] board;
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    public CheckersBoard() {
        this.board = new Piece[10][10];
        initializeBoard();
    }

    public void initializeBoard(){
        board[0][1] = new Piece(PieceColor.BLACK);
        board[0][3] = new Piece(PieceColor.BLACK);
        board[0][5] = new Piece(PieceColor.BLACK);
        board[0][7] = new Piece(PieceColor.BLACK);
        board[0][9] = new Piece(PieceColor.BLACK);
        board[1][0] = new Piece(PieceColor.BLACK);
        board[1][2] = new Piece(PieceColor.BLACK);
        board[1][4] = new Piece(PieceColor.BLACK);
        board[1][6] = new Piece(PieceColor.BLACK);
        board[1][8] = new Piece(PieceColor.BLACK);
        board[2][1] = new Piece(PieceColor.BLACK);
        board[2][3] = new Piece(PieceColor.BLACK);
        board[2][5] = new Piece(PieceColor.BLACK);
        board[2][7] = new Piece(PieceColor.BLACK);
        board[2][9] = new Piece(PieceColor.BLACK);
        board[3][0] = new Piece(PieceColor.BLACK);
        board[3][2] = new Piece(PieceColor.BLACK);
        board[3][4] = new Piece(PieceColor.BLACK);
        board[3][6] = new Piece(PieceColor.BLACK);
        board[3][8] = new Piece(PieceColor.BLACK);

        board[6][1] = new Piece(PieceColor.WHITE);
        board[6][3] = new Piece(PieceColor.WHITE);
        board[6][5] = new Piece(PieceColor.WHITE);
        board[6][7] = new Piece(PieceColor.WHITE);
        board[6][9] = new Piece(PieceColor.WHITE);
        board[7][0] = new Piece(PieceColor.WHITE);
        board[7][2] = new Piece(PieceColor.WHITE);
        board[7][4] = new Piece(PieceColor.WHITE);
        board[7][6] = new Piece(PieceColor.WHITE);
        board[7][8] = new Piece(PieceColor.WHITE);
        board[8][1] = new Piece(PieceColor.WHITE);
        board[8][3] = new Piece(PieceColor.WHITE);
        board[8][5] = new Piece(PieceColor.WHITE);
        board[8][7] = new Piece(PieceColor.WHITE);
        board[8][9] = new Piece(PieceColor.WHITE);
        board[9][0] = new Piece(PieceColor.WHITE);
        board[9][2] = new Piece(PieceColor.WHITE);
        board[9][4] = new Piece(PieceColor.WHITE);
        board[9][6] = new Piece(PieceColor.WHITE);
        board[9][8] = new Piece(PieceColor.WHITE);
    }

    public void printBoard(){
        for (int i =0; i<board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == null) {
                    System.out.print("| ");
                } else {
                    System.out.print("|");
                    System.out.print(board[i][j].getColor().equals(PieceColor.WHITE) ? "O" : RED + "O" + RESET);
                }
            }
            System.out.println("|");
        }
    }
}
