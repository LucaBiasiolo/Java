package games;

import java.util.ArrayList;
import java.util.List;

public class MatrixUtil {

    public static int[][] getUnityMatrix(int dimension){
        int[][] unityMatrix = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            unityMatrix[i][i] = 1;
        }
        return unityMatrix;
    }

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
    
    public static int[] findMatrixDiagonal(int[][] matrix){
        int[] diagonal = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            diagonal[i] = matrix[i][i];
        }
        return diagonal;
    }
    
    public static int[] findMatrixAntiDiagonal(int[][] matrix){
        int[] antiDiagonal = new int[matrix.length];
        System.arraycopy(matrix[matrix.length - 1], 0, antiDiagonal, 0, matrix.length);
        return antiDiagonal;
    }
    
    public static int[][] findSubmatrix(int[][] matrix, int fromColumn, int toColumn, int fromRow, int toRow){
        int numberOfRows = toRow-fromRow+1;
        int numberOfColumns = toColumn-fromColumn +1;
        int[][] submatrix = new int[numberOfRows][numberOfColumns];
        for (int i = 0; i < numberOfRows; i++) {
            System.arraycopy(matrix[fromRow + i], fromColumn, submatrix[i], 0, numberOfColumns);
        }
        return submatrix;
    }

    public static int[][] findSquareSubmatrix(int[][] matrix, int fromRow, int fromColumn, int dimension){
        int[][] submatrix = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            System.arraycopy(matrix[fromRow + i], fromColumn, submatrix[i], 0, dimension);
        }
        return submatrix;
    }

    public static int[][] createTransposedMatrix(int[][] matrix) {
        int[][] transposedMatrix = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                transposedMatrix[i][j] = matrix[j][i];
            }
        }
        return transposedMatrix;
    }

    public static <T> List<List<T>> createTransposedMatrix(List<List<T>> matrix) {
        List<List<T>> transposedMatrix = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            transposedMatrix.add(new ArrayList<>());
            for (List<T> matrixRow : matrix) {
                transposedMatrix.get(i).add(matrixRow.get(i));
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
