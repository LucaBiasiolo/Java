package games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        System.out.printf(Arrays.deepToString(sudoku));
        System.out.println("The solved sudoku is: ");
        System.out.println(Arrays.deepToString(solve(sudoku)));
    }

    private static int[][] solve(int[][] sudoku) {
        // todo: use depth-first-traversal if simpler approaches are not enough
        int[][] transposedSudoku = transposeMatrix(sudoku);

        for (int i = 0; i < sudoku.length; i++) {
            int[] sudokuRow = sudoku[i];
            for (int j = 0; j < sudoku.length; j++) {
                if (sudokuRow[j] == 0) {
                    // build arrayList with all possible numbers that can go here.
                    List<Integer> cellPossibleValuesList = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));

                    for (int number = 1; number <= 9; number++) {
                        // check row
                        final int finalNumber = number;
                        if (Arrays.stream(sudokuRow).anyMatch(cell -> cell == finalNumber)) {
                            cellPossibleValuesList.remove(cellPossibleValuesList.indexOf(number));
                            continue;
                        }
                        // check column using transposed sudoku

                        if (Arrays.stream(transposedSudoku[j]).anyMatch(cell -> cell == finalNumber)) {
                            cellPossibleValuesList.remove(cellPossibleValuesList.indexOf(number));
                            continue;
                        }
                        // build 3x3 submatrix associated with cell
                        // check 3x3 submatrix
                        int[][] submatrix = findSubmatrixFromIndexes(i, j);
                        if (Arrays.stream(submatrix).anyMatch(row -> Arrays.stream(row).anyMatch(value -> value == finalNumber))){
                            cellPossibleValuesList.remove(cellPossibleValuesList.indexOf(number));
                        }
                    }

                    // If list has just a value than that is the obvious value of the cell
                    if (cellPossibleValuesList.size() == 1){
                        System.out.printf("Inserting value %d in position %d,%d %n", cellPossibleValuesList.getFirst(),i,j);
                        sudoku[i][j] = cellPossibleValuesList.getFirst();
                    }
                    // todo: what happens if more values are possible inside the cell?
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

    public static int[][] findSubmatrixFromIndexes(int rowIndex, int columnIndex){
        if (List.of(0,1,2).contains(rowIndex)) {
            if (List.of(0, 1, 2).contains(columnIndex)) return submatrices[0];
            else if (List.of(3, 4, 5).contains(columnIndex)) return submatrices[1];
            else return submatrices[2];
        }else if (List.of(3,4,5).contains(rowIndex)){
            if (List.of(0,1,2).contains(columnIndex)) return submatrices[3];
            else if (List.of(3,4,5).contains(columnIndex)) return submatrices[4];
            else return submatrices[5];
        }else {
            if (List.of(0, 1, 2).contains(columnIndex)) return submatrices[6];
            else if (List.of(3, 4, 5).contains(columnIndex)) return submatrices[7];
            else return submatrices[8];
        }
    }
}
