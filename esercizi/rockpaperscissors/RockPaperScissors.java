package esercizi.rockpaperscissors;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

//This is a simple implementation of Rock-Paper-Scissors game in Java
public class RockPaperScissors {

    private static String getWordFromLetter(String letter){
        Map<String, String> mapLetterToWord = new HashMap<>(3);
        mapLetterToWord.put("r", "Rock");
        mapLetterToWord.put("p", "Paper");
        mapLetterToWord.put("s", "Scissors");
        return mapLetterToWord.get(letter);
    }

    private static String getLetterFromNumber(Integer number){
        Map<Integer, String> mapIntegerToWord = new HashMap<>();
        mapIntegerToWord.put(0, "r");
        mapIntegerToWord.put(1, "p");
        mapIntegerToWord.put(2, "s");
        return mapIntegerToWord.get(number);
    }

    private static Integer getNumberFromLetter(String letter){
        Map<String, Integer> mapLetterToNumber = new HashMap<>();
        mapLetterToNumber.put("r",0);
        mapLetterToNumber.put("p",1);
        mapLetterToNumber.put("s",2);
        return mapLetterToNumber.get(letter);
    }

    private static Integer getWinner(String userPick, String computerPick){
        int[][] winnerMatrix = {{0,2,1},{1,0,2},{2,1,0}}; // 0=tie, 1=human player wins, 2=computer wins
        return winnerMatrix[getNumberFromLetter(userPick)][getNumberFromLetter(computerPick)];
    }

    private static String getPhraseFromWinner(int winner, String userWord, String computerWord){
        Map<Integer, String> mapWinnerToPhrase = new HashMap<>();
        mapWinnerToPhrase.put(0, "It's a tie!");
        mapWinnerToPhrase.put(1, String.format("You won! %s beats %s", userWord, computerWord));
        mapWinnerToPhrase.put(2, String.format("You lost! %s is beaten by %s", userWord, computerWord));
        return mapWinnerToPhrase.get(winner);
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Java Rock-Paper-Scissors game!");
        while (true){
            System.out.println("Please type r for rock, p for paper and s for scissors");
            Scanner scanner = new Scanner(System.in);
            String userPick = scanner.nextLine().toLowerCase();
            while (!userPick.equals("r") && !userPick.equals("p") && !userPick.equals("s")){
                System.out.println("Please insert r, p or s");
                userPick = scanner.nextLine();
            }
            System.out.println("You chose " + getWordFromLetter(userPick));

            Random random = new Random();
            int randomInt = random.nextInt(0, 3);

            String computerPick = getLetterFromNumber(randomInt);
            System.out.println("Computer chose " + getWordFromLetter(computerPick));

            int winner = getWinner(userPick, computerPick);
            System.out.println(getPhraseFromWinner(winner, getWordFromLetter(userPick), getWordFromLetter(computerPick)));
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
