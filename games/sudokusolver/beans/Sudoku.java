package games.sudokusolver.beans;

import java.util.List;

public class Sudoku {

    private List<List<SudokuCell>> grid;
    private List<List<SudokuCell>> flattenedSubmatrices;
    private List<List<SudokuCell>> transposedGrid;
    private SudokuDifficulty difficulty;

    public Sudoku() {}

    public List<List<SudokuCell>> getGrid() {
        return grid;
    }

    public void setGrid(List<List<SudokuCell>> grid) {
        this.grid = grid;
    }

    public List<List<SudokuCell>> getFlattenedSubmatrices() {
        return flattenedSubmatrices;
    }

    public void setFlattenedSubmatrices(List<List<SudokuCell>> flattenedSubmatrices) {
        this.flattenedSubmatrices = flattenedSubmatrices;
    }

    public List<List<SudokuCell>> getTransposedGrid() {
        return transposedGrid;
    }

    public void setTransposedGrid(List<List<SudokuCell>> transposedGrid) {
        this.transposedGrid = transposedGrid;
    }

    public SudokuDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(SudokuDifficulty difficulty) {
        this.difficulty = difficulty;
    }
}
