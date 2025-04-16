package games.chess.beans;

import games.chess.beans.pieces.*;

public class ChessBoard {
    private ChessPiece[][] board;
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

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
            board[6][i] = new Pawn(ChessColor.WHITE);
            board[1][i] = new Pawn(ChessColor.BLACK);
        }
        // Rooks
        board[0][0] = new Rook(ChessColor.BLACK);
        board[0][7] = new Rook(ChessColor.BLACK);
        board[7][0] = new Rook(ChessColor.WHITE);
        board[7][7] = new Rook(ChessColor.WHITE);

        // Knights
        board[0][1] = new Knight(ChessColor.BLACK);
        board[0][6] = new Knight(ChessColor.BLACK);
        board[7][1] = new Knight(ChessColor.WHITE);
        board[7][6] = new Knight(ChessColor.WHITE);

        // Bishops
        board[0][2] = new Bishop(ChessColor.BLACK);
        board[0][5] = new Bishop(ChessColor.BLACK);
        board[7][2] = new Bishop(ChessColor.WHITE);
        board[7][5] = new Bishop(ChessColor.WHITE);
        // Queens
        board[0][3] = new Queen(ChessColor.BLACK);
        board[7][3] = new Queen(ChessColor.WHITE);

        // Kings
        board[0][4] = new King(ChessColor.BLACK);
        board[7][4] = new King(ChessColor.WHITE);
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
                    System.out.print(board[i][j].getColor().equals(ChessColor.WHITE) ? board[i][j].getLetter() : RED + board[i][j].getLetter() + RESET);
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
}
