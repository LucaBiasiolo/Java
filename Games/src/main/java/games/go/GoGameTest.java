package games.go;

import games.PieceColor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class GoGameTest {

    private static GoBoard board;
    private static Player whitePlayer;
    private static Player blackPlayer;

    @BeforeAll
    public static void createGoBoard(){
        board = new GoBoard(19);
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
}