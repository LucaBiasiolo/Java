package games.chess;

import games.chess.pieces.ChessPiece;
import games.chess.pieces.Pawn;
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
        player1 = new Player("Luca White", true, chessBoard);
        player2 = new Player("Luca Black", false, chessBoard);
    }

    @Test
    public void printGameBoardTest(){
        chessBoard.printBoard();
    }

    @Test
    public void testPawnMovement(){
        chessBoard.printBoard();
        ChessPiece pawn = chessBoard.getPiece(6, 0);
        assertNotNull(pawn);
        assertInstanceOf(Pawn.class, pawn);
        player1.movePiece(chessBoard,6,0,5,0);
        chessBoard.printBoard();
        assertNull(chessBoard.getPiece(6,0));
        pawn = chessBoard.getPiece(5,0);
        assertNotNull(pawn);
        assertInstanceOf(Pawn.class, pawn);
    }
}
