package games.checkers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CheckersTest {

    private static CheckersBoard checkersBoard;

    @BeforeAll
    public static void createCheckersGame(){
        checkersBoard = new CheckersBoard();
    }

    @Test
    public void testPrintCheckersBoard(){
        checkersBoard.printBoard();
    }
}
