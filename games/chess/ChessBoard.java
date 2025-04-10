package games.chess;

import games.chess.pieces.*;

public class ChessBoard {
    private ChessPiece[][] board;
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    public ChessBoard() {
        board = new ChessPiece[8][8];
        initializeBoard();
    }

    private void initializeBoard() {
        // Pawns
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(true);
            board[1][i] = new Pawn(false);
        }
        // Rooks
        board[0][0] = new Rook(false);
        board[0][7] = new Rook(false);
        board[7][0] = new Rook(true);
        board[7][7] = new Rook(true);

        // Knights
        board[0][1] = new Knight(false);
        board[0][6] = new Knight(false);
        board[7][1] = new Knight(true);
        board[7][6] = new Knight(true);

        // Bishops
        board[0][2] = new Bishop(false);
        board[0][5] = new Bishop(false);
        board[7][2] = new Bishop(true);
        board[7][5] = new Bishop(true);
        // Queens
        board[0][3] = new Queen(false);
        board[7][3] = new Queen(true);

        // Kings
        board[0][4] = new King(false);
        board[7][4] = new King(true);
    }

    public void printBoard(){
        for (ChessPiece[] boardRow : board) {
            for (int j = 0; j < board.length; j++) {
                if (boardRow[j] == null) {
                    System.out.print(" ");
                } else {
                    System.out.print(boardRow[j].isWhite() ? boardRow[j].getIcon() : RED + boardRow[j].getIcon() + RESET);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public ChessPiece getPiece(int x, int y) {
        return board[x][y];
    }

    public ChessPiece[][] getBoard() {
        return board;
    }
}
