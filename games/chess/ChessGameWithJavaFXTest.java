package games.chess;

import games.chess.pieces.ChessPiece;
import games.chess.pieces.Knight;
import games.chess.pieces.Pawn;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChessGameWithJavaFXTest {

    private static ChessBoard chessBoard;
    private static Player player1;
    private static Player player2;

    @BeforeAll
    public static void createChessBoard(){
        chessBoard = new ChessBoard();
        player1 = new Player("Luca White", true, chessBoard);
        player2 = new Player("Luca Black", false, chessBoard);
    }

    @Test
    public void printGameBoardTest(){
        chessBoard.printBoard();
    }

    @Test
    public void testPawnSingleMovement() {
        chessBoard.printBoard();
        ChessPiece pawn = chessBoard.getPiece(6, 0);
        assertNotNull(pawn);
        assertInstanceOf(Pawn.class, pawn);
        player1.movePiece(chessBoard, 6, 0, 5, 0);
        chessBoard.printBoard();
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
        player1.movePiece(chessBoard, 6,1,4,1);
        chessBoard.printBoard();
        assertNull(chessBoard.getPiece(6,1));
        assertNotNull(chessBoard.getPiece(4,1));
        assertInstanceOf(Pawn.class, chessBoard.getPiece(4,1));
    }

    @Test
    public void testKnightMovement(){
        assertNotNull(chessBoard.getPiece(7,1));
        assertInstanceOf(Knight.class, chessBoard.getPiece(7,1));
        assertNull(chessBoard.getPiece(5,2));
        player1.movePiece(chessBoard, 7, 1,5,2);
        chessBoard.printBoard();
        assertNull(chessBoard.getPiece(7,1));
        assertNotNull(chessBoard.getPiece(5,2));
        assertInstanceOf(Knight.class, chessBoard.getPiece(5,2));
    }
}
