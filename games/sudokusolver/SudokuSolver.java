package games.sudokusolver;

import java.util.ArrayList;
import java.util.List;

import static games.sudokusolver.SudokuService.*;

public class SudokuSolver {

    private static final Sudoku easySudoku = new Sudoku(new ArrayList<>(List.of(
            List.of(8,9,0,7,3,0,4,6,0),
            List.of(0,4,0,2,0,8,3,5,7),
            List.of(7,0,3,0,0,0,8,9,2),
            List.of(4,6,9,3,5,7,2,0,8),
            List.of(0,0,0,9,8,0,0,0,5),
            List.of(5,1,0,4,0,0,0,3,9),
            List.of(6,8,0,0,0,9,0,7,0),
            List.of(0,7,1,8,4,3,0,0,6),
            List.of(0,3,5,1,0,0,0,8,4))),
            SudokuDifficulty.EASY);

    private static final Sudoku mediumSudoku = new Sudoku(new ArrayList<>(List.of(
            List.of(0, 0, 0, 2, 6, 0, 7, 0, 1),
            List.of(6, 8, 0, 0, 7, 0, 0, 9, 0),
            List.of(1, 9, 0, 0, 0, 4, 5, 0, 0),
            List.of(8, 2, 0, 1, 0, 0, 0, 4, 0),
            List.of(0, 0, 4, 6, 0, 2, 9, 0, 0),
            List.of(0, 5, 0, 0, 0, 3, 0, 2, 8),
            List.of(0, 0, 9, 3, 0, 0, 0, 7, 4),
            List.of(0, 4, 0, 0, 5, 0, 0, 3, 6),
            List.of(7, 0, 3, 0, 1, 8, 0, 0, 0)
    )), SudokuDifficulty.MEDIUM);

    // todo: this is not solvable as of now
    private static final Sudoku hardSudoku = new Sudoku(new ArrayList<>(List.of(
            List.of(9, 0, 0, 4, 0, 2, 5, 0, 0),
            List.of(0, 0, 0, 9, 0, 1, 0, 0, 0),
            List.of(0, 5, 0, 0, 0, 0, 0, 0, 7),
            List.of(4, 0, 0, 0, 2, 0, 0, 8, 0),
            List.of(0, 0, 2, 0, 0, 0, 7, 3, 4),
            List.of(5, 0, 8, 0, 0, 6, 0, 0, 0),
            List.of(0, 0, 0, 8, 0, 0, 0, 2, 0),
            List.of(7, 0, 0, 0, 0, 0, 0, 0, 1),
            List.of(0, 9, 0, 2, 6, 0, 0, 0, 0)
    )), SudokuDifficulty.HARD);

    public static void main(String[] args) {
        System.out.println("Welcome to Java Sudoku Solver");
        System.out.println("You inputted the sudoku: ");
        SudokuService.printSudoku(hardSudoku.getSudoku());
        SudokuService.printSudoku(solveSudoku(hardSudoku.getSudoku()));
    }
}
