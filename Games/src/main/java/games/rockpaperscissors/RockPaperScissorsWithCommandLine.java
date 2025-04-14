package games.rockpaperscissors;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static games.rockpaperscissors.RockPaperScissorsUtil.*;

//This is a simple implementation of Rock-Paper-Scissors game in Java using a command-line approach
public class RockPaperScissorsWithCommandLine {

    public static void main(String[] args) {
        System.out.println("Welcome to Java Rock-Paper-Scissors game!");
        while (true){
            System.out.println("Please type 1 for rock, 2 for paper and 3 for scissors");
            Scanner scanner = new Scanner(System.in);

            Pattern pattern = Pattern.compile("[1-3]");
            String userInput = scanner.nextLine();
            int userChoice;
            Matcher matcher = pattern.matcher(userInput);
            if (!matcher.matches()){
                System.out.println("Please insert an integer between 1 and 3");
                continue;
            } else{
                userChoice = Integer.parseInt(userInput) -1;
            }
            System.out.println("You chose " + RockPaperScissorsChoices.fromNumber(userChoice));

            Random random = new Random();
            int computerChoice = random.nextInt(0, 3);
            System.out.println("Computer chose " + RockPaperScissorsChoices.fromNumber(computerChoice));

            int winner = getWinner(userChoice, computerChoice);
            System.out.println(getPhraseFromWinner(winner, RockPaperScissorsChoices.fromNumber(userChoice), RockPaperScissorsChoices.fromNumber(computerChoice)));
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
