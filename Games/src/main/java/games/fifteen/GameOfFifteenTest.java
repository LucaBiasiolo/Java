package games.fifteen;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameOfFifteenTest {

    private static GameOfFifteen gameOfFifteen;

    @BeforeAll
    public static void createGrid(){
        gameOfFifteen = new GameOfFifteen();
    }

    @Test
    public  void testGridCreation(){
        assertNotNull(gameOfFifteen);
    }

    @Test
    public void testPrint(){
        gameOfFifteen.printGameBoard();
    }

    @Test
    public void isMovementPossibleTest(){
        for (MoveDirection direction : List.of(MoveDirection.UP, MoveDirection.DOWN, MoveDirection.LEFT, MoveDirection.RIGHT)) {
            System.out.printf("Movement in direction %s : %s %n", direction, gameOfFifteen.isMovementPossible(direction));
        }
    }

    @Test
    public void moveTest(){
        gameOfFifteen.printGameBoard();
        for (MoveDirection direction : List.of(MoveDirection.UP, MoveDirection.DOWN, MoveDirection.LEFT, MoveDirection.RIGHT)) {
            System.out.printf("Movement in direction %s %n", direction);
            gameOfFifteen.move(direction);
            gameOfFifteen.printGameBoard();
        }
    }

    @Test
    public void endGameTest(){
        int[][] quasiEndedGameGrid = {{1,2,3,4},{5,6,7,8},{9,10,11,0},{13,14,15,12}};
        List<List<Integer>> quasiEndedGameBoard = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            quasiEndedGameBoard.add(new ArrayList<>(4));
            for (int j = 0; j < 4; j++) {
                quasiEndedGameBoard.get(i).add(quasiEndedGameGrid[i][j]);
            }
        }

        gameOfFifteen.setGrid(quasiEndedGameBoard);
        gameOfFifteen.printGameBoard();
        assertFalse(gameOfFifteen.isGameEnded());
        gameOfFifteen.move(MoveDirection.UP);
        gameOfFifteen.printGameBoard();
        assertTrue(gameOfFifteen.isGameEnded());
    }

    @Test
    public void saveGameTest(){
        System.out.println("Saving game:");
        System.out.println(gameOfFifteen.getGrid());
        gameOfFifteen.saveGame();
    }

    @Test
    public void loadGameTest(){
        System.out.println("Game board before loading:");
        gameOfFifteen.printGameBoard();
        gameOfFifteen.loadGame();
        System.out.println("Game board after loading");
        gameOfFifteen.printGameBoard();
    }

    public static void main(String[] args){
        GameOfFifteen gameOfFifteen = new GameOfFifteen();
        gameOfFifteen.play();
    }
}
