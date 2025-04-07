package games.connectfour;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static games.MatrixUtil.*;

// todo: improve game encapsulation
// todo: write tests
// todo: add saving and loading of the game
public class ConnectFourGame {

    private final int[][] gameMatrix = new int[6][7]; //connect four has 6 rows and 7 columns

    public int[][] getGameMatrix() {
        return gameMatrix;
    }

    public ConnectFourGame() {}

    public void play() {
        System.out.println("Welcome to java connect four game!");
        while(true){ // loop for playing games
            printGameMatrix();
            int player = 1;
            String playerSymbol = "X";
            Scanner scanner = new Scanner(System.in);
            while(true){ // loop for turns
                System.out.printf("Player %d, where do you want to place your '%s'? Use a number between 1 and 7%n", player, playerSymbol);
                String playerColumn = scanner.nextLine();
                Pattern pattern = Pattern.compile("[1-7]");
                Matcher matcher = pattern.matcher(playerColumn);
                if (!matcher.matches()) {
                    System.out.println("Please insert a number between 1 and 7");
                    continue;
                }
                int column = Integer.parseInt(playerColumn)-1;
                if(isMovePossible(column)){
                    playerMove(column, player);
                } else {
                    System.out.println("Column is already full, please insert another column number");
                    continue;
                }
                printGameMatrix();
                int winner = checkGameMatrixForWinner();
                if (winner == 0) {
                    System.out.println("It's a draw");
                    break;
                } else if (winner == 1 || winner == 2) {
                    System.out.printf("Player %d wins%n", winner);
                    break;
                }

                if (player == 1){
                    player = 2;
                    playerSymbol = "O";
                } else{
                    player = 1;
                    playerSymbol = "X";
                }
            }
            System.out.println("Do you wish to play again? y/n");
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

    private boolean isMovePossible(int column) {
        return Arrays.stream(createTransposedMatrix(gameMatrix)[column])
                .anyMatch(value -> value == 0);
    }

    public void playerMove(int column, int player) {
        for (int i = 5; i >=0; i--) {
            if(this.gameMatrix[i][column] == 0){
                this.gameMatrix[i][column] = player;
                break;
            }
        }
    }

    // todo: test method with random matrices
    private int checkGameMatrixForWinner(){
        if (findMinimumInMatrix(gameMatrix) != 0) { // if the matrix doesn't have zeros
            return 0; // the game is in draw condition
        }

        // use 4x4 sub-matrices and apply them the algorithm developed for tic-tac-toe, just extended. Move the submatrix around to cover all the board with offset
        for (int k=0; k<=2; k++){ // row offset
            for (int l = 0; l <= 3; l++) { // column offset
                // start from bottom 4 rows
                // start from first column on the left
                int[][] subMatrix = findSquareSubmatrix(gameMatrix, k, l, 4);
                if (checkMatrixAllZeroes(subMatrix)){ // if submatrix is all zeros
                    continue;
                }

                int[][] transposedSubMatrix = createTransposedMatrix(subMatrix);
                int[] subMatrixDiagonal = findMatrixDiagonal(subMatrix);
                int[] subMatrixAntiDiagonal = findMatrixAntiDiagonal(subMatrix);

                for (int i = 1; i <3; i++) { // loop for gamer (1,2)
                    for (int j = 0; j <= 3; j++) {
                        // check rows
                        if (Arrays.equals(subMatrix[j], new int[]{i, i, i,i})){
                            return i;
                        }
                        // check columns
                        else if (Arrays.equals(transposedSubMatrix[j], new int[]{i,i,i,i})){
                            return i;
                        }
                    }
                    // check diagonals
                    if (Arrays.equals(subMatrixDiagonal, new int[]{i,i,i,i}) || Arrays.equals(subMatrixAntiDiagonal, new int[]{i,i,i,i})){
                        return i;
                    }
                }
            }
        }
        return -1; // return -1 if game is still ongoing
    }

    public void printGameMatrix(){
        for (int i = 0; i < 6; i++) {
            System.out.println(Arrays.toString(this.gameMatrix[i])
                    .replaceAll("0", " ")
                    .replaceAll("1","X")
                    .replaceAll("2","O")
                    .replaceAll(",", "|")
                    .replaceAll("\\[","|")
                    .replaceAll("]","|"));
        }
    }
}
