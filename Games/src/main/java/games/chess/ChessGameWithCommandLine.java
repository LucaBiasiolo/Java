
package games.chess;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static games.chess.ChessBoard.getColumnIndexFromCoordinate;
import static games.chess.ChessBoard.getRowIndexFromCoordinate;

/**
 * This class manages the game of games.chess using a command-line approach (i.e. the moves are made using algebraic notation)
 * */
public class ChessGameWithCommandLine {

    public static void main(String[] args) {
        ChessGameWithCommandLine game = new ChessGameWithCommandLine();
        game.play();
    }

    public void play(){
        System.out.println("Welcome to Java Game of games.chess using command line!");
        while(true){ // loop to play games
            ChessBoard board = new ChessBoard();
            Player player1 = new Player("Player1", true, board);
            Player player2 = new Player("Player2", false, board);
            board.printBoard();
            boolean whiteTurn = true;
            Scanner scanner = new Scanner(System.in);
            while(!board.isGameOver()){ // todo: loop continues until game finishes
                System.out.print((whiteTurn ? "White " : "Black ") + "player, it's your turn. Write your move using coordinate notation\n");
                System.out.println("Remember that columns are named with letters from a to h starting from left and row are named with numbers from 1 to 8 starting from white pieces");
                String playerMove = scanner.nextLine();
                Pattern pattern = Pattern.compile("[a-h][1-8][a-h][1-8]");
                Matcher matcher = pattern.matcher(playerMove);
                if (!matcher.matches()) {
                    System.out.println("Please insert a valid coordinate notation like e2e4");
                    continue;
                }
                int[] coordinates = parsePlayerMove(playerMove);
                if(whiteTurn) {
                    if(!player1.movePiece(board, coordinates[0], coordinates[1], coordinates[2], coordinates[3])){
                        continue;
                    }
                } else{
                    if(!player2.movePiece(board, coordinates[0],coordinates[1],coordinates[2],coordinates[3])){
                        continue;
                    }
                }
                board.printBoard();
                whiteTurn = !whiteTurn;
            }
        }
    }

    public int[] parsePlayerMove(String playerMove){
        String startingPosition = playerMove.substring(0,2);
        String endingPosition = playerMove.substring(2,4);

        String startColumnLetter = String.valueOf(startingPosition.charAt(0));
        Integer startRow = Integer.valueOf(startingPosition.substring(1,2));
        String endColumnLetter = String.valueOf(endingPosition.charAt(0));
        Integer endRow = Integer.valueOf(endingPosition.substring(1,2));

        int startX = getRowIndexFromCoordinate(startRow);
        int startY = getColumnIndexFromCoordinate(startColumnLetter);
        int endX = getRowIndexFromCoordinate(endRow);
        int endY = getColumnIndexFromCoordinate(endColumnLetter);

        return new int[]{startX, startY, endX, endY};
    }
}
