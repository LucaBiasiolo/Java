package games.tictactoe;

import games.MatrixUtil;

import java.util.Arrays;

import static games.MatrixUtil.*;

public class TicTacToeUtil {

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
    public static int checkGameMatrixForWinner(int[][] gameMatrix) {
        int[][] transposedMatrix = MatrixUtil.createTransposedMatrix(gameMatrix);
        int[] matrixDiagonal = findMatrixDiagonal(gameMatrix);
        int[] matrixAntiDiagonal = findMatrixAntiDiagonal(gameMatrix);

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

    static void printGameMatrix(int[][] gameMatrix) {
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
