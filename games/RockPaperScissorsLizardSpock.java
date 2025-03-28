package games;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RockPaperScissorsLizardSpock {

    private static Integer getWinner(int userPick, int computerPick){
        int[][] winnerMatrix = {{0,2,1,1,2},{1,0,2,2,1},{2,1,0,1,2},{2,1,2,0,1},{1,2,1,2,0}}; // 0=tie, 1=human player wins, 2=computer wins
        return winnerMatrix[userPick][computerPick];
    }

    private static String getPhraseFromWinner(int winner, String userWord, String computerWord){
        Map<Integer, String> mapWinnerToPhrase = new HashMap<>();
        mapWinnerToPhrase.put(0, "It's a tie!");
        mapWinnerToPhrase.put(1, String.format("You won! %s beats %s", userWord, computerWord));
        mapWinnerToPhrase.put(2, String.format("You lost! %s is beaten by %s", userWord, computerWord));
        return mapWinnerToPhrase.get(winner);
    }

    private static String getWordFromNumber(int number){
        Map<Integer, String> mapNumberToWord = new HashMap<>();
        mapNumberToWord.put(0,"Rock");
        mapNumberToWord.put(1,"Paper");
        mapNumberToWord.put(2,"Scissors");
        mapNumberToWord.put(3,"Lizard");
        mapNumberToWord.put(4,"Spock");
        return mapNumberToWord.get(number);
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Java Rock-Paper-Scissors-Lizard-Spock game!");
        while (true) {
            System.out.println("Please type 1 for rock, 2 for paper, 3 for scissors, 4 for Lizard and 5 for Spock");
            Scanner scanner = new Scanner(System.in);

            Pattern pattern = Pattern.compile("[1-5]");
            String userInput = scanner.nextLine();
            int userPick;
            Matcher matcher = pattern.matcher(userInput);
            if (!matcher.matches()) {
                System.out.println("Please insert an integer between 1 and 5");
                continue;
            } else {
                userPick = Integer.parseInt(userInput) - 1;
            }
            System.out.println("You chose " + getWordFromNumber(userPick));

            Random random = new Random();
            int computerPick = random.nextInt(0, 6);
            System.out.println("Computer chose " + getWordFromNumber(computerPick));

            int winner = getWinner(userPick, computerPick);
            System.out.println(getPhraseFromWinner(winner, getWordFromNumber(userPick), getWordFromNumber(computerPick)));
            System.out.println("Do you wish to play again? y/n");
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
