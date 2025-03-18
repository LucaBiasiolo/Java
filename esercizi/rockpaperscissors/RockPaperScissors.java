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

    private static String getLetterFromInteger(Integer number){
        Map<Integer, String> mapIntegerToWord = new HashMap<>();
        mapIntegerToWord.put(0, "r");
        mapIntegerToWord.put(1, "p");
        mapIntegerToWord.put(2, "s");
        return mapIntegerToWord.get(number);
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

            String computerPick = getLetterFromInteger(randomInt);
            System.out.println("Computer chose " + getWordFromLetter(computerPick));

            if (userPick.equals(computerPick)) {
                System.out.println("It's a tie!");
            } else if (userPick.equals("r")){
                if(computerPick.equals("p")) System.out.println("You lost! Rock is beaten by Paper");
                if(computerPick.equals("s")) System.out.println("You won! Rock beats Scissors");
            } else if (userPick.equals("p")){
                if(computerPick.equals("r")) System.out.println("You won! Paper beats Rock");
                if(computerPick.equals("s")) System.out.println("You lost! Paper is beaten by scissors");
            } else {
                if(computerPick.equals("r")) System.out.println("You lost! Scissors is beaten by Rock");
                if(computerPick.equals("p")) System.out.println("You won! Scissors beats paper");
            }
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
