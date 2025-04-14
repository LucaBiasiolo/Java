package games.rockpaperscissors;

import org.junit.jupiter.api.Test;

public class RockPaperScissorsTest {

    @Test
    public void testGetWinner(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int winner = RockPaperScissorsUtil.getWinner(i, j);
                System.out.println("Winner between " + RockPaperScissorsChoices.fromNumber(i) + " and " + RockPaperScissorsChoices.fromNumber(j) + " is: " + winner);
            }
        }
    }

    @Test
    public void testGetPhraseFromWinner(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int winner = RockPaperScissorsUtil.getWinner(i, j);
                String phrase = RockPaperScissorsUtil.getPhraseFromWinner(winner, RockPaperScissorsChoices.fromNumber(i), RockPaperScissorsChoices.fromNumber(j));
                System.out.println("Phrase for winner between " + RockPaperScissorsChoices.fromNumber(i) + " and " + RockPaperScissorsChoices.fromNumber(j) + " is: " + phrase);
            }
        }
    }
}
