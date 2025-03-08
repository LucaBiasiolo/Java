package esercizi;

import java.util.Random;
import java.util.Scanner;

//This is a simple program in which you can roll dice specifying the type of die and how many of them
//TODO: manage bad user input
public class DiceRoller {
    public static void main(String[] args) {
        System.out.println("Hello adventurer!");
        while(true) {
            System.out.println("How many dice and of what type do you wish to roll? Use nDm with n number of die and m type of die");
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine().toUpperCase();
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
            String userContinue = scanner.nextLine();
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
