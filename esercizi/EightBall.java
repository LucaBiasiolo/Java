package esercizi;

import java.util.Random;
import java.util.Scanner;

// This a simple 8-ball which serves as an oracle to a yes/no question asked by the user
public class EightBall {

    public static void main(String[] args) {
        while(true) {
            System.out.println("Hello! Please write here your yes/no question: ");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine(); // userQuestion is not used, it's just to improve user interaction
            String eightBallAnswer = getEightBallAnswer();
            System.out.println("Oracle answer: " + eightBallAnswer);
            System.out.println("Would you like to ask again? y/n");
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

    private static String getEightBallAnswer() {
        Random random = new Random();
        int randomNumber = random.nextInt(1, 21);
        return switch (randomNumber) {
            case 1 -> "It is certain";
            case 2 -> "It is decidedly so";
            case 3 -> "Without a doubt";
            case 4 -> "Yes definitely";
            case 5 -> "You may rely on it";
            case 6 -> "As I see it, yes";
            case 7 -> "Most likely";
            case 8 -> "Outlook good";
            case 9 -> "Yes";
            case 10 -> "Signs point to yes";
            case 11 -> "Reply hazy, try again";
            case 12 -> "Ask again later";
            case 13 -> "Better not tell you now";
            case 14 -> "Cannot predict now";
            case 15 -> "Concentrate and ask again";
            case 16 -> "Don't count on it";
            case 17 -> "My reply is no";
            case 18 -> "My sources say no";
            case 19 -> "Outlook not so good";
            case 20 -> "Very Doubtful";
            default -> "";
        };
    }
}

