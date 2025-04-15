
package games.chess;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class manages the game of chess using a command-line approach (i.e. the moves are made using algebraic notation)
 * */
public class ChessGameWithCommandLine {

    public static void main(String[] args) {
        ChessGameWithCommandLine game = new ChessGameWithCommandLine();
        game.play();
    }

    public void play(){
        System.out.println("Welcome to Java game of chess using command line!");
        while(true){ // loop to play games
            ChessBoard board = new ChessBoard();
            Player player1 = new Player("Player1", true, board);
            Player player2 = new Player("Player2", false, board);
            board.printBoardWithLetters();
            boolean whiteTurn = true;
            Scanner scanner = new Scanner(System.in);
            while(!board.isGameOver()){ // todo: loop for turns continues until game finishes
                System.out.print((whiteTurn ? "White " : "Black ") + "player, it's your turn. Write your move using coordinate notation\n");
                System.out.println("Remember that columns are named with letters from a to h starting from left and row are named with numbers from 1 to 8 starting from white pieces");
                String playerMove = scanner.nextLine();
                Pattern pattern = Pattern.compile("[a-h][1-8][a-h][1-8]");
                Matcher matcher = pattern.matcher(playerMove);
                if (!matcher.matches()) {
                    System.out.println("Please insert a valid coordinate notation like e2e4");
                    continue;
                }
                if(whiteTurn) {
                    if(!player1.movePieceWithBoardCoordinates(board, playerMove)){
                        continue;
                    }
                } else{
                    if(!player2.movePieceWithBoardCoordinates(board, playerMove)){
                        continue;
                    }
                }
                board.printBoardWithLetters();
                whiteTurn = !whiteTurn;
            }
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
