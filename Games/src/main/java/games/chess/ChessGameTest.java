package games.chess;

import games.chess.beans.ChessColor;
import games.chess.beans.Move;
import games.chess.beans.ChessPiece;
import games.chess.beans.pieces.Knight;
import games.chess.beans.pieces.Pawn;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ChessGameTest {

    private static ChessBoard chessBoard;
    private static Player player1;
    private static Player player2;

    @BeforeAll
    public static void createChessBoard(){
        chessBoard = new ChessBoard();
        player1 = new Player("Luca White", ChessColor.WHITE, chessBoard);
        player2 = new Player("Luca Black", ChessColor.BLACK, chessBoard);
    }

    @Test
    public void printGameBoardTest(){
        chessBoard.printBoardWithLetters();
    }

    @Test
    public void testPawnSingleMovement() {
        chessBoard.printBoardWithLetters();
        ChessPiece pawn = chessBoard.getPiece(6, 0);
        assertNotNull(pawn);
        assertInstanceOf(Pawn.class, pawn);
        player1.movePieceWithMatrixCoordinates(chessBoard, new Move(6, 0, 5, 0));
        chessBoard.printBoardWithLetters();
        assertNull(chessBoard.getPiece(6, 0));
        pawn = chessBoard.getPiece(5, 0);
        assertNotNull(pawn);
        assertInstanceOf(Pawn.class, pawn);
    }

    @Test
    public void testPawnDoubleMovement(){
        assertNotNull(chessBoard.getPiece(6,1));
        assertInstanceOf(Pawn.class, chessBoard.getPiece(6,1));
        assertNull(chessBoard.getPiece(4,1));
        player1.movePieceWithMatrixCoordinates(chessBoard, new Move(6,1,4,1));
        chessBoard.printBoardWithLetters();
        assertNull(chessBoard.getPiece(6,1));
        assertNotNull(chessBoard.getPiece(4,1));
        assertInstanceOf(Pawn.class, chessBoard.getPiece(4,1));
    }

    @Test
    public void testKnightMovement(){
        assertNotNull(chessBoard.getPiece(7,1));
        assertInstanceOf(Knight.class, chessBoard.getPiece(7,1));
        assertNull(chessBoard.getPiece(5,2));
        player1.movePieceWithMatrixCoordinates(chessBoard, new Move(7, 1,5,2));
        chessBoard.printBoardWithLetters();
        assertNull(chessBoard.getPiece(7,1));
        assertNotNull(chessBoard.getPiece(5,2));
        assertInstanceOf(Knight.class, chessBoard.getPiece(5,2));
    }

    @Test
    public void testPieceAlongTheWayMethod(){
        assertFalse(player1.movePieceWithMatrixCoordinates(chessBoard, new Move(7,2,5,0)));
        assertFalse(player1.movePieceWithMatrixCoordinates(chessBoard, new Move(7,0,5,0)));
        assertFalse(player1.movePieceWithMatrixCoordinates(chessBoard,new Move(7,3,5,1)));
        assertFalse(player1.movePieceWithBoardCoordinates(chessBoard, "c1a3"));
        assertFalse(player1.movePieceWithBoardCoordinates(chessBoard,"a1a3"));
        assertFalse(player1.movePieceWithBoardCoordinates(chessBoard,"d1b3"));
        assertFalse(player1.movePieceWithBoardCoordinates(chessBoard,"e1e2"));
        assertTrue(player1.movePieceWithBoardCoordinates(chessBoard,"b1c3"));
    }
}
