package games.sudokusolver;

import games.sudokusolver.beans.Sudoku;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SudokuTest {

    @Test
    void testSudokuCreation() {
        Sudoku sudoku = new Sudoku();
        assertNotNull(sudoku);
    }
}