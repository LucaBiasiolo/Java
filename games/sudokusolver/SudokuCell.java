package games.sudokusolver;

import java.util.List;

public class SudokuCell {

    private int rowIndex;
    private int columnIndex;
    private int submatrixIndex;
    private int value;
    private List<Integer> possibleValues;

    public SudokuCell(int rowIndex, int columnIndex, int value) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.value = value;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<Integer> getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(List<Integer> possibleValues) {
        this.possibleValues = possibleValues;
    }

    public int getSubmatrixIndex() {
        return submatrixIndex;
    }

    public void setSubmatrixIndex(int submatrixIndex) {
        this.submatrixIndex = submatrixIndex;
    }
}
