package games.connectfour;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static games.MatrixUtil.*;

public class ConnectFour {

    public static void main(String[] args) {
        System.out.println("Welcome to java connect four game!");
        while(true){ // loop for playing games
            int[][] gameMatrix = new int[6][7]; // connect four has 6 rows and 7 columns
            printGameMatrix(gameMatrix);
            int player = 1;
            Scanner scanner = new Scanner(System.in);
            while(true){ // loop for turns
                System.out.printf("Player %d, where do you want to place your '%d'? Use a number between 1 and 7%n", player, player);
                String playerColumn = scanner.nextLine();
                Pattern pattern = Pattern.compile("[1-7]");
                Matcher matcher = pattern.matcher(playerColumn);
                if (!matcher.matches()) {
                    System.out.println("Please insert a number between 1 and 7");
                    continue;
                }
                int player1Column = Integer.parseInt(playerColumn)-1;
                // populate game board
                boolean success = populateGameBoardColumn(gameMatrix, player1Column, player);
                if (!success){
                    System.out.println("Column is already full, please insert another column number");
                    continue;
                }
                printGameMatrix(gameMatrix);
                int winner = checkGameMatrixForWinner(gameMatrix);
                if (winner == 0) {
                    System.out.println("It's a draw");
                    break;
                } else if (winner == 1 || winner == 2) {
                    System.out.printf("Player %d wins%n", winner);
                    break;
                }

                if (player == 1){
                    player = 2;
                } else{
                    player = 1;
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

    // todo: test method with random matrices
    private static int checkGameMatrixForWinner(int[][] gameMatrix){
        if (findMinimumInMatrix(gameMatrix) != 0) { // if the matrix doesn't have zeros
            return 0; // the game is in draw condition
        }

        // use 4x4 sub-matrices and apply them the algorithm developed for tic-tac-toe, just extended. Move the submatrix around to cover all the board with offset
        int[][] subMatrix = new int[4][4];
        for (int k=0; k<=2; k++){ // row offset
            for (int l = 0; l <= 3; l++) { // column offset
                // start from bottom 4 rows
                // start from first column on the left
                for (int i = 0; i <=3; i++) {
                    for (int j = 0; j <= 3; j++) {
                        subMatrix[i][j] = gameMatrix[i+k][j+l]; // build submatrix from gameMatrix using offsets
                    }
                }
                if (checkMatrixAllZeroes(subMatrix)){ // if submatrix is all zeros
                    continue;
                }

                int[][] transposedSubMatrix = transposeMatrix(subMatrix);
                int[] subMatrixDiagonal = {subMatrix[0][0],subMatrix[1][1], subMatrix[2][2], subMatrix[3][3]};
                int[] subMatrixAntiDiagonal = {subMatrix[3][0], subMatrix[2][1], subMatrix[1][2], subMatrix[0][3]};

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

    private static boolean populateGameBoardColumn(int[][] gameMatrix, int column, int player) {
        for (int i = 5; i >=0; i--) {
            if(gameMatrix[i][column] == 0){
                gameMatrix[i][column] = player;
                return true;
            }
        }
        return false;
    }

    private static void printGameMatrix(int[][] gameMatrix){
        for (int i = 0; i < 6; i++) {
            // todo: print '' instead of zeros
            System.out.println(Arrays.toString(gameMatrix[i]).replaceAll("0", "").replaceAll(",", "|"));
        }
    }
}
