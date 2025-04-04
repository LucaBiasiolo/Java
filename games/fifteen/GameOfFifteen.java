package games.fifteen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameOfFifteen {

    private List<List<Integer>> grid;

    public GameOfFifteen() {
        this.grid = new ArrayList<>(4);
        populateGrid();
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
        } else{
            System.out.printf("Movement in direction %s not possible %n", direction);
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
                if (number == 0){
                    printableGameBoard.append("|    ");
                } else if (number < 10) {
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

    public boolean isGameEnded(){
        // check if board has numbers from 1 to 15 in line starting from the top-left corner
        List<List<Integer>> finalGrid = List.of(List.of(1,2,3,4),List.of(5,6,7,8),List.of(9,10,11,12), List.of(13,14,15,0));
        return grid.equals(finalGrid);
    }

    public void play(){
        System.out.println("Welcome to Java Game of 15!");
        System.out.println("Please make your moves using keys w,a,s,d on the keyboard");
        System.out.println("This is the starting configuration of the board");
        print();
        while(!isGameEnded()){
            Scanner scanner = new Scanner(System.in);
            String userMove = scanner.nextLine();
            Pattern pattern = Pattern.compile("[wasd]");
            Matcher matcher = pattern.matcher(userMove);
            MoveDirection direction = null;
            if(matcher.matches()){
                direction = switch (userMove) {
                    case "w" -> MoveDirection.UP;
                    case "a" -> MoveDirection.LEFT;
                    case "s" -> MoveDirection.DOWN;
                    case "d" -> MoveDirection.RIGHT;
                    default -> direction;
                };
            }
            if(direction != null){
                move(direction);
                print();
            }
        }
    }
}
