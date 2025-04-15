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

    public void printBoardWithLetters(){
        System.out.println("   abcdefgh");
        for (int i =0; i<board.length; i++) {
            System.out.print(8-i + " |");
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == null) {
                    System.out.print(" ");
                } else {
                    System.out.print(board[i][j].isWhite() ? board[i][j].getLetter() : RED + board[i][j].getLetter() + RESET);
                }
            }
            System.out.print("| ");
            System.out.print(8-i + "\n");
        }
        System.out.println("   abcdefgh");
    }

    public void printBoardWithIcons(){
        for (int i =0; i<board.length; i++) {
            System.out.print(8-i + " ");
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == null) {
                    System.out.print(" ");
                } else {
                    System.out.print(board[i][j].isWhite() ? board[i][j].getIcon(): RED + board[i][j].getIcon() + RESET);
                }
            }
            System.out.println();
        }
        System.out.println("  abcdefgh");
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

    public static int[] parsePlayerMove(String playerMove){
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

        return new int[]{startX, startY, endX, endY};
    }
}
