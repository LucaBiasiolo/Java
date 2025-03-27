package games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static games.MatrixUtil.transposeMatrix;

public class SudokuSolver {

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

    private static final List<List<List<Integer>>> submatrices = List.of(
            List.of(List.of(8,9,0),List.of(0,4,0),List.of(7,0,3)),
            List.of(List.of(7,3,0),List.of(2,0,8),List.of(0,0,0)),
            List.of(List.of(4,6,0),List.of(3,5,7),List.of(8,9,2)),
            List.of(List.of(4,6,9),List.of(0,0,0),List.of(5,1,0)),
            List.of(List.of(3,5,7),List.of(9,8,0),List.of(4,0,0)),
            List.of(List.of(2,0,8),List.of(0,0,5),List.of(0,3,9)),
            List.of(List.of(6,8,0),List.of(0,7,1),List.of(0,3,5)),
            List.of(List.of(0,0,9),List.of(8,4,3),List.of(1,0,0)),
            List.of(List.of(0,7,0),List.of(0,0,6),List.of(0,8,4)));

    private static final List<List<Integer>> flattenedSubmatrices = List.of(
            List.of(8, 9, 0, 0, 4, 0, 7, 0, 3),
            List.of(7, 3, 0, 2, 0, 8, 0, 0, 0),
            List.of(4, 6, 0, 3, 5, 7, 8, 9, 2),
            List.of(4, 6, 9, 0, 0, 0, 5, 1, 0),
            List.of(3, 5, 7, 9, 8, 0, 4, 0, 0),
            List.of(2, 0, 8, 0, 0, 5, 0, 3, 9),
            List.of(6, 8, 0, 0, 7, 1, 0, 3, 5),
            List.of(0, 0, 9, 8, 4, 3, 1, 0, 0),
            List.of(0, 7, 0, 0, 0, 6, 0, 8, 4));

    public static void main(String[] args) {
        System.out.println("Welcome to Java Sudoku Solver");
        System.out.println("You inputted the sudoku: ");
        printSudoku(sudoku);
        System.out.println("The solved sudoku is: ");
        printSudoku(solve(sudoku));
    }

    private static void printSudoku(List<List<Integer>> sudoku){
        StringBuilder printableSudoku = new StringBuilder();
        for (List<Integer> sudokuRow : sudoku){
            for (Integer value : sudokuRow){
                printableSudoku.append(" ").append(value).append(" ");
            }
            printableSudoku.append("\n");
        }
        System.out.println(printableSudoku);
    }

    private static List<List<Integer>> solve(List<List<Integer>> sudoku) {
        // todo: use depth-first-traversal if simpler approaches are not enough

        while(!checkSolvedSudoku(sudoku)) {
            List<List<Integer>> transposedSudoku = transposeMatrix(sudoku);
            for (int i = 0; i < sudoku.size(); i++) {
                List<Integer> sudokuRow = sudoku.get(i);
                for (int j = 0; j < sudoku.size(); j++) {
                    if (sudokuRow.get(j) == 0) {
                        // build arrayList with all possible numbers that can go here.
                        List<Integer> cellPossibleValuesList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

                        for (Integer number = 1; number <= 9; number++) {
                            // check row
                            if (sudokuRow.contains(number)) {
                                cellPossibleValuesList.remove(number);
                                continue;
                            }
                            // check column using transposed sudoku

                            if (transposedSudoku.get(j).contains(number)) {
                                cellPossibleValuesList.remove(number);
                                continue;
                            }
                            // build 3x3 submatrix associated with cell
                            // check 3x3 submatrix
                            List<Integer> flattenedSubmatrix = findFlattenedSubmatrixFromIndexes(i, j);
                            if (flattenedSubmatrix.contains(number)) {
                                cellPossibleValuesList.remove(number);
                            }
                        }

                        // If list has just a value than that is the obvious value of the cell
                        if (cellPossibleValuesList.size() == 1) {
                            System.out.printf("Inserting value %d in position %d,%d %n", cellPossibleValuesList.getFirst(), i, j);
                            // Crea una nuova lista mutabile basata sulla lista immutabile
                            List<Integer> mutableRow = new ArrayList<>(sudoku.get(i));
                            mutableRow.set(j, cellPossibleValuesList.getFirst());
                            sudoku.set(i, mutableRow);
                        }
                        // todo: what happens if more values are possible inside the cell?
                    }
                }
            }
        }
        return sudoku;
    }

    public static int[][][] buildSubmatricesFromSudoku(List<List<Integer>> sudoku){
        int[][][] submatrices = new int[9][3][3];
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                submatrices[0][i][j] = sudoku.get(i).get(j);
                submatrices[1][i][j] = sudoku.get(i).get(j + 3);
                submatrices[2][i][j] = sudoku.get(i).get(j + 6);
                submatrices[3][i][j] = sudoku.get(i + 3).get(j);
                submatrices[4][i][j] = sudoku.get(i + 3).get(j + 3);
                submatrices[5][i][j] = sudoku.get(i + 3).get(j + 6);
                submatrices[6][i][j] = sudoku.get(i + 6).get(j);
                submatrices[7][i][j] = sudoku.get(i + 6).get(j + 3);
                submatrices[8][i][j] = sudoku.get(i + 6).get(j + 6);
            }
        }
        return submatrices;
    }

    public static List<Integer> findFlattenedSubmatrixFromIndexes(int rowIndex, int columnIndex){
        if (List.of(0,1,2).contains(rowIndex)) {
            if (List.of(0, 1, 2).contains(columnIndex)) return flattenedSubmatrices.get(0);
            else if (List.of(3, 4, 5).contains(columnIndex)) return flattenedSubmatrices.get(1);
            else return flattenedSubmatrices.get(2);
        }else if (List.of(3,4,5).contains(rowIndex)){
            if (List.of(0,1,2).contains(columnIndex)) return flattenedSubmatrices.get(3);
            else if (List.of(3,4,5).contains(columnIndex)) return flattenedSubmatrices.get(4);
            else return flattenedSubmatrices.get(5);
        }else {
            if (List.of(0, 1, 2).contains(columnIndex)) return flattenedSubmatrices.get(6);
            else if (List.of(3, 4, 5).contains(columnIndex)) return flattenedSubmatrices.get(7);
            else return flattenedSubmatrices.get(8);
        }
    }

    public static boolean checkSolvedSudoku(List<List<Integer>> sudoku){
        // check if found solution is correct
        List<Integer> sudokuValues = List.of(1,2,3,4,5,6,7,8,9);

        // check if rows contain numbers from 1 to 9
        for (List<Integer> sudokuRow : sudoku){
            if (!sudokuRow.containsAll(sudokuValues)){
                return false;
            }
        }

        // check if columns contain numbers from 1 to 9
        for (List<Integer> sudokuColumns : transposeMatrix(sudoku)){
            if (!sudokuColumns.containsAll(sudokuValues)){
                return false;
            }
        }

        // check if every submatrix contains numbers from 1 to 9
        int[][][] submatrices = buildSubmatricesFromSudoku(sudoku);
        for (int[][] submatrix : Arrays.stream(submatrices).toList()){
            List<Integer> flatSubmatrix = new ArrayList<>(9);
            for (int[] row : submatrix) {
                for (int entry : row){
                    flatSubmatrix.add(entry);
                }
            }
            if (!flatSubmatrix.containsAll(sudokuValues)){
                return false;
            }
        }

        return true;
    }
}
