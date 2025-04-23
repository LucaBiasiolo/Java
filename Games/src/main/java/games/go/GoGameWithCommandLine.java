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
            while(!(whitePlayer.getPassed() && blackPlayer.getPassed())) { // if both players pass, the game is over
                System.out.println(activePlayer.getName() + ", it's your turn. Write your move using row,column notation (e.g., 1,2) or press p to pass");
                String playerMove = scanner.nextLine();
                Pattern pattern = Pattern.compile("(^\\d,\\d|p)$");
                Matcher matcher = pattern.matcher(playerMove);
                if (!matcher.matches()){
                    System.out.println("Input not valid. Please retry");
                    continue;
                } else {
                    if (playerMove.equals("p")){
                        activePlayer.setPassed(true);
                    } else {
                        activePlayer.setPassed(false);
                        String[] coordinates = playerMove.split(",");
                        activePlayer.moveWithBoardCoordinates(board, Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
                        board.printBoard();
                    }
                }
                if (activePlayer.equals(blackPlayer)){
                    activePlayer = whitePlayer;
                } else{
                    activePlayer = blackPlayer;
                }
            }
            board.countScore();
            System.out.println("Game over! Do you want to play again? y/n: ");
            String userContinue = scanner.nextLine().toLowerCase();
            while (!userContinue.equals("n") && !userContinue.equals("y")) {
                System.out.println("Please insert y to continue or n to exit the program");
                userContinue = scanner.nextLine();
            }
            if (userContinue.equals("n")) {
                System.out.println("Bye!");
                scanner.close();
                break;
            }
        }
    }
}
