package esercizi.tictactoe;

public class MatrixUtil {

    static int findMinimumInMatrix(int[][] gameMatrix) {
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

    static int[][] transposeMatrix(int[][] gameMatrix) {
        int[][] transposedMatrix = new int[3][3];
        for (int i = 0; i < gameMatrix.length; i++) {
            for (int j = 0; j < gameMatrix.length; j++) {
                transposedMatrix[i][j] = gameMatrix[j][i];
            }
        }
        return transposedMatrix;
    }
}
