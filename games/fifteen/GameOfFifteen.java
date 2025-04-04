package games.fifteen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameOfFifteen {

    private List<List<Integer>> grid;

    public GameOfFifteen() {
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

    public void move(MoveDirection direction){
        if (isMovementPossible(direction)){
            List<Integer> zeroCellCoordinates = findZeroCellCoordinates();
            Integer zeroCellRowIndex = zeroCellCoordinates.get(0);
            Integer zeroCellColumnIndex = zeroCellCoordinates.get(1);
            Integer numberToSwitch;
            List<Integer> numberToSwitchCoordinates;
            numberToSwitch = switch (direction) {
                case UP -> {
                    // switch zero-valued cell and cell below
                    numberToSwitchCoordinates = List.of(zeroCellRowIndex + 1, zeroCellColumnIndex);
                    yield grid.get(numberToSwitchCoordinates.get(0)).get(numberToSwitchCoordinates.get(1));
                }
                case DOWN -> {
                    // switch zero-valued cell and cell above
                    numberToSwitchCoordinates = List.of(zeroCellRowIndex -1, zeroCellColumnIndex);
                    yield grid.get(numberToSwitchCoordinates.get(0)).get(numberToSwitchCoordinates.get(1));
                }
                case LEFT -> {
                    // switch zero-valued cell and cell on the right
                    numberToSwitchCoordinates = List.of(zeroCellRowIndex, zeroCellColumnIndex + 1);
                    yield grid.get(numberToSwitchCoordinates.get(0)).get(numberToSwitchCoordinates.get(1));
                }
                case RIGHT -> {
                    // switch zero-valued cell and cell on the left
                    numberToSwitchCoordinates = List.of(zeroCellRowIndex, zeroCellColumnIndex - 1);
                    yield grid.get(numberToSwitchCoordinates.get(0)).get(numberToSwitchCoordinates.get(1));
                }
            };
            grid.get(zeroCellRowIndex).set(zeroCellColumnIndex, numberToSwitch);
            grid.get(numberToSwitchCoordinates.get(0)).set(numberToSwitchCoordinates.get(1), 0);;
        }
    }

    public List<Integer> findZeroCellCoordinates() {
        List<Integer> zeroCellCoordinates = new ArrayList<>();
        outerLoop:
        for(int i =0; i<grid.size();i++){
            List<Integer> row = grid.get(i);
            for (int j = 0; j < row.size(); j++) {
                if (row.get(j) == 0){
                    zeroCellCoordinates.add(i);
                    zeroCellCoordinates.add(j);
                    break outerLoop;
                }
            }
        }
        return zeroCellCoordinates;
    }

    public boolean isMovementPossible(MoveDirection direction){
        List<Integer> zeroCellCoordinates = findZeroCellCoordinates();
        int rowIndexOfZeroCell = zeroCellCoordinates.get(0);
        int columnIndexOfZeroCell = zeroCellCoordinates.get(1);
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
