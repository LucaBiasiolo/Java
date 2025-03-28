package games.sudokusolver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static games.MatrixUtil.transposeMatrix;

public class SudokuUtil {
    static boolean checkSolvedSudoku(List<List<Integer>> sudoku){
        List<Integer> sudokuValues = IntStream.rangeClosed(1,9).boxed().toList();

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
        List<List<Integer>> flatSubmatrices = buildFlattenedSubmatricesFromSudoku(sudoku);
        for (List<Integer> flatSubmatrix : flatSubmatrices){
            if (!flatSubmatrix.containsAll(sudokuValues)){
                return false;
            }
        }

        return true;
    }

    public static List<List<Integer>> buildFlattenedSubmatricesFromSudoku(List<List<Integer>> sudoku){
        List<List<Integer>> submatrices = new ArrayList<>(9);

        IntStream.range(0,9).forEach(_ -> submatrices.add(new ArrayList<>(9)));
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                submatrices.get(0).add(sudoku.get(i).get(j));
                submatrices.get(1).add(sudoku.get(i).get(j + 3));
                submatrices.get(2).add(sudoku.get(i).get(j + 6));
                submatrices.get(3).add(sudoku.get(i + 3).get(j));
                submatrices.get(4).add(sudoku.get(i + 3).get(j + 3));
                submatrices.get(5).add(sudoku.get(i + 3).get(j + 6));
                submatrices.get(6).add(sudoku.get(i + 6).get(j));
                submatrices.get(7).add(sudoku.get(i + 6).get(j + 3));
                submatrices.get(8).add(sudoku.get(i + 6).get(j + 6));
            }
        }
        return submatrices;
    }

    static void printSudoku(List<List<Integer>> sudoku){
        StringBuilder printableSudoku = new StringBuilder();
        for (List<Integer> sudokuRow : sudoku){
            for (Integer value : sudokuRow){
                printableSudoku.append(" ").append(value).append(" ");
            }
            printableSudoku.append("\n");
        }
        System.out.println(printableSudoku);
    }

    static List<Integer> buildCellPossibleValuesList(List<Integer> sudokuRow, List<Integer> sudokuColumn, List<Integer> flattenedSubmatrix) {
        return IntStream.rangeClosed(1,9).filter(possibleNumber ->
                !sudokuRow.contains(possibleNumber) && !sudokuColumn.contains(possibleNumber) && !flattenedSubmatrix.contains(possibleNumber)).boxed().toList();
    }

    static List<Integer> findFlattenedSubmatrixFromIndexes(List<List<Integer>> flattenedSubmatrices, int rowIndex, int columnIndex){
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
}
