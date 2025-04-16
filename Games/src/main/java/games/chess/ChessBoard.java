package games.chess;

import games.chess.beans.Move;
import games.chess.beans.pieces.*;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {
    private ChessPiece[][] board;
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String[] columnIndexToLetter = new String[]{"a","b","c","d","e","f","g","h"};
    public static final Map<String,Integer> letterToColumnIndex = new HashMap<>(8);

    static{
        for (int i = 0; i < columnIndexToLetter.length; i++) {
            letterToColumnIndex.put(columnIndexToLetter[i],i);
        }
    }

    public ChessBoard() {
        board = new ChessPiece[8][8];
        initializeBoard();
    }

    /*public static int[] parsePlayerMoveInAlgebraicNotation(String playerMove, boolean isWhite) {
        int[] matrixCoordinates = new int[4];
        List<String> piecesLetters = List.of("K","Q","R","B","N");
        String firstLetter = String.valueOf(playerMove.charAt(0));
        if (piecesLetters.contains(firstLetter)){
            // move a piece which is not a pawn

            ChessPiece pieceToMove = findPiece(firstLetter, isWhite);
        } else{
            // move a pawn
        }

    }

    public ChessPiece findPiece(String pieceLetter, boolean isWhite){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != null){
                    ChessPiece piece = board[i][j];
                    // fixme: this finds the first piece with same letter and same color
                    if(piece.getLetter().equals(pieceLetter) && ((piece.isWhite() && isWhite) || (!piece.isWhite() && !isWhite))){
                        return piece;
                    }
                }
            }
        }
        return null;
    }*/

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

    public static Integer getBoardRowFromRowIndex(Integer rowIndex){
        return 8+rowIndex;
    }

    public static Integer getColumnIndexFromCoordinate(String columnLetter){
        return letterToColumnIndex.get(columnLetter);
    }

    public static String getBoardColumnFromColumnIndex(Integer columnIndex){
        return columnIndexToLetter[columnIndex];
    }

    public void printBoardWithLetters(){
        System.out.println("   a b c d e f g h");
        for (int i =0; i<board.length; i++) {
            System.out.print(8-i + " ");
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == null) {
                    System.out.print("| ");
                } else {
                    System.out.print("|");
                    System.out.print(board[i][j].isWhite() ? board[i][j].getLetter() : RED + board[i][j].getLetter() + RESET);
                }
            }
            System.out.print("| ");
            System.out.print(8-i + "\n");
        }
        System.out.println("   a b c d e f g h");
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

    //todo: check the row for castling?
    //todo: check the first cell in the diagonal for capturing en-passant?
    public boolean isTrajectoryBlocked(ChessPiece pieceToMove, int startX, int startY, int endX, int endY) {
        //Pieces cannot move over one another (except for the knight)
        if (!(pieceToMove instanceof Knight)) {
            int directionAlongX = Integer.signum(endX - startX);
            int directionAlongY = Integer.signum(endY - startY);

            int currentX = startX + directionAlongX;
            int currentY = startY + directionAlongY;

            while (currentX != endX || currentY != endY) {
                if (board[currentX][currentY] != null) {
                    return true;
                }
                currentX += directionAlongX;
                currentY += directionAlongY;
            }
        }
        return false;
    }

    public static Move parsePlayerMoveFromBoardCoordinates(String playerMove){
        String startingPosition = playerMove.substring(0,2);
        String endingPosition = playerMove.substring(2,4);

        String startColumnLetter = String.valueOf(startingPosition.charAt(0));
        Integer startRow = Integer.valueOf(startingPosition.substring(1,2));
        String endColumnLetter = String.valueOf(endingPosition.charAt(0));
        Integer endRow = Integer.valueOf(endingPosition.substring(1,2));

        int startX = getRowIndexFromCoordinate(startRow);
        int startY = getColumnIndexFromCoordinate(startColumnLetter);
        int endX = getRowIndexFromCoordinate(endRow);
        int endY = getColumnIndexFromCoordinate(endColumnLetter);

        return new Move(startX, startY, endX, endY);
    }
}
