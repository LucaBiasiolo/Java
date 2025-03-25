package games.rockpaperscissors;

import java.util.HashMap;
import java.util.Map;

public class RockPaperScissorsUtil {
    public static Integer getWinner(int userPick, int computerPick){
        int[][] winnerMatrix = {{0,2,1},{1,0,2},{2,1,0}}; // 0=tie, 1=human player wins, 2=computer wins
        return winnerMatrix[userPick][computerPick];
    }

    public static String getPhraseFromWinner(int winner, String userWord, String computerWord){
        Map<Integer, String> mapWinnerToPhrase = new HashMap<>();
        mapWinnerToPhrase.put(0, "It's a tie!");
        mapWinnerToPhrase.put(1, String.format("You won! %s beats %s", userWord, computerWord));
        mapWinnerToPhrase.put(2, String.format("You lost! %s is beaten by %s", userWord, computerWord));
        return mapWinnerToPhrase.get(winner);
    }

    public static String getWordFromNumber(int number){
        Map<Integer, String> mapNumberToWord = new HashMap<>();
        mapNumberToWord.put(0,"Rock");
        mapNumberToWord.put(1,"Paper");
        mapNumberToWord.put(2,"Scissors");
        return mapNumberToWord.get(number);
    }
}
