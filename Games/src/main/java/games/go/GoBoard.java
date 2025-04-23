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
        MatrixCoordinates stoneCoordinates = getStoneMatrixCoordinates(stoneToCheck);
        PieceColor stoneToCheckColor = stoneToCheck.getColor();

        Stone stoneUp = getStoneWithMatrixCoordinates(stoneCoordinates.getRowIndex()-1,stoneCoordinates.getColumnIndex());
        Stone stoneDown = getStoneWithMatrixCoordinates(stoneCoordinates.getRowIndex()+1,stoneCoordinates.getColumnIndex());
        Stone stoneRight = getStoneWithMatrixCoordinates(stoneCoordinates.getRowIndex(),stoneCoordinates.getColumnIndex()+1);
        Stone stoneLeft = getStoneWithMatrixCoordinates(stoneCoordinates.getRowIndex(),stoneCoordinates.getColumnIndex()-1);

        List<Stone> stonesAround = new ArrayList<>();
        stonesAround.add(stoneUp);
        stonesAround.add(stoneDown);
        stonesAround.add(stoneLeft);
        stonesAround.add(stoneRight);

        // edge cases: stone in the perimeter of the board or in one of the four edges of the grid
        if (stoneCoordinates.getRowIndex() == 0){
            stonesAround.remove(stoneUp);
        } else if (stoneCoordinates.getRowIndex() == board.length -1){
            stonesAround.remove(stoneDown);
        }
        if (stoneCoordinates.getColumnIndex() == 0){
            stonesAround.remove(stoneLeft);
        } else if (stoneCoordinates.getColumnIndex() == board.length-1){
            stonesAround.remove(stoneRight);
        }

        if (stonesAround.contains(null)){
            // the stone or group is alive
            return true;
        } else{
            // Check the colors of stones around. If the color is only that of the opponent, the stone is dead
            List<PieceColor> stonesAroundColors = stonesAround.stream().map(Stone::getColor).toList();
            if (!stonesAroundColors.contains(stoneToCheckColor)){
                // the stone is dead because all stones around are of different colors
                //board[stoneCoordinates.getRowIndex()][stoneCoordinates.getColumnIndex()] = null;
                // todo: add removed stone to opponent player
                return false;
            } else{
                // stone belongs to group
                List<Stone> group = stonesAround.stream().filter(stoneAround -> stoneAround.getColor().equals(stoneToCheckColor)).toList();
                for (Stone stoneOfGroup : group) {
                    return isStoneOrGroupAlive(stoneOfGroup);
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
