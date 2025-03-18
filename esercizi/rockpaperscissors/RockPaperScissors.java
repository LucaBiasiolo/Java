package esercizi.rockpaperscissors;

import java.util.Random;
import java.util.Scanner;

//This is a simple implementation of Rock-Paper-Scissors game in Java
public class RockPaperScissors {

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
            switch (userPick) {
                case "r":
                    System.out.println("You chose Rock");
                    break;
                case "p":
                    System.out.println("You chose Paper");
                    break;
                case "s":
                    System.out.println("You chose Scissors");
                    break;
            }
            Random random = new Random();
            int randomInt = random.nextInt(0, 3);
            String computerPick = "";
            switch (randomInt) {
                case 0:
                    computerPick = "r";
                    System.out.println("Computer chose Rock");
                    break;
                case 1:
                    computerPick = "p";
                    System.out.println("Computer chose Paper");
                    break;
                case 2:
                    computerPick = "s";
                    System.out.println("Computer chose Scissors");
                    break;
            }
            if (userPick.equals(computerPick)) {
                System.out.println("It's a tie!");
            } else if (userPick.equals("r") && computerPick.equals("p")) {
                System.out.println("You lost! Rock is beaten by Paper");
            } else if (userPick.equals("r") && computerPick.equals("s")) {
                System.out.println("You won! Rock beats Scissors");
            } else if (userPick.equals("p") && computerPick.equals("r")) {
                System.out.println("You won! Paper beats Rock");
            } else if (userPick.equals("p") && computerPick.equals("s")) {
                System.out.println("You lost! Paper is beaten by scissors");
            } else if (userPick.equals("s") && computerPick.equals("r")) {
                System.out.println("You lost! Scissors is beaten by Rock");
            } else if (userPick.equals("s") && computerPick.equals("p")) {
                System.out.println("You won! Scissors beats paper");
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
