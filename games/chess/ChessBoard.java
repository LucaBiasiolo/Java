package games.chess;

import games.chess.pieces.*;

public class ChessBoard {
    private ChessPiece[][] board;

    public ChessBoard() {
        board = new ChessPiece[8][8];
        initializeBoard();
    }

    private void initializeBoard() {
        // Initialize pieces on the board
        // Pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(1,i,true);
            board[6][i] = new Pawn(6,i, false);
        }
        // Rooks
        board[0][0] = new Rook(0, 0, true);
        board[0][7] = new Rook(0, 7, true);
        board[7][0] = new Rook(7, 0, false);
        board[7][7] = new Rook(7, 7, false);
        // Knights
        board[0][1] = new Knight(0, 1, true);
        board[0][6] = new Knight(0, 6, true);
        board[7][1] = new Knight(7, 1, false);
        board[7][6] = new Knight(7, 6, false);
        // Bishops
        board[0][2] = new Bishop(0, 2, true);
        board[0][5] = new Bishop(0, 5, true);
        board[7][2] = new Bishop(7, 2, false);
        board[7][5] = new Bishop(7, 5, false);
        // Queens
        board[0][3] = new Queen(0, 3, true);
        board[7][3] = new Queen(7, 3, false);
        // Kings
        board[0][4] = new King(0, 4, true);
        board[7][4] = new King(7, 4, false);
    }

    public boolean movePiece(int startX, int startY, int endX, int endY) {
        ChessPiece piece = board[startX][startY];
        if (piece != null && piece.isValidMove(startX, startY, endX, endY, board)) {
            board[endX][endY] = piece;
            board[startX][startY] = null;
            return true;
        }
        // Invalid move
        System.out.println("Invalid move for " + piece.getIcon() + " from (" + startX + "," + startY + ") to (" + endX + "," + endY + ")");
        return false;
    }

    public ChessPiece getPiece(int x, int y) {
        return board[x][y];
    }
}
