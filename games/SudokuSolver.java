package games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static games.MatrixUtil.transposeMatrix;

public class SudokuSolver {

    private static final int[][] sudoku = {
            {8,9,0,7,3,0,4,6,0},
            {0,4,0,2,0,8,3,5,7},
            {7,0,3,0,0,0,8,9,2},
            {4,6,9,3,5,7,2,0,8},
            {0,0,0,9,8,0,0,0,5},
            {5,1,0,4,0,0,0,3,9},
            {6,8,0,0,0,9,0,7,0},
            {0,7,1,8,4,3,0,0,6},
            {0,3,5,1,0,0,0,8,4}};

    private static final int[][][] submatrices = {
            {{8,9,0},{0,4,0},{7,0,3}},
            {{7,3,0},{2,0,8},{0,0,0}},
            {{4,6,0},{3,5,7},{8,9,2}},
            {{4,6,9},{0,0,0},{5,1,0}},
            {{3,5,7},{9,8,0},{4,0,0}},
            {{2,0,8},{0,0,5},{0,3,9}},
            {{6,8,0},{0,7,1},{0,3,5}},
            {{0,0,9},{8,4,3},{1,0,0}},
            {{0,7,0},{0,0,6},{0,8,4}}
    };

    public static void main(String[] args) {
        System.out.println("Welcome to Java Sudoku Solver");
        System.out.println("You inputted the sudoku: ");
        System.out.printf(Arrays.toString(sudoku));
        System.out.println("The solved sudoku is: ");
        System.out.println(Arrays.deepToString(solve(sudoku)));
    }

    private static int[][] solve(int[][] sudoku) {
        // todo: use depth-first-traversal if simpler approaches are not enough
        int[][] transposedSudoku = transposeMatrix(sudoku);

        for (int[] row : sudoku) {
            for (int i = 0; i < sudoku.length; i++) {
                if (row[i] == 0) {
                    // build arrayList with all possible numbers that can go here. If list has just a value than that is the obvious value of the cell
                    // build arrayList checking row, column and 3x3 submatrix
                    List<Integer> cellPossibleValuesList = new ArrayList<>();
                    IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).forEachOrdered(cellPossibleValuesList::add);

                    for (int number = 1; number <= 9; number++) {
                        // check row
                        final int finalNumber = number;
                        if (Arrays.stream(row).anyMatch(cell -> cell == finalNumber)) {
                            cellPossibleValuesList.remove(number);
                            break;
                        }
                        // check column using transposed sudoku

                        if (Arrays.stream(transposedSudoku[i]).anyMatch(cell -> cell == finalNumber)){
                            cellPossibleValuesList.remove(number);
                            break;
                        }
                        // build 3x3 submatrix associated with cell
                        // check 3x3 submatrix

                    }
                }
            }
        }
        return sudoku;
    }

    public static int[][][] buildSubmatricesFromSudoku(int[][]sudoku){
        int[][][] submatrices = new int[9][3][3];
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                submatrices[0][i][j] = sudoku[i][j];
                submatrices[1][i][j] = sudoku[i][j + 3];
                submatrices[2][i][j] = sudoku[i][j + 6];
                submatrices[3][i][j] = sudoku[i+3][j];
                submatrices[4][i][j] = sudoku[i+3][j+3];
                submatrices[5][i][j] = sudoku[i+3][j+6];
                submatrices[6][i][j] = sudoku[i+6][j];
                submatrices[7][i][j] = sudoku[i+6][j+3];
                submatrices[8][i][j] = sudoku[i+6][j+6];
            }
        }
        return submatrices;
    }
}
