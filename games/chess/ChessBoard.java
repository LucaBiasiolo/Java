package games.chess;

import games.chess.pieces.*;

public class ChessBoard {
    private ChessPiece[][] board;

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

    public void updateBoard(){

    }

    /*public boolean movePiece(int startX, int startY, int endX, int endY) {
        ChessPiece piece = board[startX][startY];
        if (piece != null && piece.isValidMove(startX, startY, endX, endY, board)) {
            board[endX][endY] = piece;
            board[startX][startY] = null;
            System.out.println("Moved " + piece + "from (" + startX + "," + startY + ") to (" + endX + "," + endY + ")");
            return true;
        }
        // Invalid move
        System.out.println("Invalid move for from (" + startX + "," + startY + ") to (" + endX + "," + endY + ")");
        return false;
    }*/

    public ChessPiece getPiece(int x, int y) {
        return board[x][y];
    }
}
