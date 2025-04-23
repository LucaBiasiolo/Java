package games.go;

import games.PieceColor;

public class GoBoard {

    //9x9, 13x13 or 19x19 crosses
    private final Piece[][] board;

    public GoBoard(int dimension) {
        board = new Piece[dimension][dimension];
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

    public Piece[][] getBoard() {
        return board;
    }

    public void checkBoardState(){
        // check the liberties of pieces and remove the pieces that do not have liberties
    }

    public void countScore(){
        // count captured pieces from both players and add territories
    }
}
