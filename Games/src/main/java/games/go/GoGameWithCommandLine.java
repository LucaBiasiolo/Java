package games.go;

import games.PieceColor;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoGameWithCommandLine {

    public static void main(String[] args) {
        GoGameWithCommandLine game = new GoGameWithCommandLine();
        game.play();
    }

    public void play(){
        System.out.println("Welcome to Java Go game!");
        while(true){ // loop to play games
            GoBoard board = new GoBoard(9);
            Player whitePlayer = new Player("White Player", PieceColor.WHITE);
            Player blackPlayer = new Player("Black Player", PieceColor.BLACK);
            board.printBoard();
            Player activePlayer = blackPlayer;
            Scanner scanner = new Scanner(System.in);
            while(true) {
                System.out.println(activePlayer.getName() + ", it's your turn. Write your move using row,column notation (e.g., 1,2)");
                String playerMove = scanner.nextLine();
                Pattern pattern = Pattern.compile("(^\\d,\\d)$");
                Matcher matcher = pattern.matcher(playerMove);
                if (!matcher.matches()){
                    System.out.println("Input not valid. Please retry");
                    continue;
                } else {
                    String[] coordinates = playerMove.split(",");
                    activePlayer.moveWithBoardCoordinates(board, Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
                    board.printBoard();
                }
                if (activePlayer.equals(blackPlayer)){
                    activePlayer = whitePlayer;
                } else{
                    activePlayer = blackPlayer;
                }
            }
        }
    }
}
