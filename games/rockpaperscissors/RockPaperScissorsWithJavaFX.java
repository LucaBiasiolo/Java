package games.rockpaperscissors;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

import static games.rockpaperscissors.RockPaperScissorsUtil.*;

public class RockPaperScissorsWithJavaFX extends Application {

    private final Label userPickLabel = new Label("");
    private final Label computerPickLabel = new Label("");
    private final Label endGameLabel = new Label("");

    @Override
    public void start(Stage primaryStage) {
        Label welcomeLabel = new Label("Welcome to Java Rock-Paper-Scissors game!");
        Label instructionLabel = new Label("You will play against the computer.Please choose one of the buttons below.");
        VBox vBox = getVBox(welcomeLabel, instructionLabel);
        Scene scene = new Scene(vBox, 500, 500);

        primaryStage.setTitle("Rock-Paper-Scissors");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox getVBox(Label welcomeLabel, Label instructionLabel) {
        Button rockButton = new Button("Rock");
        Button paperButton = new Button("Paper");
        Button scissorsButton = new Button("Scissors");

        rockButton.setOnAction(e -> playGame(0));
        paperButton.setOnAction(e -> playGame(1));
        scissorsButton.setOnAction(e -> playGame(2));

        HBox buttonsHbox = new HBox(rockButton, paperButton, scissorsButton);

        return  new VBox(10, welcomeLabel, instructionLabel, buttonsHbox, userPickLabel, computerPickLabel,
                endGameLabel);
    }

    private void playGame(int userPick) {
        // reset in case of more games in a row
        computerPickLabel.setText("");
        endGameLabel.setText("");

        String[] userPickLabels = {"You chose Rock", "You chose Paper", "You chose Scissors"};
        String[] computerPickLabels = {"Computer chose Rock", "Computer chose Paper", "Computer chose Scissors"};
        Random random = new Random();
        int computerPick = random.nextInt(3);

        userPickLabel.setText(userPickLabels[userPick]);

        // add pauses to fake calculations and improve user experience
        PauseTransition pause  = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(_ -> {
            computerPickLabel.setText(computerPickLabels[computerPick]);
        });
        pause.play();

        Integer winner = getWinner(userPick, computerPick);
        PauseTransition pause2 = new PauseTransition(Duration.seconds(2));
        pause2.setOnFinished(_ ->{
            String endGamePhrase = getPhraseFromWinner(winner, getWordFromNumber(userPick), getWordFromNumber(computerPick));
            endGamePhrase +="\nPress again your choice to start another game";
            endGameLabel.setText(endGamePhrase);
        });
        pause2.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}