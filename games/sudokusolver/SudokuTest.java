package games.sudokusolver;

import games.sudokusolver.beans.Sudoku;
import games.sudokusolver.beans.SudokuDifficulty;
import org.junit.jupiter.api.Test;

import java.util.List;

import static games.sudokusolver.SudokuService.*;
import static org.junit.jupiter.api.Assertions.*;

class SudokuTest {

    private final Sudoku easySudoku1 = createSudokuFromGrid(
            new int[][]{{8,9,0,7,3,0,4,6,0},
                    {0,4,0,2,0,8,3,5,7},
                    {7,0,3,0,0,0,8,9,2},
                    {4,6,9,3,5,7,2,0,8},
                    {0,0,0,9,8,0,0,0,5},
                    {5,1,0,4,0,0,0,3,9},
                    {6,8,0,0,0,9,0,7,0},
                    {0,7,1,8,4,3,0,0,6},
                    {0,3,5,1,0,0,0,8,4}},
            SudokuDifficulty.EASY);

    private final Sudoku easySudoku2 = createSudokuFromGrid(
            new int[][]{
                    {8, 3, 0, 6, 0, 0, 0, 5, 7},
                    {7, 4, 0, 8, 1, 0, 0, 9, 6},
                    {1, 0, 0, 0, 0, 7, 0, 8, 0},
                    {4, 0, 9, 5, 8, 0, 7, 6, 0},
                    {3, 0, 0, 2, 6, 0, 0, 0, 0},
                    {5, 0, 0, 0, 7, 3, 0 ,2 ,0},
                    {0 ,0 ,4 ,1 ,0 ,5 ,0 ,0 ,2},
                    {0 ,1 ,3 ,0 ,0 ,0 ,0 ,0 ,0},
                    {2 ,0 ,7 ,9 ,4 ,0 ,8 ,0 ,0}},
            SudokuDifficulty.EASY);

    private final Sudoku mediumSudoku1 = createSudokuFromGrid(
            new int[][]{
                    {0, 0, 0, 2, 6, 0, 7, 0, 1},
                    {6, 8, 0, 0, 7, 0, 0, 9, 0},
                    {1, 9, 0, 0, 0, 4, 5, 0, 0},
                    {8, 2, 0, 1, 0, 0, 0, 4, 0},
                    {0, 0, 4, 6, 0, 2, 9, 0, 0},
                    {0, 5, 0, 0, 0, 3, 0, 2, 8},
                    {0, 0, 9, 3, 0, 0, 0, 7, 4},
                    {0, 4, 0, 0, 5, 0, 0, 3, 6},
                    {7, 0, 3, 0, 1, 8, 0, 0, 0}
            },
            SudokuDifficulty.MEDIUM);

    private final Sudoku hardSudoku1 = createSudokuFromGrid(
            new int[][]{
                    {9, 0, 0, 4, 0, 2, 5, 0, 0},
                    {0, 0, 0, 9, 5, 1, 0, 0, 0},
                    {0, 5, 0, 0, 0, 0, 0, 0, 7},
                    {4, 0, 0, 0, 2, 0, 0, 8, 5},
                    {0, 0, 2, 0, 0, 0, 7, 3, 4},
                    {5, 0, 8, 0, 0, 6, 0, 0, 0},
                    {0, 0, 0, 8, 0, 0, 0, 2, 0},
                    {7, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 9, 0, 2, 6, 0, 0, 0, 0}},
            SudokuDifficulty.HARD);

    @Test
    void testEasySudoku(){
        List<Sudoku> listOfEasySudoku = List.of(easySudoku1, easySudoku2);
        for (Sudoku sudoku : listOfEasySudoku) {
            SudokuService.printSudoku(sudoku.getGrid());
            while (!checkSolvedSudoku(sudoku)) {
                SudokuService.solveWithLastPossibleNumber(sudoku);
                SudokuService.printSudoku(sudoku.getGrid());
            }
            assertTrue(checkSolvedSudoku(sudoku));
        }
    }

    @Test
    void testMediumSudoku(){
        SudokuService.printSudoku(mediumSudoku1.getGrid());
        while (!checkSolvedSudoku(mediumSudoku1)) {
            SudokuService.solveWithLastPossibleNumber(mediumSudoku1);
            SudokuService.printSudoku(mediumSudoku1.getGrid());
        }
        assertTrue(checkSolvedSudoku(mediumSudoku1));
    }

    @Test
    void testHardSudoku(){
        SudokuService.printSudoku(hardSudoku1.getGrid());
        while (!checkSolvedSudoku(hardSudoku1)) {
            solveWithLastPossibleNumber(hardSudoku1);
            solveWithLastRemainingCell(hardSudoku1);
            SudokuService.printSudoku(hardSudoku1.getGrid());
        }
        assertTrue(checkSolvedSudoku(hardSudoku1));
    }
}