package games.sudokusolver;

import games.sudokusolver.beans.Sudoku;
import games.sudokusolver.beans.SudokuCell;
import games.sudokusolver.beans.SudokuDifficulty;

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
                    int submatrixIndex = sudoku.getFlattenedSubmatrices().indexOf(cellSubmatrix);
                    cell.setSubmatrixIndex(submatrixIndex);
                    cell.setPossibleValues(buildCellPossibleValuesList(sudokuRow, sudoku.getTransposedGrid().get(cell.getColumnIndex()), cellSubmatrix));
                }
            }
        }

        return sudoku;
    }

    static Sudoku solveWithLastRemainingCell(Sudoku sudoku){
        for (List<SudokuCell> submatrix : sudoku.getFlattenedSubmatrices()){
            for (int number = 1; number <= 9; number++) {
                List<SudokuCell> possibleCellsForNumber = new ArrayList<>();
                if (!submatrix.stream().map(SudokuCell::getValue).toList().contains(number)){ // if submatrix does not contain the number
                    for (SudokuCell cell : submatrix) {
                        if (cell.getValue() == 0) {
                            // if the number appears only in a cell, that is the cell in which it has to be inserted
                            List<Integer> cellPossibleValues = cell.getPossibleValues();
                            if (cellPossibleValues.contains(number)){
                                possibleCellsForNumber.add(cell);
                            }
                        }
                    }
                    if (possibleCellsForNumber.size() == 1){
                        SudokuCell cellToFill = possibleCellsForNumber.getFirst();
                        System.out.printf("Solve with last remaining cell: Inserting value %d in position %d,%d %n", number, cellToFill.getRowIndex()+1, cellToFill.getColumnIndex()+1);
                        updateCellAndSudoku(sudoku, cellToFill, number);
                    }
                }
            }
        }
        return sudoku;
    }

    // this method inserts a number in a cell if it is the only possible value in the cell's list of possible values
    static Sudoku solveWithLastPossibleNumber(Sudoku sudoku) {
        for (List<SudokuCell> sudokuRow : sudoku.getGrid()) {
            for (SudokuCell cell : sudokuRow) {
                if (cell.getValue() == 0) {
                    List<Integer> cellPossibleValues = cell.getPossibleValues();
                    if (cellPossibleValues.size() == 1) {
                        System.out.printf("Solve with last possible number: Inserting value %d in position %d,%d %n", cellPossibleValues.getFirst()+1, cell.getRowIndex(), cell.getColumnIndex()+1);
                        updateCellAndSudoku(sudoku, cell, cellPossibleValues.getFirst());
                    }
                }
            }
        }
        return sudoku;
    }

    private static void updateCellAndSudoku(Sudoku sudoku, SudokuCell cell, Integer valueToInsert) {
        cell.setValue(valueToInsert);
        cell.setPossibleValues(null);

        for (SudokuCell cellInRow : sudoku.getGrid().get(cell.getRowIndex())){
            if(cellInRow.getPossibleValues() != null){
               cellInRow.getPossibleValues().remove(valueToInsert);
            }
        }
        for (SudokuCell cellInColumn : sudoku.getTransposedGrid().get(cell.getColumnIndex())){
            if (cellInColumn.getPossibleValues() != null) {
                cellInColumn.getPossibleValues().remove(valueToInsert);
            }
        }
        List<SudokuCell> submatrix = sudoku.getFlattenedSubmatrices().get(cell.getSubmatrixIndex());
        for (SudokuCell cellInSubmatrix : submatrix){
            if (cellInSubmatrix.getPossibleValues() != null) {
                cellInSubmatrix.getPossibleValues().remove(valueToInsert);
            }
        }
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
