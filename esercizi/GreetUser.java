package esercizi;

import java.util.Scanner;

public class GreetUser {
    public static void main(String[] args) {

        System.out.println("Hello, i'm Java. What is your name?");

        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();
        System.out.println(String.format("Hello %s!",userName));

        scanner.close();
    }
}
