package games.rockpaperscissors;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

import static games.rockpaperscissors.RockPaperScissorsUtil.*;

public class RockPaperScissorsWithJavaFX extends Application {

    private final Label userPickLabel = new Label("");
    private final Label computerPickLabel = new Label("");
    private final Label gameResultLabel = new Label("");

    @Override
    public void start(Stage primaryStage) {
        Label welcomeLabel = new Label("Welcome to Java Rock-Paper-Scissors game!");
        Label instructionLabel = new Label("You will play against the computer.Please choose one of the buttons below.");
        Button rockButton = new Button("Rock");
        Button paperButton = new Button("Paper");
        Button scissorsButton = new Button("Scissors");

        rockButton.setOnAction(e -> playGame(0));
        paperButton.setOnAction(e -> playGame(1));
        scissorsButton.setOnAction(e -> playGame(2));

        HBox buttonsHbox = new HBox(rockButton, paperButton, scissorsButton);
        Label endGameLabel = new Label("Press again your choice to start another game");
        VBox vBox = new VBox(10,welcomeLabel,instructionLabel, buttonsHbox, userPickLabel, computerPickLabel,
                gameResultLabel, endGameLabel);
        Scene scene = new Scene(vBox, 500, 500);

        primaryStage.setTitle("Rock-Paper-Scissors");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void playGame(int userPick) {
        String[] userPickLabels = {"You chose Rock", "You chose Paper", "You chose Scissors"};
        String[] computerPickLabels = {"Computer chose Rock", "Computer chose Paper", "Computer chose Scissors"};
        Random random = new Random();
        int computerPick = random.nextInt(3);

        userPickLabel.setText(userPickLabels[userPick]);

        computerPickLabel.setText(computerPickLabels[computerPick]);

        Integer winner = getWinner(userPick, computerPick);
        String gameResultPhrase = getPhraseFromWinner(winner, getWordFromNumber(userPick), getWordFromNumber(computerPick));

        gameResultLabel.setText(gameResultPhrase);
    }

    public static void main(String[] args) {
        launch(args);
    }
}