package games.fifteen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
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
            grid.get(numberToSwitchCoordinates.get(0)).set(numberToSwitchCoordinates.get(1), 0);
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

    public void printGameBoard() {
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
        System.out.println("Please make your moves using keys w,a,s,d");
        System.out.println("Press c to exit the game, S to save the game and l to load the previous game");
        while(true) { // loop to play games
            System.out.println("This is the starting configuration of the board:");
            printGameBoard();
            userMovesLoop:
            while (!isGameEnded()) { // loop for user moves
                Scanner scanner = new Scanner(System.in);
                String userInput = scanner.nextLine();
                Pattern pattern = Pattern.compile("[wasdcSl]");
                Matcher matcher = pattern.matcher(userInput);
                MoveDirection direction = null;
                if (matcher.matches()) {
                    switch (userInput) {
                        case "w":
                            direction = MoveDirection.UP;
                            break;
                        case "a":
                            direction = MoveDirection.LEFT;
                            break;
                        case "s":
                            direction = MoveDirection.DOWN;
                            break;
                        case "d":
                            direction = MoveDirection.RIGHT;
                            break;
                        case "c":
                            break userMovesLoop;
                        case "S":
                            saveGame();
                            break;
                        case "l":
                            loadGame();
                            System.out.println("This is the loaded game:");
                            printGameBoard();
                            break;
                    }
                }
                if (direction != null) {
                    move(direction);
                    printGameBoard();
                }
            }
            System.out.println("You finished the game! Do you want to play again? y/n: ");
            Scanner scanner = new Scanner(System.in);
            String userContinue = scanner.nextLine().toLowerCase();
            while (!userContinue.equals("n") && !userContinue.equals("y")) {
                System.out.println("Please insert y to continue or n to exit the program");
                userContinue = scanner.nextLine();
            }
            if (userContinue.equals("n")) {
                System.out.println("Bye!");
                scanner.close();
                break;
            }
        }
    }

    void loadGame() {
        try{
            File savedGame = new File("./games/games.fifteen/gameOfFifteen.csv");
            String gameString = Files.readString(savedGame.toPath());
            String[] gridNumbers = gameString.split(",");
            List<List<Integer>> newGrid = new ArrayList<>(4);
            int index = 0;
            for (int i = 0; i < 4; i++) {
                newGrid.add(new ArrayList<>(4));
                for (int j = 0; j < 4; j++) {
                    newGrid.get(i).add(Integer.parseInt(gridNumbers[index++]));
                }
            }
            this.grid = newGrid;
            System.out.println("Loaded previous game from date");
            System.out.println(new Date(savedGame.lastModified()));
        } catch (IOException exception){
            System.err.println("Error during loadGame operation");
            exception.printStackTrace();
        }
    }

    void saveGame() {
        File saveFile = new File("./games/games.fifteen/gameOfFifteen.csv");
        try (FileWriter fileWriter = new FileWriter(saveFile)){
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    stringBuilder.append(this.grid.get(i).get(j)).append(",");
                }
            }
            fileWriter.write(stringBuilder.toString());
            System.out.println("Game was saved successfully");
        } catch (IOException exception){
            System.err.println("Error during saveGame operation");
            exception.printStackTrace();
        }
    }
}
