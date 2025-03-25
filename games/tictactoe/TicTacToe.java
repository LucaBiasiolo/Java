package games.tictactoe;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static games.tictactoe.TicTacToeUtil.checkGameMatrixForWinner;
import static games.tictactoe.TicTacToeUtil.printGameMatrix;

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
}