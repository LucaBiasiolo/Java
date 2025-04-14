package games.sudokusolver;

import games.sudokusolver.beans.Sudoku;
import games.sudokusolver.beans.SudokuCell;
import games.sudokusolver.beans.SudokuDifficulty;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static games.MatrixUtil.createTransposedMatrix;

public class SudokuService {

    static Sudoku createSudokuFromGrid(int[][] values, SudokuDifficulty difficulty) {
        Sudoku sudoku = new Sudoku();
        sudoku.setDifficulty(difficulty);

        List<List<SudokuCell>> grid = new ArrayList<>(9);
        for (int i = 0; i < 9; i++) {
            List<SudokuCell> sudokuRow = new ArrayList<>(9);
            for (int j = 0; j < 9; j++) {
                sudokuRow.add(new SudokuCell(i,j,values[i][j]));
            }
            grid.add(sudokuRow);
        }

        sudoku.setGrid(grid);
        sudoku.setTransposedGrid(createTransposedMatrix(grid));
        sudoku.setBlocks(buildBlocksFromSudoku(grid));

        // for every cell, build its list of possible values
        for(List<SudokuCell> sudokuRow : sudoku.getGrid()){
            for(SudokuCell cell : sudokuRow){
                if (cell.getValue() == 0){
                    List<SudokuCell> cellblock = findBlockFromCellIndexes(sudoku.getBlocks(), cell.getRowIndex(), cell.getColumnIndex());
                    int blockIndex = sudoku.getBlocks().indexOf(cellblock);
                    cell.setBlockIndex(blockIndex);
                    cell.setPossibleValues(buildCellPossibleValuesList(sudokuRow, sudoku.getTransposedGrid().get(cell.getColumnIndex()), cellblock));
                }
            }
        }
        return sudoku;
    }

    static boolean solveWithBruteForce(Sudoku sudoku, int[] numberOfInsertions, int[] numberOfReset){
        for(List<SudokuCell> sudokuRow : sudoku.getGrid()){
            for(SudokuCell cell : sudokuRow){
                if (cell.getValue() == 0){ //find first zero-valued cell
                    if(cell.getPossibleValues() != null) {
                        for (int possibleValue =1;possibleValue<=9; possibleValue++) { //for every possible value
                            if(valueIsValid(sudoku, cell, possibleValue)) { // if value is valid
                                cell.setValue(possibleValue); // set value
                                numberOfInsertions[0]++;
                                if (solveWithBruteForce(sudoku, numberOfInsertions, numberOfReset)) { // pass to next zero-valued cell
                                    return true;
                                }
                                cell.setValue(0); // reset to zero
                                numberOfReset[0]++;
                            }
                        }
                        return false; // if no value is possible -> backtrack
                    }
                }
            }
        }
        return true; // if sudoku is solved
    }

    private static boolean valueIsValid(Sudoku sudoku, SudokuCell cell, int possibleValue) {
        List<Integer> sudokuRow = sudoku.getGrid().get(cell.getRowIndex()).stream().map(SudokuCell::getValue).toList();
        List<Integer> sudokuColumn = sudoku.getTransposedGrid().get(cell.getColumnIndex()).stream().map(SudokuCell::getValue).toList();
        List<Integer> block = sudoku.getBlocks().get(cell.getBlockIndex()).stream().map(SudokuCell::getValue).toList();

        return !sudokuRow.contains(possibleValue) && !sudokuColumn.contains(possibleValue) && !block.contains(possibleValue);
    }

    static void solveWithHiddenSingles(Sudoku sudoku){
        // for every possible number, build the list of cells in which the number may be inserted.
        // If this list is just of 1 cell, insert the number in that cell
        for(List<List<SudokuCell>> partitions : getSudokuPartitions(sudoku)){
            for(List<SudokuCell> rowColumnOrBlock : partitions){
                for (int number = 1; number <= 9 ; number++) {
                    List<SudokuCell> possibleCellsForNumber = getPossibleCellsForNumber(rowColumnOrBlock, number);
                    if (possibleCellsForNumber.size() == 1){
                        System.out.printf("Found hidden single: inserting number %d in position %s %n", number, possibleCellsForNumber.getFirst());
                        updateCellAndSudoku(sudoku, possibleCellsForNumber.getFirst(), number);
                    }
                }
            }
        }
    }

    private static List<SudokuCell> getPossibleCellsForNumber(List<SudokuCell> rowColumnOrBlock, int number) {
        List<SudokuCell> possibleCellsForNumber = new ArrayList<>();
        if (!rowColumnOrBlock.stream().map(SudokuCell::getValue).toList().contains(number)){
            for (SudokuCell cell : rowColumnOrBlock) {
                if (cell.getValue() == 0) {
                    List<Integer> cellPossibleValues = cell.getPossibleValues();
                    if (cellPossibleValues.contains(number)){
                        possibleCellsForNumber.add(cell);
                    }
                }
            }
        }
        return possibleCellsForNumber;
    }

