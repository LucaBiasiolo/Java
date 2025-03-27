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

    public static int[][] transposeMatrix(int[][] matrix) {
        int[][] transposedMatrix = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                transposedMatrix[i][j] = matrix[j][i];
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
