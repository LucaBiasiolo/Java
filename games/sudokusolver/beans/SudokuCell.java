package games.sudokusolver.beans;

import java.util.List;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "Cell (" + rowIndex +
                "," +columnIndex +
                "), possibleValues=" + possibleValues;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SudokuCell that = (SudokuCell) o;
        return rowIndex == that.rowIndex && columnIndex == that.columnIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowIndex, columnIndex);
    }
}
