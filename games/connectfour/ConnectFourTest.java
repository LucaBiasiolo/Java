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
        connectFourGame.playerMove(0,1);
        connectFourGame.playerMove(3,2);
        connectFourGame.playerMove(6,2);
        connectFourGame.playerMove(3,1);

        assertEquals(1, connectFourGame.getGameMatrix()[5][0]);
        assertEquals(2, connectFourGame.getGameMatrix()[5][3]);
        assertEquals(2, connectFourGame.getGameMatrix()[5][6]);
        assertEquals(1, connectFourGame.getGameMatrix()[4][3]);
    }

    public static void main(String[] args) {
        ConnectFourGame connectFourGame = new ConnectFourGame();
        connectFourGame.play();
    }
}