    static void solveWithHiddenPairs(Sudoku sudoku){
        for(List<SudokuCell> block : sudoku.getBlocks()){
            HashMap<Integer, List<SudokuCell>> mapNumberToPossibleCells = new HashMap<>();
            for (int number = 1; number <= 9 ; number++) {
                List<SudokuCell> possibleCellsForNumber = new ArrayList<>();
                if (!block.stream().map(SudokuCell::getValue).toList().contains(number)){
                    for (SudokuCell cell : block) {
                        if (cell.getValue() == 0) {
                            // if the number appears only in a cell, that is the cell in which it has to be inserted
                            List<Integer> cellPossibleValues = cell.getPossibleValues();
                            if (cellPossibleValues.contains(number) && cellPossibleValues.size() >2){
                                possibleCellsForNumber.add(cell);
                            }
                        }
                    }
                }
                if (possibleCellsForNumber.size() == 2){
                    mapNumberToPossibleCells.put(number, possibleCellsForNumber);
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
        for(List<SudokuCell> block : sudoku.getBlocks()){
            List<SudokuCell> cellsWith2PossibleValues = block.stream().filter(cell ->
                    cell.getPossibleValues() != null && cell.getPossibleValues().size() == 2).toList();

            for (int i = 0; i < cellsWith2PossibleValues.size(); i++) {
                for (int j = i+1; j < cellsWith2PossibleValues.size(); j++) {
                    SudokuCell cell1 = cellsWith2PossibleValues.get(i);
                    SudokuCell cell2 = cellsWith2PossibleValues.get(j);
                    if(cell1.getPossibleValues().containsAll(cell2.getPossibleValues()) && cell2.getPossibleValues().containsAll(cell1.getPossibleValues())) {
                        // found obvious pair, now delete the pair from the other cells of the block
                        List<SudokuCell> otherCells = block.stream().filter(cell ->
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
            for (List<SudokuCell> rowColumnOrblock : sudokuPartition){
                for (int number = 1; number <= 9; number++) {
                    List<SudokuCell> possibleCellsForNumber = new ArrayList<>();
                    if (!rowColumnOrblock.stream().map(SudokuCell::getValue).toList().contains(number)){ // if block does not contain the number
                        for (SudokuCell cell : rowColumnOrblock) {
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
        return List.of(sudoku.getGrid(), sudoku.getTransposedGrid(), sudoku.getBlocks());
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
        List<SudokuCell> block = sudoku.getBlocks().get(cell.getBlockIndex());
        for (SudokuCell cellInblock : block){
            if (cellInblock.getPossibleValues() != null) {
                cellInblock.getPossibleValues().remove(valueToInsert);
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

        // check if rows, columns and blocks contain numbers from 1 to 9
        for (List<List<SudokuCell>> sudokuPartition : getSudokuPartitions(sudoku)) {
            for (List<SudokuCell> rowColumnOrblock : sudokuPartition){
                if(!rowColumnOrblock.stream().map(SudokuCell::getValue).toList().containsAll(sudokuValues)){
                    return false;
                }
            }
        }

        return true;
    }

    public static List<List<SudokuCell>> buildBlocksFromSudoku(List<List<SudokuCell>> sudoku){
        List<List<SudokuCell>> blocks = new ArrayList<>(9);

        IntStream.range(0,9).forEach(_ -> blocks.add(new ArrayList<>(9)));
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                blocks.get(0).add(sudoku.get(i).get(j));
                blocks.get(1).add(sudoku.get(i).get(j + 3));
                blocks.get(2).add(sudoku.get(i).get(j + 6));
                blocks.get(3).add(sudoku.get(i + 3).get(j));
                blocks.get(4).add(sudoku.get(i + 3).get(j + 3));
                blocks.get(5).add(sudoku.get(i + 3).get(j + 6));
                blocks.get(6).add(sudoku.get(i + 6).get(j));
                blocks.get(7).add(sudoku.get(i + 6).get(j + 3));
                blocks.get(8).add(sudoku.get(i + 6).get(j + 6));
            }
        }
        return blocks;
    }

    static void printSudoku(List<List<SudokuCell>> sudoku){
        StringBuilder printableSudoku = new StringBuilder();
        for (List<SudokuCell> sudokuRow : sudoku){
            for (SudokuCell cell : sudokuRow){
                if( cell.getValue() != 0) {
                    printableSudoku.append("| ").append(cell.getValue()).append(" ");
                } else{
                    printableSudoku.append("|   ");
                }
            }
            printableSudoku.append("|\n");
            if (sudokuRow.getFirst().getRowIndex() %3 == 2) {
                printableSudoku.append("-------------------------------------\n");
            }
        }
        System.out.println(printableSudoku);
    }

    static List<Integer> buildCellPossibleValuesList(List<SudokuCell> sudokuRow, List<SudokuCell> sudokuColumn, List<SudokuCell> block) {
        return IntStream.rangeClosed(1,9).filter(possibleNumber ->
                !sudokuRow.stream().map(SudokuCell::getValue).toList().contains(possibleNumber) &&
                !sudokuColumn.stream().map(SudokuCell::getValue).toList().contains(possibleNumber) &&
                !block.stream().map(SudokuCell::getValue).toList().contains(possibleNumber))
                .boxed().collect(Collectors.toList());
    }

    static List<SudokuCell> findBlockFromCellIndexes(List<List<SudokuCell>> blocks, int rowIndex, int columnIndex){
        if (List.of(0,1,2).contains(rowIndex)) {
            if (List.of(0, 1, 2).contains(columnIndex)) return blocks.get(0);
            else if (List.of(3, 4, 5).contains(columnIndex)) return blocks.get(1);
            else return blocks.get(2);
        }else if (List.of(3,4,5).contains(rowIndex)){
            if (List.of(0,1,2).contains(columnIndex)) return blocks.get(3);
            else if (List.of(3,4,5).contains(columnIndex)) return blocks.get(4);
            else return blocks.get(5);
        }else {
            if (List.of(0, 1, 2).contains(columnIndex)) return blocks.get(6);
            else if (List.of(3, 4, 5).contains(columnIndex)) return blocks.get(7);
            else return blocks.get(8);
        }
    }
}
