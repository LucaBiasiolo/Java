package games.chess;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ChessGame extends Application {
    private ChessBoard chessBoard;
    private Player player1;
    private Player player2;

    @Override
    public void start(Stage primaryStage) {
        chessBoard = new ChessBoard();
        GridPane gridPane = new GridPane();

        // Add code to initialize the GUI with chess pieces

        Scene scene = new Scene(gridPane, 800, 800);
        primaryStage.setTitle("Chess Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}