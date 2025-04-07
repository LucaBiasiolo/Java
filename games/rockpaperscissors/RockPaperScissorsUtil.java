package games.rockpaperscissors;

import java.util.HashMap;
import java.util.Map;

public class RockPaperScissorsUtil {
    public static Integer getWinner(int userPick, int computerPick){
        int[][] winnerMatrix = {{0,2,1},{1,0,2},{2,1,0}}; // 0=tie, 1=human player wins, 2=computer wins
        return winnerMatrix[userPick][computerPick];
    }

    public static String getPhraseFromWinner(int winner, RockPaperScissorsChoices userChoice, RockPaperScissorsChoices computerChoice){
        Map<Integer, String> mapWinnerToPhrase = new HashMap<>();
        mapWinnerToPhrase.put(0, "It's a tie!");
        mapWinnerToPhrase.put(1, String.format("You won! %s beats %s", userChoice, computerChoice));
        mapWinnerToPhrase.put(2, String.format("You lost! %s is beaten by %s", userChoice, computerChoice));
        return mapWinnerToPhrase.get(winner);
    }
}
