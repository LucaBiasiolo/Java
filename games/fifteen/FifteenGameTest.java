package games.fifteen;

import org.junit.jupiter.api.Test;

public class FifteenGameTest {

    @Test
    public void testGridCreationAndPrint(){
        GameBoard gameBoard = new GameBoard();
        gameBoard.populateGrid();
        gameBoard.print();
    }
}
