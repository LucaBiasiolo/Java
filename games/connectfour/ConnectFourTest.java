package games.connectfour;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConnectFourTest {

    private static ConnectFourGame connectFourGame;

    @BeforeAll
    public static void createNewGame(){
        connectFourGame = new ConnectFourGame();
    }

    @Test
    public void printGameMatrixTest(){
        connectFourGame.printGameMatrix();
    }

    @Test
    public void populateGameBoardTest(){
        connectFourGame.populateGameBoardColumn(0,1);
        connectFourGame.populateGameBoardColumn(3,2);
        connectFourGame.populateGameBoardColumn(6,2);
        connectFourGame.populateGameBoardColumn(3,1);

        assertEquals(1, connectFourGame.getGameMatrix()[5][0]);
        assertEquals(2, connectFourGame.getGameMatrix()[5][3]);
        assertEquals(2, connectFourGame.getGameMatrix()[5][6]);
        assertEquals(1, connectFourGame.getGameMatrix()[4][3]);
    }
}
