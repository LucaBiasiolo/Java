package games.sudokusolver;

import java.util.ArrayList;
import java.util.List;

import static games.MatrixUtil.transposeMatrix;
import static games.sudokusolver.SudokuUtil.*;

public class SudokuSolver {

    // todo: test on medium and hard-difficulty sudoku
    private static final List<List<Integer>> sudoku = new ArrayList<>(List.of(
            List.of(8,9,0,7,3,0,4,6,0),
            List.of(0,4,0,2,0,8,3,5,7),
            List.of(7,0,3,0,0,0,8,9,2),
            List.of(4,6,9,3,5,7,2,0,8),
            List.of(0,0,0,9,8,0,0,0,5),
            List.of(5,1,0,4,0,0,0,3,9),
            List.of(6,8,0,0,0,9,0,7,0),
            List.of(0,7,1,8,4,3,0,0,6),
            List.of(0,3,5,1,0,0,0,8,4)));

    public static void main(String[] args) {
        System.out.println("Welcome to Java Sudoku Solver");
        System.out.println("You inputted the sudoku: ");
        SudokuUtil.printSudoku(sudoku);
        System.out.println("The solved sudoku is: ");
        SudokuUtil.printSudoku(solveSudoku(sudoku));
    }

    static List<List<Integer>> solveSudoku(List<List<Integer>> sudoku) {
        // todo: use depth-first-traversal if simpler approaches are not enough
        while(!checkSolvedSudoku(sudoku)) { // todo: what if this loop never ends?
            List<List<Integer>> transposedSudoku = transposeMatrix(sudoku);
            for (int i = 0; i < sudoku.size(); i++) {
                List<Integer> sudokuRow = sudoku.get(i);
                for (int j = 0; j < sudoku.size(); j++) {
                    if (sudokuRow.get(j) == 0) {
                        List<Integer> sudokuColumn = transposedSudoku.get(j);
                        List<List<Integer>> flattenedSubmatrices = buildFlattenedSubmatricesFromSudoku(sudoku);
                        List<Integer> cellSubmatrix = findFlattenedSubmatrixFromIndexes(flattenedSubmatrices,i,j);

                        List<Integer> cellPossibleValues = buildCellPossibleValuesList(sudokuRow, sudokuColumn, cellSubmatrix);
                        // If list has just a value than that is the obvious value of the cell
                        if (cellPossibleValues.size() == 1) {
                            System.out.printf("Inserting value %d in position %d,%d %n", cellPossibleValues.getFirst(), i, j);
                            // Crea una nuova lista mutabile basata sulla lista immutabile
                            List<Integer> newSudokuRow = new ArrayList<>(sudoku.get(i));
                            newSudokuRow.set(j, cellPossibleValues.getFirst());
                            sudoku.set(i, newSudokuRow);
                        }
                        // todo: what happens if more values are possible inside the cell?
                    }
                }
            }
        }
        return sudoku;
    }
}
