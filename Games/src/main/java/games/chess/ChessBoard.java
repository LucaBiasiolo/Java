package games.chess;

import games.chess.pieces.*;

import java.util.HashMap;
import java.util.Map;

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

    public static Integer getRowIndexFromCoordinate(Integer startRow){
        return 8-startRow;
    }

    public static Integer getColumnIndexFromCoordinate(String columnLetter){
        Map<String, Integer> mappingColumnLetterToIndex = new HashMap<>();
        mappingColumnLetterToIndex.put("a",0);
        mappingColumnLetterToIndex.put("b",1);
        mappingColumnLetterToIndex.put("c",2);
        mappingColumnLetterToIndex.put("d",3);
        mappingColumnLetterToIndex.put("e",4);
        mappingColumnLetterToIndex.put("f",5);
        mappingColumnLetterToIndex.put("g",6);
        mappingColumnLetterToIndex.put("h",7);
        return mappingColumnLetterToIndex.get(columnLetter);
    }

    public void printBoard(){
        for (ChessPiece[] boardRow : board) {
            for (int j = 0; j < board.length; j++) {
                if (boardRow[j] == null) {
                    System.out.print(" ");
                } else {
                    System.out.print(boardRow[j].isWhite() ? boardRow[j].getLetter() : RED + boardRow[j].getLetter() + RESET);
                }
            }
            System.out.println();
        }
        System.out.println("abcdefgh");
    }

    public ChessPiece getPiece(int x, int y) {
        return board[x][y];
    }

    public ChessPiece[][] getBoard() {
        return board;
    }

    public boolean isGameOver() {
        // check if game is in checkmate, stalemate or draw condition
        return false;
    }

    public boolean isTrajectoryBlocked(ChessPiece pieceToMove, int startX, int startY, int endX, int endY) {
        //todo: check if there are pieces along the way. Pieces cannot move over one another (except for the knight)
        if (pieceToMove instanceof Knight){
            return false;
        } else if (pieceToMove instanceof Rook || pieceToMove instanceof Bishop || pieceToMove instanceof  Queen){
            // linear movements, use directions and check every cell along the trajectory
        } else if (pieceToMove instanceof King){
            // check only the final square if it is a normal movement
            // check the row for castling
        } else if (pieceToMove instanceof  Pawn){
            // check the first one or two frontal cells for normal movement
            // check the first cell in the diagonal
        }
        return false;
    }
}
