package games.checkers;

import games.PieceColor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CheckersTest {

    private static CheckersBoard checkersBoard;
    private static Player player1;
    private static Player player2;

    @BeforeAll
    public static void createCheckersGame(){
        checkersBoard = new CheckersBoard();
        player1 = new Player("Luca White", PieceColor.WHITE);
        player2 = new Player("Luca Black", PieceColor.BLACK);
    }

    @Test
    public void testPrintCheckersBoard(){
        checkersBoard.printBoard();
    }

    @Test
    public void testMovePiece(){
        //player1.movePiece(checkersBoard, );
    }
}
