package games.chess.beans;

import games.chess.ChessBoardUtil;
import games.chess.beans.pieces.*;

public class ChessBoard {
    private ChessPiece[][] board;
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    public ChessBoard() {
        board = new ChessPiece[8][8];
        initializeBoard();
    }

    public Move parsePlayerMoveInAlgebraicNotation(String playerMove, ChessColor playerColor) {
        playerMove = playerMove.replace("x", "")
                .replace("=","")
                .replace("+", "")
                .replace("#", "");

        String firstLetter = String.valueOf(playerMove.charAt(0));
        String secondCharacter = String.valueOf(playerMove.charAt(1));

        if (firstLetter.matches("0")){
            if (playerMove.matches("0-0")){
                // king-side castling
            } else if (playerMove.matches("0-0-0")){
                // queen-side castling
            }
        } else if (firstLetter.matches("[KQRBN]")){
            // non-pawn movement
            // todo: add case of double disambiguation (e.g. Qh4e1)
            if (secondCharacter.matches("[a-h]")){
                String thirdCharacter = String.valueOf(playerMove.charAt(2));
                if(thirdCharacter.matches("[1-8]")) {
                    // standard non-pawn movement (e.g. Nh3)
                    if (playerMove.length() == 3) {
                        return parseStandardNonPawnMove(playerMove, playerColor);
                    } else if (playerMove.length() == 5){
                        // double disambiguation (e.g. Qh4e1)
                        return parseNonPawnMoveWithDoubleDisambiguation(playerMove, playerColor);
                    }
                } else if (thirdCharacter.matches("[a-h]")){
                    // non-standard non-pawn movement with disambiguation (e.g. Nbd7)
                    return parseNonPawnMoveWithColumnDisambiguation(playerMove, playerColor);
                }
            } else if (secondCharacter.matches("[1-8]")){
                // non-standard non-pawn movement with disambiguation (e.g. R1a3)
                return parseNonPawnMoveWithRowDisambiguation(playerMove, playerColor);
            }
        } else if (firstLetter.matches("[a-h]")){
            // pawn movement
            // todo: add pawn promotion
            if (secondCharacter.matches("[1-8]")){
                // standard pawn move
                return parsePawnMoveWithoutCapturing(playerMove, playerColor);
            } else if (secondCharacter.matches("[a-h]")){
                return parsePawnMoveWithCapturing(playerMove, playerColor);
            }
        }
        return null;
    }

    private Move parseNonPawnMoveWithRowDisambiguation(String playerMove, ChessColor playerColor) {
        // non-standard non-pawn movement with disambiguation (e.g. R1a3)
        String pieceLetter = String.valueOf(playerMove.charAt(0));
        int startBoardRow = Integer.parseInt(String.valueOf(playerMove.charAt(1)));
        int startMatrixColumn = -1;
        String endBoardColumn = String.valueOf(playerMove.charAt(2));
        int endBoardRow = Integer.parseInt(String.valueOf(playerMove.charAt(3)));

        for (int j = 0; j < board.length; j++) {
            if (board[startBoardRow][j] != null){
                ChessPiece piece = board[startBoardRow][j];
                if (piece.getLetter().equals(pieceLetter) && piece.getColor().equals(playerColor)){
                    startMatrixColumn = j;
                }
            }
        }

        Integer startMatrixRow = ChessBoardUtil.convertRowInOtherNotation(startBoardRow);
        Integer endMatrixRow = ChessBoardUtil.convertRowInOtherNotation(endBoardRow);
        Integer endMatrixColumn = ChessBoardUtil.fromBoardColumnToMatrixColumn(endBoardColumn);

        return new Move(startMatrixRow, startMatrixColumn, endMatrixRow, endMatrixColumn);
    }

