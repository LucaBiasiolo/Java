package games.sudokusolver;

import java.util.List;

public class Sudoku {

    private List<List<Integer>> sudoku;
    private List<List<Integer>> flattenedSubmatrices;
    private SudokuDifficulty difficulty;

    public Sudoku(List<List<Integer>> sudoku, SudokuDifficulty difficulty) {
        this.sudoku = sudoku;
        this.difficulty = difficulty;
    }

    public List<List<Integer>> getSudoku() {
        return sudoku;
    }

    public void setSudoku(List<List<Integer>> sudoku) {
        this.sudoku = sudoku;
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
