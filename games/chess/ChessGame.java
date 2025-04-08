package games.chess;

import games.chess.pieces.ChessPiece;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        // For example, you can create buttons for each square and add them to the gridPane
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // Create a button or any other UI component for each square
                // and add it to the gridPane
                Button button = new Button();
                button.setMinSize(100, 100);
                // set background color alternating between beige and brown
                if ((row + col) % 2 == 0) {
                    button.setStyle("-fx-background-color: beige;");
                } else {
                    button.setStyle("-fx-background-color: brown;");
                }
                ChessPiece piece = chessBoard.getPiece(row, col);
                if (piece != null) {
                    button.setText(piece.getIcon());
                    button.setStyle(button.getStyle() + "-fx-font-size: 40px;");
                    button.setStyle(button.getStyle() + " -fx-text-fill: " + (piece.isWhite() ? "white" : "black") + ";");
                }

                gridPane.add(button, col, row);
            }
        }

        Scene scene = new Scene(gridPane, 1200, 800);
        primaryStage.setTitle("Chess Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}