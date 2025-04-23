package games.go;

import games.MatrixCoordinates;
import games.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class GoBoard {

    //9x9, 13x13 or 19x19 intersections
    private final Stone[][] board;

    public GoBoard(int dimension) {
        board = new Stone[dimension][dimension];
    }

    public void printBoard() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  ");
        for (int i = 0; i < board.length; i++) {
            if (i<10) {
                stringBuilder.append(" ").append(i + 1).append(" ");
            } else{
                stringBuilder.append(i + 1).append(" ");
            }
        }

        System.out.println(stringBuilder);
        for (int i = 0; i < board.length; i++) {
            if (i<9){
                System.out.print(i+1 + " ");
            } else {
                System.out.print(i+1);
            }
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == null) {
                    System.out.print(" + ");
                } else {
                    System.out.print(board[i][j].getColor().equals(PieceColor.BLACK) ? " ○ " : " ● ");
                }
            }
            System.out.println(i+1 + " ");
        }
        System.out.println(stringBuilder);
    }

    public Stone[][] getBoard() {
        return board;
    }

    public boolean checkKoRule(){
        return false;
    }

    public boolean isStoneOrGroupAlive(Stone stoneToCheck){
        MatrixCoordinates stoneToCheckCoordinates = getStoneMatrixCoordinates(stoneToCheck);
        PieceColor stoneToCheckColor = stoneToCheck.getColor();
        int stoneToCheckRowIndex = stoneToCheckCoordinates.getRowIndex();
        int stoneToCheckColumnIndex = stoneToCheckCoordinates.getColumnIndex();

        Stone upperStone = getStoneWithMatrixCoordinates(stoneToCheckRowIndex -1, stoneToCheckColumnIndex);
        Stone lowerStone = getStoneWithMatrixCoordinates(stoneToCheckRowIndex +1, stoneToCheckColumnIndex);
        Stone rightStone = getStoneWithMatrixCoordinates(stoneToCheckRowIndex, stoneToCheckColumnIndex +1);
        Stone leftStone = getStoneWithMatrixCoordinates(stoneToCheckRowIndex, stoneToCheckColumnIndex -1);

        List<Stone> adjacentStones = new ArrayList<>();
        adjacentStones.add(upperStone);
        adjacentStones.add(lowerStone);
        adjacentStones.add(leftStone);
        adjacentStones.add(rightStone);

        // edge cases: stone in the perimeter of the board or in one of the four edges of the grid
        if (stoneToCheckRowIndex == 0){
            adjacentStones.remove(upperStone);
        } else if (stoneToCheckRowIndex == board.length -1){
            adjacentStones.remove(lowerStone);
        }
        if (stoneToCheckColumnIndex == 0){
            adjacentStones.remove(leftStone);
        } else if (stoneToCheckColumnIndex == board.length-1){
            adjacentStones.remove(rightStone);
        }

        if (adjacentStones.contains(null)){ // if one of the adjacent stones is null, the stone or group is alive
            return true;
        } else{
            // Check the colors of stones around
            List<PieceColor> adjacentStonesColors = adjacentStones.stream().map(Stone::getColor).distinct().toList();
            if (!adjacentStonesColors.contains(stoneToCheckColor)){
                // the stone is dead because all stones around are of different colors
                return false;
            } else{
                // Stone belongs to group. Find adjacent group stones
                List<Stone> adjacentGroupStones = adjacentStones.stream().filter(stoneAround -> stoneAround.getColor().equals(stoneToCheckColor)).toList();
                for (Stone groupStone : adjacentGroupStones) {
                    return isStoneOrGroupAlive(groupStone);
                }
            }
        }
        return false;
    }

    public void checkBoardState(){
        // check the liberties of pieces and remove the pieces that do not have liberties
    }

    public void countScore(){
        // count captured pieces from both players and add territories
    }

    public MatrixCoordinates getStoneMatrixCoordinates(Stone stone){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != null && board[i][j].equals(stone)){
                    return new MatrixCoordinates(i,j);
                }
            }
        }
        return null;
    }

    public Stone getStoneWithMatrixCoordinates(int row, int column){
        try{
            return board[row][column];
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
            System.err.println(arrayIndexOutOfBoundsException.getMessage());
            return null;
        }
    }

    public Stone getStoneWithBoardCoordinates(int row, int column){
        return getStoneWithMatrixCoordinates(row-1,column-1);
    }
}
