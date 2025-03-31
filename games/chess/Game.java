package games.chess;

public class Game {

    private Player player1;
    private Player player2;
    private BoardGame boardGame;

    public void start(){
        setup();
    }

    public void setup(){

    }

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
}
