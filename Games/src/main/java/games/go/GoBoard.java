package games.go;

import games.MatrixCoordinates;
import games.PieceColor;

import java.util.*;

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

    public boolean isGroupAlive(Stone startingStone){
       List<Stone> group = findGroup(startingStone, null);
       for (Stone stoneOfGroup : group){
           MatrixCoordinates stoneOfGroupCoordinates = getStoneMatrixCoordinates(stoneOfGroup);
           List<Stone> adjacentStones = getAdjacentStonesByMatrixCoordinates(stoneOfGroupCoordinates.getRowIndex(), stoneOfGroupCoordinates.getColumnIndex());
           if (adjacentStones.contains(null)){
              return true;
           }
       }
       return false;
    }

    private List<Stone> getAdjacentStonesByMatrixCoordinates(int row, int column) {
        Stone upperStone = getStoneWithMatrixCoordinates(row -1, column);
        Stone lowerStone = getStoneWithMatrixCoordinates(row +1, column);
        Stone rightStone = getStoneWithMatrixCoordinates(row, column +1);
        Stone leftStone = getStoneWithMatrixCoordinates(row, column -1);

        List<Stone> adjacentStones = new ArrayList<>();
        adjacentStones.add(upperStone);
        adjacentStones.add(lowerStone);
        adjacentStones.add(leftStone);
        adjacentStones.add(rightStone);

        // edge cases: stone in the perimeter of the board or in one of the four edges of the grid
        if (row == 0){
            adjacentStones.remove(upperStone);
        } else if (row == board.length -1){
            adjacentStones.remove(lowerStone);
        }
        if (column == 0){
            adjacentStones.remove(leftStone);
        } else if (column == board.length-1){
            adjacentStones.remove(rightStone);
        }
        return adjacentStones;
    }

    public List<Stone> findGroup(Stone startingStone, Set<Stone> visited) {
        List<Stone> group = new ArrayList<>();
        if (visited == null){
            visited = new HashSet<>();
        }
        if (visited.contains(startingStone)) {
            return group; // Skip already visited stones
        }
        visited.add(startingStone);
        group.add(startingStone);

        MatrixCoordinates startingStoneCoordinatest = getStoneMatrixCoordinates(startingStone);
        List<Stone> adjacentStones = getAdjacentStonesByMatrixCoordinates(startingStoneCoordinatest.getRowIndex(), startingStoneCoordinatest.getColumnIndex())
                .stream().filter(Objects::nonNull).toList();
        List<Stone> adjacentGroup = adjacentStones.stream()
                .filter(adjacentStone -> adjacentStone.getColor().equals(startingStone.getColor()))
                .toList();

        for (Stone stoneOfGroup : adjacentGroup) {
            group.addAll(findGroup(stoneOfGroup, visited)); // Pass the visited set recursively
        }
        return group;
    }

    public boolean removeDeadStones(Player blackPlayer, Player whitePlayer){
        // check the liberties of pieces and remove the pieces that do not have liberties
        boolean boardChanged = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != null){
                    boolean isStoneOrGroupAlive = isGroupAlive(board[i][j]);
                    if (!isStoneOrGroupAlive){
                        Stone stoneToRemove = board[i][j];
                        PieceColor stoneColor = stoneToRemove.getColor();
                        if (stoneColor.equals(PieceColor.WHITE)){
                            blackPlayer.setScore(blackPlayer.getScore() +1);
                        } else{
                            whitePlayer.setScore(whitePlayer.getScore() +1);
                        }
                        board[i][j] = null;
                        boardChanged = true;
                    }
                }
            }
        }
        return boardChanged;
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
            return null;
        }
    }

    public Stone getStoneWithBoardCoordinates(int row, int column){
        return getStoneWithMatrixCoordinates(row-1,column-1);
    }
}
