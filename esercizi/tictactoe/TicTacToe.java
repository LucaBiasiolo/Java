package esercizi.tictactoe;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TicTacToe {

    public static void main(String[] args) {
        System.out.println("Welcome to Java tic-tac-toe game!");
        while(true) { // loop for playing games
            int[][] gameMatrix = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
            printGameMatrix(gameMatrix);
            int playerTurn = 1;
            String playerSymbol = "X";
            Scanner scanner = new Scanner(System.in);
            while (true) { // loop for players' turns
                System.out.printf("Player %d, where do you want to place your %s? use n,m where n is row number (from 1 to 3) " +
                        "and m is the column number (from 1 to 3)%n", playerTurn, playerSymbol);

                String player1Input = scanner.nextLine();
                Pattern pattern = Pattern.compile("[1-3],[1-3]");
                Matcher matcher = pattern.matcher(player1Input);
                if (!matcher.matches()) {
                    System.out.println("Please insert two numbers (from 1 to 3) separated by a comma");
                    continue;
                }
                int[] player1Coordinates = {Integer.parseInt(player1Input.split(",")[0]) -1,
                        Integer.parseInt(player1Input.split(",")[1]) -1};
                if (gameMatrix[player1Coordinates[0]][player1Coordinates[1]] == 0) {
                    gameMatrix[player1Coordinates[0]][player1Coordinates[1]] = playerTurn;
                    printGameMatrix(gameMatrix);
                    int winner = checkGameMatrixForWinner(gameMatrix);
                    if (winner == 0) {
                        System.out.println("It's a draw");
                        break;
                    } else if (winner == 1 || winner == 2) {
                        System.out.printf("Player %d wins%n", winner);
                        break;
                    }

                    if (playerTurn ==1) {
                        playerTurn = 2;
                        playerSymbol = "O";
                    } else {
                        playerTurn = 1;
                        playerSymbol = "X";
                    }
                } else{
                    System.out.println("Cell already occupied. Please choose a different cell");
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

    /**
    Check the status of a tic-tac-toe game.

    Parameters:
    @param gameMatrix (int[][]): A 3x3 array representing the game board.
                                       1 represents player 1's move, 2 represents player 2's move,
                                       and 0 represents an empty cell.
    @return
    int: The result of the game.
         1 if player 1 wins,
         2 if player 2 wins,
         0 if the game is a draw,
         -1 if the game is still ongoing.
    */
    private static int checkGameMatrixForWinner(int[][] gameMatrix) {
        int[][] transposedMatrix = transposeMatrix(gameMatrix);
        int[] matrixDiagonal = {gameMatrix[0][0],gameMatrix[1][1], gameMatrix[2][2]};
        int[] matrixAntiDiagonal = {gameMatrix[2][0], gameMatrix[1][1], gameMatrix[0][2]};

        for (int i = 1; i <3; i++) {
            for (int j = 0; j < 2; j++) {
                // check rows
                if (Arrays.equals(gameMatrix[j], new int[]{i, i, i})){
                    return i;
                }
                // check columns
                else if (Arrays.equals(transposedMatrix[j], new int[]{i,i,i})){
                    return i;
                }
            }
            // check diagonals
            if (Arrays.equals(matrixDiagonal, new int[]{i,i,i}) || Arrays.equals(matrixAntiDiagonal, new int[]{i,i,i})){
                return i;
            }
        }
        if (findMinimumInMatrix(gameMatrix) != 0) { // if the matrix doesn't have zeros
            return 0; // the game is in draw condition
        }
        return -1; // return -1 if game is still ongoing
    }

    private static int findMinimumInMatrix(int[][] gameMatrix) {
        int min = Integer.MAX_VALUE;

        for (int[] matrixRow : gameMatrix) {
            for (int j = 0; j < gameMatrix.length; j++) {
                if (matrixRow[j] < min) {
                    min = matrixRow[j];
                }
            }
        }
        return min;
    }

    private static int[][] transposeMatrix(int[][] gameMatrix) {
        int[][] transposedMatrix = new int[3][3];
        for (int i = 0; i < gameMatrix.length; i++) {
            for (int j = 0; j < gameMatrix.length; j++) {
                transposedMatrix[i][j] = gameMatrix[j][i];
            }
        }
        return transposedMatrix;
    }

    private static void printGameMatrix(int[][] gameMatrix) {
        StringBuilder firstRowCells = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            if (gameMatrix[0][i] == 0) {
                firstRowCells.append("|    ");
            } else if (gameMatrix[0][i] == 1) {
                firstRowCells.append("| X  ");
            } else {
                firstRowCells.append("| O  ");
            }
        }

        StringBuilder secondRowCells = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            if (gameMatrix[1][i] == 0) {
                secondRowCells.append("|    ");
            } else if (gameMatrix[1][i] == 1) {
                secondRowCells.append("| X  ");
            } else {
                secondRowCells.append("| O  ");
            }
        }

        StringBuilder thirdRowCells = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            if (gameMatrix[2][i] == 0) {
                thirdRowCells.append("|    ");
            } else if (gameMatrix[2][i] == 1) {
                thirdRowCells.append("| X  ");
            } else {
                thirdRowCells.append("| O  ");
            }
        }

        System.out.printf("""
                 ---  ---  ---
                %s|
                 ---  ---  ---
                %s|
                 ---  ---  ---
                %s|
                 ---  ---  ---
                %n""", firstRowCells, secondRowCells, thirdRowCells);
    }
}