package games.sudokusolver;

import java.util.List;

public class Sudoku {

    private List<List<Integer>> grid;
    private List<List<Integer>> flattenedSubmatrices;
    private SudokuDifficulty difficulty;

    public Sudoku(List<List<Integer>> sudoku, SudokuDifficulty difficulty) {
        this.grid = sudoku;
        this.difficulty = difficulty;
    }

    public List<List<Integer>> getGrid() {
        return grid;
    }

    public void setGrid(List<List<Integer>> grid) {
        this.grid = grid;
    }

    public List<List<Integer>> getFlattenedSubmatrices() {
        return flattenedSubmatrices;
    }

    public void setFlattenedSubmatrices(List<List<Integer>> flattenedSubmatrices) {
        this.flattenedSubmatrices = flattenedSubmatrices;
    }

    public SudokuDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(SudokuDifficulty difficulty) {
        this.difficulty = difficulty;
    }
}
