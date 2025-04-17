
package games.chess;

import games.chess.beans.ChessBoard;
import games.chess.beans.ChessColor;
import games.chess.beans.Player;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class allows to play chess using a command-line approach. The moves are made using board notation (e.g. e2e4 moves a pawn two squares ahead)
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
            Player whitePlayer = new Player("White Player", ChessColor.WHITE);
            Player blackPlayer = new Player("Black Player", ChessColor.BLACK);
            board.printBoardWithLetters();
            Player activePlayer = whitePlayer;
            Scanner scanner = new Scanner(System.in);
            while(!board.isGameOver()){
                System.out.print(activePlayer.getName() + ", it's your turn. Write your move using coordinate notation\n");
                String playerMove = scanner.nextLine();
                Pattern pattern = Pattern.compile("^(O-O(-O)?|[KQRBN]?[a-h]?[1-8]?x?[a-h][1-8](=[QRBN])?[+#]?)$");
                Matcher matcher = pattern.matcher(playerMove);
                if (!matcher.matches()) {
                    System.out.println("Please insert a valid coordinate notation like e2e4");
                    continue;
                }
                if(!activePlayer.movePieceWithAlgebraicNotation(board, playerMove)) {
                    continue;
                }
                board.printBoardWithLetters();
                if(activePlayer.equals(whitePlayer)){
                    activePlayer = blackPlayer;
                } else{
                    activePlayer = whitePlayer;
                }
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
