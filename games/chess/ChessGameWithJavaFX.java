package games.chess;

import games.chess.pieces.ChessPiece;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 "the game of chess is like a sword fight: you must think first... Before you move!"
 *
 */
public class ChessGameWithJavaFX extends Application {
    private ChessBoard chessBoard;
    private Player player1;
    private Player player2;
    private ChessPiece selectedPiece;

    @Override
    public void start(Stage primaryStage) {
        chessBoard = new ChessBoard();
        GridPane gridPane = new GridPane();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Button button = new Button();
                button.setMinSize(100, 100);
                if ((row + col) % 2 == 0) {
                    button.setStyle("-fx-background-color: beige;");
                } else {
                    button.setStyle("-fx-background-color: brown;");
                }
                ChessPiece piece = chessBoard.getPiece(row, col);
                if (piece != null) {
                    button.setText(piece.getIcon());
                    button.setStyle(button.getStyle() + "-fx-font-size: 40px; -fx-font-weight: bold;");
                    button.setStyle(button.getStyle() + " -fx-text-fill: " + (piece.isWhite() ? "white" : "black") + ";");
                    button.setOnMouseClicked(event ->{
                        handlePieceClick(event,piece);
                    });
                } else{
                    int finalRow = row;
                    int finalCol = col;
                    button.setOnMouseClicked(event -> handleSquareClick(event, finalRow, finalCol));
                }

                gridPane.add(button, col, row);
            }
        }

        Scene scene = new Scene(gridPane, 1200, 800);
        primaryStage.setTitle("Chess Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleSquareClick(MouseEvent click, int row, int column){
        click.consume();
        System.out.println("Square clicked");
    }

    private void handlePieceClick(MouseEvent click, ChessPiece piece){
        click.consume();
        System.out.println("Button clicked");
        // todo: show possible moves
        selectedPiece = piece;
        System.out.println("Selected piece: " + piece);
    }

    public static void main(String[] args) {
        launch(args);
    }
}