package games.fifteen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static games.MatrixUtil.transposeMatrix;

public class GameBoard {

    private List<List<Integer>> grid;

    public GameBoard() {
        this.grid = new ArrayList<>(4);
    }

    public List<List<Integer>> getGrid() {
        return grid;
    }

    public void setGrid(List<List<Integer>> grid) {
        this.grid = grid;
    }

    public void populateGrid(){
        List<Integer> numbers = new ArrayList<>(15);
        for (int i = 0; i <= 15; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        int index = 0;
        for (int i = 0; i < 4; i++) {
            this.grid.add(new ArrayList<>(4));
            for (int j = 0; j < 4; j++) {
                Integer number = numbers.get(index++);
                this.grid.get(i).add(number);
            }
        }
    }

    public boolean isMovementPossible(MoveDirection direction){
        int rowIndexOfZeroCell = -1;
        int columnIndexOfZeroCell = -1;
        for(int i =0; i<grid.size();i++){
            List<Integer> row = grid.get(i);
            for (int j = 0; j < row.size(); j++) {
                if (row.get(j) == 0){
                    rowIndexOfZeroCell = i;
                    columnIndexOfZeroCell = j;
                }
            }
        }
        return switch (direction) {
            case UP -> rowIndexOfZeroCell != 3; // if null cell is not on the last row
            case DOWN -> rowIndexOfZeroCell != 0; // if null cell is not on the first row
            case LEFT -> columnIndexOfZeroCell != 3; // if null cell is not in the right-most column
            case RIGHT -> columnIndexOfZeroCell != 0; // if null cell is not in the leftmost column
        };
    }

    public void print() {
        StringBuilder printableGameBoard = new StringBuilder();
        for (List<Integer> row : grid) {
            for (Integer number : row) {
                if (number < 10) {
                    printableGameBoard.append("| ").append(number).append("  ");
                } else{
                    printableGameBoard.append("| ").append(number).append(" ");
                }
            }
            printableGameBoard.append("|\n");
            printableGameBoard.append("---------------------\n");
        }
        System.out.println(printableGameBoard);
    }
}
