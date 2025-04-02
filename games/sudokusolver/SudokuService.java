package games.sudokusolver;

import games.sudokusolver.beans.Sudoku;
import games.sudokusolver.beans.SudokuCell;
import games.sudokusolver.beans.SudokuDifficulty;

import java.util.ArrayList;
import java.util.HashMap;
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

    static void solveWithHiddenPairs(Sudoku sudoku){
        for(List<SudokuCell> submatrix : sudoku.getFlattenedSubmatrices()){
            HashMap<Integer, List<SudokuCell>> mapNumberToPossibleCells = new HashMap<>();
            for (int number = 1; number <= 9 ; number++) {
                List<SudokuCell> possibleCellsForNumber = new ArrayList<>();
                if (!submatrix.stream().map(SudokuCell::getValue).toList().contains(number)){
                    for (SudokuCell cell : submatrix) {
                        if (cell.getValue() == 0) {
                            // if the number appears only in a cell, that is the cell in which it has to be inserted
                            List<Integer> cellPossibleValues = cell.getPossibleValues();
                            if (cellPossibleValues.contains(number) && cellPossibleValues.size() >2){
                                possibleCellsForNumber.add(cell);
                            }
                        }
                    }
                    if (possibleCellsForNumber.size() == 2){
                        mapNumberToPossibleCells.put(number, possibleCellsForNumber);
                    }
                }
            }

            //fixme: eliminate duplicate passages
            //fixme: exclude case of two cells with 3 possible identical numbers (es. (1,3,7) , (1,3,7)
            for(Integer number1 : mapNumberToPossibleCells.keySet()){
                for(Integer number2 : mapNumberToPossibleCells.keySet()){
                    if(!number1.equals(number2) && mapNumberToPossibleCells.get(number1).equals(mapNumberToPossibleCells.get(number2))){
                        // found hidden pair: i can delete other numbers in notes
                        System.out.printf("Found hidden pair: numbers %d and %d appear only in cells %s %n",number1, number2, mapNumberToPossibleCells.get(number1));
                        System.out.println("Delete other possible numbers from the cells");
                        for (SudokuCell cell : mapNumberToPossibleCells.get(number1)){
                            cell.getPossibleValues().removeIf(value -> !value.equals(number1) && !value.equals(number2));
                        }
                    }
                }
            }

        }
    }

    static void solveWithObviousPairs(Sudoku sudoku){
        for(List<SudokuCell> submatrix : sudoku.getFlattenedSubmatrices()){
            List<SudokuCell> cellsWith2PossibleValues = submatrix.stream().filter(cell ->
                    cell.getPossibleValues() != null && cell.getPossibleValues().size() == 2).toList();

            for (int i = 0; i < cellsWith2PossibleValues.size(); i++) {
                for (int j = i+1; j < cellsWith2PossibleValues.size(); j++) {
                    SudokuCell cell1 = cellsWith2PossibleValues.get(i);
                    SudokuCell cell2 = cellsWith2PossibleValues.get(j);
                    if(cell1.getPossibleValues().containsAll(cell2.getPossibleValues()) && cell2.getPossibleValues().containsAll(cell1.getPossibleValues())) {
                        // found obvious pair, now delete the pair from the other cells of the submatrix
                        List<SudokuCell> otherCells = submatrix.stream().filter(cell ->
                                cell.getPossibleValues() != null && cell.getPossibleValues().size() > 2).toList();
                        // fixme: avoid to consider cells that do not have numbers in common with obvious pair
                        for (SudokuCell cell : otherCells) {
                            System.out.printf("Obvious pairs: %s %s %n", cell1, cell2);
                            System.out.printf("Removing possible values %s from %s %n", cell1.getPossibleValues(), cell);
                            cell.getPossibleValues().removeAll(cell1.getPossibleValues());
                        }
                    }
                }
            }
        }
    }

    static void solveWithLastRemainingCell(Sudoku sudoku){
        for (List<List<SudokuCell>> sudokuPartition : getSudokuPartitions(sudoku)){
            for (List<SudokuCell> rowColumnOrSubmatrix : sudokuPartition){
                for (int number = 1; number <= 9; number++) {
                    List<SudokuCell> possibleCellsForNumber = new ArrayList<>();
                    if (!rowColumnOrSubmatrix.stream().map(SudokuCell::getValue).toList().contains(number)){ // if submatrix does not contain the number
                        for (SudokuCell cell : rowColumnOrSubmatrix) {
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
        }
    }

    private static List<List<List<SudokuCell>>> getSudokuPartitions(Sudoku sudoku) {
        return List.of(sudoku.getGrid(), sudoku.getTransposedGrid(), sudoku.getFlattenedSubmatrices());
    }

    // this method inserts a number in a cell if it is the only possible value in the cell's list of possible values
    static void solveWithLastPossibleNumber(Sudoku sudoku) {
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

        for(List<SudokuCell> sudokuRow : sudoku.getGrid()){
            for(SudokuCell cell : sudokuRow){
                if (cell.getValue() == 0){
                    return false;
                }
            }
        }

        // check if rows, columns and Submatrices contain numbers from 1 to 9
        for (List<List<SudokuCell>> sudokuPartition : getSudokuPartitions(sudoku)) {
            for (List<SudokuCell> rowColumnOrSubmatrix : sudokuPartition){
                if(!rowColumnOrSubmatrix.stream().map(SudokuCell::getValue).toList().containsAll(sudokuValues)){
                    return false;
                }
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