    private Move parseNonPawnMoveWithColumnDisambiguation(String playerMove, ChessColor playerColor) {
        // non-standard non-pawn movement with disambiguation (e.g. Nbd7)
        String pieceLetter = String.valueOf(playerMove.charAt(0));
        String startBoardColumn = String.valueOf(playerMove.charAt(1));
        String endBoardColumn = String.valueOf(playerMove.charAt(2));
        Integer endBoardRow = Integer.parseInt(String.valueOf(playerMove.charAt(3)));
        Integer startMatrixColumn = ChessBoardUtil.fromBoardColumnToMatrixColumn(startBoardColumn);
        Integer endMatrixColumn = ChessBoardUtil.fromBoardColumnToMatrixColumn(endBoardColumn);
        Integer endMatrixRow = ChessBoardUtil.convertRowInOtherNotation(endBoardRow);
        int startMatrixRow = -1;

        for (int i = 0; i < board.length; i++) {
            if (board[i][startMatrixColumn] != null){
                ChessPiece piece= board[i][startMatrixColumn];
                if (piece.getLetter().equals(pieceLetter) && piece.getColor().equals(playerColor)){
                    startMatrixRow = i;
                }
            }
        }
        return new Move(startMatrixRow, startMatrixColumn, endMatrixRow, endMatrixColumn);
    }

    private Move parseNonPawnMoveWithDoubleDisambiguation(String playerMove, ChessColor playerColor) {
        // double disambiguation (e.g. Qh4e1)
        String pieceLetter = String.valueOf(playerMove.charAt(0));
        String startBoardColumn = String.valueOf(playerMove.charAt(1));
        Integer startBoardRow = Integer.parseInt(String.valueOf(playerMove.charAt(2)));
        String endBoardColumn = String.valueOf(playerMove.charAt(3));
        Integer endBoardRow = Integer.parseInt(String.valueOf(playerMove.charAt(4)));

        Integer startMatrixColumn = ChessBoardUtil.fromBoardColumnToMatrixColumn(startBoardColumn);
        Integer endMatrixColumn = ChessBoardUtil.fromBoardColumnToMatrixColumn(endBoardColumn);
        Integer endMatrixRow = ChessBoardUtil.convertRowInOtherNotation(endBoardRow);
        int startMatrixRow = ChessBoardUtil.convertRowInOtherNotation(startBoardRow);

        ChessPiece piece = getPiece(startMatrixRow, startMatrixColumn);
        if (piece.getLetter().equals(pieceLetter) && piece.getColor().equals(playerColor)){
            return new Move(piece,startMatrixRow, startMatrixColumn, endMatrixRow, endMatrixColumn);
        }
        return null;
    }

    private Move parsePawnMoveWithCapturing(String playerMove, ChessColor playerColor) {
        //bxc3
        String startBoardColumn = String.valueOf(playerMove.charAt(0));
        String endBoardColumn = String.valueOf(playerMove.charAt(2));

        Integer startMatrixColumn = ChessBoardUtil.fromBoardColumnToMatrixColumn(startBoardColumn);
        Integer endMatrixColumn = ChessBoardUtil.fromBoardColumnToMatrixColumn(endBoardColumn);
        int endBoardRow = Integer.parseInt(String.valueOf(playerMove.charAt(3)));
        Integer endMatrixRow = ChessBoardUtil.convertRowInOtherNotation(endBoardRow);

        int startBoardRow;
        if (playerColor.equals(ChessColor.WHITE)){
            startBoardRow = endBoardRow -1;
        } else{
            startBoardRow = endBoardRow +1;
        }

        Integer startMatrixRow = ChessBoardUtil.convertRowInOtherNotation(startBoardRow);

        return new Move(startMatrixRow, startMatrixColumn, endMatrixRow, endMatrixColumn);
    }

    private Move parseStandardNonPawnMove(String playerMove, ChessColor playerColor){
        // Nf5
        String pieceLetter = String.valueOf(playerMove.charAt(0));
        String endBoardColumn = String.valueOf(playerMove.charAt(1));
        int endMatrixColumn = ChessBoardUtil.fromBoardColumnToMatrixColumn(endBoardColumn);
        int endBoardRow = Integer.parseInt(String.valueOf(playerMove.charAt(2)));
        int endMatrixRow = ChessBoardUtil.convertRowInOtherNotation(endBoardRow);

        int[] startMatrixCoordinates = findPieceStartingCoordinates(pieceLetter, endMatrixRow, endMatrixColumn, playerColor);
        if (startMatrixCoordinates == null){
            return null;
        }
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

    public int[] findPieceStartingCoordinates(String pieceLetter, int endMatrixRow, int endMatrixColumn, ChessColor playerColor){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != null){
                    ChessPiece piece = board[i][j];
                    if(piece.getLetter().equals(pieceLetter) && piece.getColor().equals(playerColor)){
                        if (piece.isMoveValid(i,j, endMatrixRow, endMatrixColumn)) {
                            return new int[]{i, j};
                        }
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
