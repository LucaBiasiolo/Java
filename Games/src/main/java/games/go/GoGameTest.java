package games.go;

import games.PieceColor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoGameTest {

    private static GoBoard board;
    private static Player whitePlayer;
    private static Player blackPlayer;

    @BeforeAll
    public static void createGoBoard(){
        board = new GoBoard(9);
        whitePlayer = new Player("Luca White", PieceColor.WHITE);
        blackPlayer = new Player("Luca Black", PieceColor.BLACK);
    }

    @Test
    public void printGoBoardTest(){
        board.printBoard();
    }

    @Test
    public void moveWithBoardCoordinates(){
        blackPlayer.moveWithBoardCoordinates(board, 3,4);
        board.printBoard();
        whitePlayer.moveWithBoardCoordinates(board, 4,3);
        board.printBoard();
    }

    @Test
    public void isStoneOrGroupAliveTest(){
        blackPlayer.moveWithBoardCoordinates(board, 2,3);
        whitePlayer.moveWithBoardCoordinates(board, 2,4);
        blackPlayer.moveWithBoardCoordinates(board,3,3);
        whitePlayer.moveWithBoardCoordinates(board, 2,2);
        blackPlayer.moveWithBoardCoordinates(board,1,3);
        whitePlayer.moveWithBoardCoordinates(board, 4,3);
        board.printBoard();
        boolean stoneOrGroupAlive = board.isGroupAlive(board.getStoneWithBoardCoordinates(2, 3));
        assertTrue(stoneOrGroupAlive);
    }

    @Test
    public void isGroupAlive2Test(){
        blackPlayer.moveWithBoardCoordinates(board, 2,3);
        whitePlayer.moveWithBoardCoordinates(board, 2,4);
        blackPlayer.moveWithBoardCoordinates(board,3,3);
        whitePlayer.moveWithBoardCoordinates(board, 2,2);
        blackPlayer.moveWithBoardCoordinates(board,1,3);
        whitePlayer.moveWithBoardCoordinates(board, 4,3);
        board.printBoard();
        boolean stoneOrGroupAlive = board.isGroupAlive(board.getStoneWithBoardCoordinates(2, 3));
        assertTrue(stoneOrGroupAlive);
    }

    @Test
    public void findGroupTest(){
        blackPlayer.moveWithBoardCoordinates(board, 2,3);
        whitePlayer.moveWithBoardCoordinates(board, 2,4);
        blackPlayer.moveWithBoardCoordinates(board,3,3);
        whitePlayer.moveWithBoardCoordinates(board, 2,2);
        blackPlayer.moveWithBoardCoordinates(board,1,3);
        whitePlayer.moveWithBoardCoordinates(board, 4,3);
        whitePlayer.moveWithBoardCoordinates(board, 2,1);
        board.printBoard();

        List<Stone> blackGroup = board.findGroup(board.getStoneWithBoardCoordinates(1, 3), null);

        List<Stone> whiteGroup = board.findGroup(board.getStoneWithBoardCoordinates(2, 2), null);
        assertEquals(3, blackGroup.size());
        assertEquals(2, whiteGroup.size());
    }
}