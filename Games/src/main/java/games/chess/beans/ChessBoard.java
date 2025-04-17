package games.chess.beans;

import games.chess.ChessBoardUtil;
import games.chess.beans.pieces.*;

import java.util.regex.Pattern;

public class ChessBoard {
    private ChessPiece[][] board;
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    public ChessBoard() {
        board = new ChessPiece[8][8];
        initializeBoard();
    }

    public Move parsePlayerMoveInAlgebraicNotation(String playerMove, ChessColor playerColor) {

        //todo: ignore "x", check and checkmate symbols. These cases will be checked anyway
        boolean isPawnMove = Pattern.matches("^([a-h][1-8])$", playerMove);
        boolean isNonPawnMove = Pattern.matches("^([KQRBN][a-h][1-8])$", playerMove); // pattern for other pieces other that pawn
        boolean pawnCapturing = Pattern.matches("^([a-h]x[a-h][1-8][+#]?)$", playerMove); // pattern for pawn capturing and promotion
        boolean nonPawnCapturing = Pattern.matches("^([KQRBN]x[a-h][1-8])$", playerMove);

        //todo: add disambiguation cases

        // special moves
        Pattern kingSideCastlingPattern = Pattern.compile("^(O-O)$");
        Pattern queenSideCastlingPattern = Pattern.compile("^(O-O-O)$");
        boolean pawnMoveWithPromotion = Pattern.matches("^([a-h][1-8](=[QRBN])?[+#]?)$", playerMove); // pattern for pawn move and promotion

        if (isPawnMove){
            return parsePawnMoveWithoutCapturing(playerMove, playerColor);
        } else if (isNonPawnMove){
            return parseNonPawnMoveWithoutCapturing(playerMove, playerColor);
        }

        /*List<String> piecesLetters = List.of("K","Q","R","B","N");
        String firstLetter = String.valueOf(playerMove.charAt(0)); // todo: add other cases in which there's also starting file and rank
        String endBoardColumn = String.valueOf(playerMove.charAt(1));
        int endMatrixColumn = ChessBoardUtil.fromBoardColumnToMatrixColumn(endBoardColumn);
        int endMatrixRow = Integer.parseInt(String.valueOf(playerMove.charAt(2)));
        if (piecesLetters.contains(firstLetter)){
            // move a piece which is not a pawn
            //1.Nf3 Nf6

            int[] startMatrixCoordinates = findPieceCoordinates(firstLetter, playerColor);
            if (startMatrixCoordinates != null) {
                return new Move(startMatrixCoordinates[0], startMatrixCoordinates[1], endMatrixRow, endMatrixColumn);
            } else{
                // piece not found
                // todo: reprompt user for move adding more detail
            }
        } else{
            // move a pawn

            Pawn pawnToMove = findPawn(playerColor);
        }*/

        return null;
    }

    private Move parseNonPawnMoveWithoutCapturing(String playerMove, ChessColor playerColor){
        // Nf5
        String pieceLetter = String.valueOf(playerMove.charAt(0));
        String endBoardColumn = String.valueOf(playerMove.charAt(1));
        int endMatrixColumn = ChessBoardUtil.fromBoardColumnToMatrixColumn(endBoardColumn);
        int endMatrixRow = Integer.parseInt(String.valueOf(playerMove.charAt(2)));

        int[] startMatrixCoordinates = findPieceStartingCoordinates(pieceLetter, playerColor);

        return new Move(startMatrixCoordinates[0], startMatrixCoordinates[1], endMatrixRow, endMatrixColumn);
    }

    private Move parsePawnMoveWithoutCapturing(String playerMove, ChessColor playerColor) {
        String boardColumn = String.valueOf(playerMove.charAt(0));
        Integer boardEndRow = Integer.parseInt(String.valueOf(playerMove.charAt(1)));

        int matrixEndRow = ChessBoardUtil.convertRowInOtherNotation(boardEndRow);
        Integer matrixColumn = ChessBoardUtil.fromBoardColumnToMatrixColumn(boardColumn);

        Pawn pawn;
        int matrixStartRow;
        try {
            if (playerColor.equals(ChessColor.WHITE)) {
                if (matrixEndRow == 4) {
                    // go back 1 or 2 squares. Start with 1 square
                    pawn = (Pawn) getPiece(matrixEndRow + 1, matrixColumn);
                    matrixStartRow = matrixEndRow + 1;
                    if (pawn == null) {
                        pawn = (Pawn) getPiece(matrixEndRow + 2, matrixColumn);
                        matrixStartRow = matrixEndRow + 2;
                    }
                } else {
                    // go back 1 square
                    pawn = (Pawn) getPiece(matrixEndRow + 1, matrixColumn);
                    matrixStartRow = matrixEndRow + 1;
                }
            } else {
                if (matrixEndRow == 3) {
                    // go back 1 or 2 squares. Start with 1 square
                    pawn = (Pawn) getPiece(matrixEndRow - 1, matrixColumn);
                    matrixStartRow = matrixEndRow - 1;
                    if (pawn == null) {
                        pawn = (Pawn) getPiece(matrixEndRow - 2, matrixColumn);
                        matrixStartRow = matrixEndRow - 2;
                    }
                } else {
                    // go back 1 square
                    pawn = (Pawn) getPiece(matrixEndRow - 1, matrixColumn);
                    matrixStartRow = matrixEndRow - 1;
                }
            }
            return new Move(pawn, matrixStartRow, matrixColumn, matrixEndRow, matrixColumn);
        } catch (ClassCastException classCastException){ // if a piece is found that is not a Pawn
            System.err.println("Error: pawn movement invalid. Please retry");
            return null;
        }
    }

    public int[] findPieceStartingCoordinates(String pieceLetter, ChessColor playerColor){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != null){
                    ChessPiece piece = board[i][j];
                    // fixme: this finds the first piece with same letter and same color. What if there are more?
                    if(piece.getLetter().equals(pieceLetter) && piece.getColor().equals(playerColor)){
                        return new int[]{i,j};
                    }
                }
            }
        }
        return null;
    }

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
