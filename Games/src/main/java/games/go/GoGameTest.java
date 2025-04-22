package games.go;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class GoGameTest {

    private static GoBoard board;

    @BeforeAll
    public static void createGoBoard(){
        board = new GoBoard(19);
    }

    @Test
    public void printGoBoardTest(){
        board.printBoard();
    }
}
