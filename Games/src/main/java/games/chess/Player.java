package games.chess;

import games.chess.pieces.ChessPiece;
import games.chess.pieces.King;
import games.chess.pieces.Rook;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private List<ChessPiece> pieces = new ArrayList<>(16); // todo: play "Pick up the pieces" by Average White Band
    private boolean isWhite;

    public Player(String name, boolean isWhite, ChessBoard chessBoard) {
        this.name = name;
        this.isWhite = isWhite;
        associatePieces(chessBoard);
    }

    public void associatePieces(ChessBoard chessBoard){
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
    }

    public boolean movePieceWithBoardCoordinates(ChessBoard chessBoard, String playerMove){
        int[] matrixCoordinates = ChessBoard.parsePlayerMove(playerMove);
        return movePieceWithMatrixCoordinates(chessBoard, matrixCoordinates[0], matrixCoordinates[1], matrixCoordinates[2], matrixCoordinates[3] );
    }

    public boolean movePieceWithMatrixCoordinates(ChessBoard chessBoard, int startX, int startY, int endX, int endY){
        ChessPiece pieceToMove = chessBoard.getPiece(startX, startY);
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

    public void promotePawn(ChessBoard board, int xPosition, int yPosition, ChessPiece pieceToPromoteTo){
        // Logic for promoting the pawn to a different piece (e.g., Queen, Rook, Bishop, Knight)
        // This would typically involve replacing the Pawn object with the new piece object on the board
        // For simplicity, we can just set the position of the new piece to the Pawn's position
        // and remove the Pawn from the board.
        ChessPiece pawnToPromote = board.getPiece(xPosition, yPosition);
        board.getBoard()[xPosition][yPosition] = pieceToPromoteTo;
    }
}
