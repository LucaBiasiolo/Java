package games;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//This is a simple program in which you can roll dice specifying the type of die and how many of them
public class DiceRoller {
    public static void main(String[] args) {
        System.out.println("Hello adventurer!");
        while(true) {
            System.out.println("How many dice and what type of dice do you wish to roll? Use nDm with n number of die and m type of die");
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine().toUpperCase();
            Pattern pattern = Pattern.compile("\\d+D\\d+"); // (one or more digit)D(one or more digit)
            Matcher matcher = pattern.matcher(userInput);
            if(!matcher.matches()) {
                System.out.println("Invalid input. Please use nDm, where n is the number of dice you want to throw and m is the type of die");
                continue;
            }
            String[] userInputSplit = userInput.split("D");
            int numberOfDice = Integer.parseInt(userInputSplit[0]);
            int typeOfDie = Integer.parseInt(userInputSplit[1]);

            Random random = new Random();
            int diceResult = 0;
            for (int i = 0; i < numberOfDice; i++) {
                diceResult += random.nextInt(1, typeOfDie + 1);
            }

            System.out.println("Result: " + diceResult);
            System.out.println("Would you like to make another throw? y/n");
            String userContinue = scanner.nextLine().toLowerCase();
            while (!userContinue.equals("n") && !userContinue.equals("y")){
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
