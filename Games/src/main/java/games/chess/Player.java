package games.chess;

import games.chess.beans.Move;
import games.chess.beans.pieces.*;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private List<ChessPiece> pieces = new ArrayList<>(16); // todo: play "Pick up the pieces" by Average White Band
    private boolean isWhite;
    private List<Move> moveLog = new ArrayList<>();

    public Player(String name, boolean isWhite, ChessBoard chessBoard) {
        this.name = name;
        this.isWhite = isWhite;
        //associatePieces(chessBoard);
    }

    /*public void associatePieces(ChessBoard chessBoard){
        ChessPiece[][] board = chessBoard.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != null){
                    if(board[i][j].isWhite() && isWhite) {
                        pieces.add(board[i][j]);
                    } else if (!board[i][j].isWhite() && !isWhite){
                        pieces.add(board[i][j]);
                    }
                }
            }
        }
    }*/


    //1.Nf3 Nf6 2.c4 g6 3.Nc3 Bg7 4.d4 O-O 5.Bf4 d5 6.Qb3 dxc4 7.Qxc4 c6 8.e4 Nbd7 9.Rd1 Nb6 10.Qc5 Bg4 11.Bg5 Na4 12.Qa3 Nxc3 13.bxc3 Nxe4 14.Bxe7 Qb6 15.Bc4 Nxc3 16.Bc5 Rfe8+ 17.Kf1 Be6 18.Bxb6 Bxc4+ 19.Kg1 Ne2+ 20.Kf1 Nxd4+ 21.Kg1 Ne2+ 22.Kf1 Nc3+ 23.Kg1 axb6 24.Qb4 Ra4 25.Qxb6 Nxd1 26.h3 Rxa2 27.Kh2 Nxf2 28.Re1 Rxe1 29.Qd8+ Bf8 30.Nxe1 Bd5 31.Nf3 Ne4 32.Qb8 b5 33.h4 h5 34.Ne5 Kg7 35.Kg1 Bc5+ 36.Kf1 Ng3+ 37.Ke1 Bb4+ 38.Kd1 Bb3+ 39.Kc1 Ne2+ 40.Kb1 Nc3+ 41.Kc1 Rc2# 0-1
//    public boolean movePieceWithAlgebraicNotation(ChessBoard chessBoard, String playerMove){
//        Pattern pattern = Pattern.compile("^[KQRBN]?[a-h]?x?[a-h][1-8]$|^0-0$|^0-0-0$");
//        Matcher matcher = pattern.matcher(playerMove);
//        if(matcher.matches()){
//            //ChessBoard.parsePlayerMoveInAlgebraicNotation(playerMove, isWhite);
//        }
//    }

    public boolean movePieceWithBoardCoordinates(ChessBoard chessBoard, String playerMove){
        Move move = ChessBoard.parsePlayerMoveFromBoardCoordinates(playerMove);
        return movePieceWithMatrixCoordinates(chessBoard, move);
    }

    public boolean movePieceWithMatrixCoordinates(ChessBoard chessBoard, Move move){
        int startRow = move.getStartRow();
        int startColumn = move.getStartColumn();
        int endRow = move.getEndRow();
        int endColumn = move.getEndColumn();

        ChessPiece pieceToMove = chessBoard.getPiece(startRow, startColumn);
        if (pieceToMove != null){
            if((isWhite && pieceToMove.isWhite()) || (!isWhite && !pieceToMove.isWhite())) {
                if (pieceToMove.isValidMove(startX, startY, endX, endY, chessBoard.getBoard())) {  // check if movement complains with the piece's movement rules
                    ChessPiece pieceOnDestination = chessBoard.getBoard()[endX][endY];
                    if (pieceOnDestination == null){
                        if(!chessBoard.isTrajectoryBlocked(pieceToMove, startX, startY, endX, endY)) {
                            //move
                            chessBoard.getBoard()[endX][endY] = pieceToMove;
                            chessBoard.getBoard()[startX][startY] = null;
                            System.out.printf("Moved %s from (%d,%d) to (%d,%d)\n", pieceToMove, startX, startY, endX, endY);
                            return true;
                        } else{
                            System.out.println("Trajectory blocked by another piece");
                        }
                    } else {
                        if (pieceOnDestination.isWhite() && pieceToMove.isWhite() ||
                                !pieceOnDestination.isWhite() && !pieceToMove.isWhite()) {
                            // movement not possible, there's a piece with the same color on the final position
                            System.err.printf("Movement %s from (%d,%d) to (%d,%d) not possible\n", pieceToMove, startX, startY, endX, endY);
                        }else{
                            // there's a piece with different color in the finall position.
                            // capture
                            chessBoard.getBoard()[endX][endY] = pieceToMove;
                            chessBoard.getBoard()[startX][startY] = null;
                            // todo: remove piece from other player's pieces ?
                            return true;
                        }
                    }
                } else{
                    System.out.println("Movement not valid for piece " + pieceToMove);
                }
            } else{
                System.out.println("You cannot move pieces of the other player");
            }
        } else{
            System.out.println("No piece found in starting position");
        }
        return false;
    }

    public void castling(King king, Rook rook){
        // if neither rook nor the king were moved and the line along them is free, perform castling
        // The king cannot be in check when castling, nor can it move through or into a square attacked by an opponent's piece.
        // move the king two pieces towards the rook and the rook on the cell the king has passed
    }

    public void promotePawn(ChessBoard board, int xPosition, int yPosition){
        // This would typically involve replacing the Pawn object with the new piece object on the board
        // For simplicity, we can just set the position of the new piece to the Pawn's position
        // and remove the Pawn from the board.
        boolean isWhite = xPosition == 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("The pawn can be promoted! What piece do you want to promote it to?");
        System.out.println("Choose between Q (Queen), R (Rook), B (Bishop), K (Knight) :");
        String playerMove = scanner.nextLine();
        Pattern pattern = Pattern.compile("[QRBK]");
        Matcher matcher = pattern.matcher(playerMove);
        while (!matcher.matches()) {
            System.out.println("Please insert a valid choice");
            playerMove = scanner.nextLine();
        }
        ChessPiece pieceToPromoteTo = board.getPiece(xPosition, yPosition);
        pieceToPromoteTo = switch (playerMove) {
            case "Q" -> new Queen(isWhite);
            case "R" -> new Rook(isWhite);
            case "B" -> new Bishop(isWhite);
            case "K" -> new Knight(isWhite);
            default -> pieceToPromoteTo;
        };
        board.getBoard()[xPosition][yPosition] = pieceToPromoteTo;
    }
}
