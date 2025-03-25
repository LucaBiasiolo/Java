package games;

public class MatrixUtil {

    public static int findMinimumInMatrix(int[][] gameMatrix) {
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

    public static int[][] transposeMatrix(int[][] gameMatrix) {
        int[][] transposedMatrix = new int[gameMatrix.length][gameMatrix.length];
        for (int i = 0; i < gameMatrix.length; i++) {
            for (int j = 0; j < gameMatrix.length; j++) {
                transposedMatrix[i][j] = gameMatrix[j][i];
            }
        }
        return transposedMatrix;
    }

    public static boolean checkMatrixAllZeroes(int[][] subMatrix) {
        for (int[] row : subMatrix) {
            for (int j = 0; j < subMatrix.length; j++) {
                if (row[j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
