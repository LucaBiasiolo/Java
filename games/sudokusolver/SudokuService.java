package games.sudokusolver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static games.MatrixUtil.transposeMatrix;

public class SudokuService {

    static Sudoku createSudokuFromGrid(int[][] valuesMatrix, SudokuDifficulty difficulty) {
        Sudoku sudoku = new Sudoku();
        sudoku.setDifficulty(difficulty);

        List<List<SudokuCell>> grid = new ArrayList<>(9);
        for (int i = 0; i < 9; i++) {
            List<SudokuCell> sudokuRow = new ArrayList<>(9);
            for (int j = 0; j < 9; j++) {
                sudokuRow.add(new SudokuCell(i,j,valuesMatrix[i][j]));
            }
            grid.add(sudokuRow);
        }

        sudoku.setGrid(grid);
        sudoku.setTransposedGrid(transposeMatrix(grid));
        sudoku.setFlattenedSubmatrices(buildFlattenedSubmatricesFromSudoku(grid));

        // for every cell, build its list of possible values
        for(List<SudokuCell> sudokuRow : sudoku.getGrid()){
            for(SudokuCell cell : sudokuRow){
                if (cell.getValue() == 0){
                    List<SudokuCell> cellSubmatrix = findFlattenedSubmatrixFromIndexes(sudoku.getFlattenedSubmatrices(), cell.getRowIndex(), cell.getColumnIndex());
                    cell.setPossibleValues(buildCellPossibleValuesList(sudokuRow, sudoku.getTransposedGrid().get(cell.getColumnIndex()), cellSubmatrix));
                }
            }
        }

        return sudoku;
    }

    // this method inserts a number in a cell if it is the only possible value
    static Sudoku solveWithLastPossibleNumber(Sudoku sudoku) {
        // todo: use depth-first-traversal if simpler approaches are not enough
        int roundCounter = 0;
        while(!checkSolvedSudoku(sudoku)) {
            for (List<SudokuCell> sudokuRow : sudoku.getGrid()) {
                for (SudokuCell cell : sudokuRow) {
                    if (cell.getValue() == 0) {
                        List<Integer> cellPossibleValues = cell.getPossibleValues();
                        if (cellPossibleValues.size() == 1) {
                            System.out.printf("Solve with last possible number: Inserting value %d in position %d,%d %n", cellPossibleValues.getFirst(), cell.getRowIndex(), cell.getColumnIndex());
                            cell.setValue(cellPossibleValues.getFirst());
                            cell.setPossibleValues(null);

                            for (SudokuCell cellInRow : sudokuRow){
                                if(cellInRow.getPossibleValues() != null){
                                   cellInRow.getPossibleValues().remove(cellPossibleValues.getFirst());
                                }
                            }
                            for (SudokuCell cellInColumn : sudoku.getTransposedGrid().get(cell.getColumnIndex())){
                                if (cellInColumn.getPossibleValues() != null) {
                                    cellInColumn.getPossibleValues().remove(cellPossibleValues.getFirst());
                                }
                            }
                            List<SudokuCell> submatrix = findFlattenedSubmatrixFromIndexes(sudoku.getFlattenedSubmatrices(), cell.getRowIndex(), cell.getColumnIndex());
                            for (SudokuCell cellInSubmatrix : submatrix){
                                if (cellInSubmatrix.getPossibleValues() != null) {
                                    cellInSubmatrix.getPossibleValues().remove(cellPossibleValues.getFirst());
                                }
                            }
                        } else{
                            // todo: what happens if more values are possible inside the cell?
                            // last cell possible
                        }
                    }
                }
            }
            System.out.printf("End of round %d, the sudoku is as follows%n", roundCounter);
            printSudoku(sudoku.getGrid());
            roundCounter++;
        }
        return sudoku;
    }

    static boolean checkSolvedSudoku(Sudoku sudoku){
        List<Integer> sudokuValues = IntStream.rangeClosed(1,9).boxed().toList();

        // check if rows contain numbers from 1 to 9
        for (List<SudokuCell> sudokuRow : sudoku.getGrid()){
            if (!sudokuRow.stream().map(SudokuCell::getValue).toList().containsAll(sudokuValues)){
                return false;
            }
        }

        // check if columns contain numbers from 1 to 9
        for (List<SudokuCell> sudokuColumns : sudoku.getTransposedGrid()){
            if (!sudokuColumns.stream().map(SudokuCell::getValue).toList().containsAll(sudokuValues)){
                return false;
            }
        }

        // check if every submatrix contains numbers from 1 to 9
        List<List<SudokuCell>> flatSubmatrices = sudoku.getFlattenedSubmatrices();
        for (List<SudokuCell> flatSubmatrix : flatSubmatrices){
            if (!flatSubmatrix.stream().map(SudokuCell::getValue).toList().containsAll(sudokuValues)){
                return false;
            }
        }

        return true;
    }

    public static List<List<SudokuCell>> buildFlattenedSubmatricesFromSudoku(List<List<SudokuCell>> sudoku){
        List<List<SudokuCell>> submatrices = new ArrayList<>(9);

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

    static void printSudoku(List<List<SudokuCell>> sudoku){
        StringBuilder printableSudoku = new StringBuilder();
        for (List<SudokuCell> sudokuRow : sudoku){
            for (SudokuCell cell : sudokuRow){
                printableSudoku.append(" ").append(cell.getValue()).append(" ");
            }
            printableSudoku.append("\n");
        }
        System.out.println(printableSudoku);
    }

    static List<Integer> buildCellPossibleValuesList(List<SudokuCell> sudokuRow, List<SudokuCell> sudokuColumn, List<SudokuCell> flattenedSubmatrix) {
        return IntStream.rangeClosed(1,9).filter(possibleNumber ->
                !sudokuRow.stream().map(SudokuCell::getValue).toList().contains(possibleNumber) &&
                !sudokuColumn.stream().map(SudokuCell::getValue).toList().contains(possibleNumber) &&
                !flattenedSubmatrix.stream().map(SudokuCell::getValue).toList().contains(possibleNumber))
                .boxed().collect(Collectors.toList());
    }

    static List<SudokuCell> findFlattenedSubmatrixFromIndexes(List<List<SudokuCell>> flattenedSubmatrices, int rowIndex, int columnIndex){
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
