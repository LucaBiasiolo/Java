package games.fifteen;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FifteenGameTest {

    private static GameOfFifteen gameBoard;

    @BeforeAll
    public static void createGrid(){
        gameBoard = new GameOfFifteen();
        gameBoard.populateGrid();
    }

    @Test
    public  void testGridCreation(){
        assertNotNull(gameBoard);
    }

    @Test
    public void testPrint(){
        gameBoard.print();
    }

    @Test
    public void isMovementPossibleTest(){
        for (MoveDirection direction : List.of(MoveDirection.UP, MoveDirection.DOWN, MoveDirection.LEFT, MoveDirection.RIGHT)) {
            System.out.printf("Movement in direction %s : %s %n", direction, gameBoard.isMovementPossible(direction));
        }
    }

    @Test
    public void moveTest(){
        gameBoard.print();
        for (MoveDirection direction : List.of(MoveDirection.UP, MoveDirection.DOWN, MoveDirection.LEFT, MoveDirection.RIGHT)) {
            System.out.printf("Movement in direction %s %n", direction);
            gameBoard.move(direction);
            gameBoard.print();
        }
    }

    public static void main(String[] args){
        GameOfFifteen gameOfFifteen = new GameOfFifteen();
        gameOfFifteen.play();
    }
}
