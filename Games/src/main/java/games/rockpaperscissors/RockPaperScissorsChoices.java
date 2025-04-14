package games.rockpaperscissors;

public enum RockPaperScissorsChoices {

    ROCK("Rock",0),PAPER("Paper",1),SCISSORS("Scissors",2);

    private final String description;
    private final int numberOfChoice;

    RockPaperScissorsChoices(String description, int numberOfChoice) {
        this.description = description;
        this.numberOfChoice = numberOfChoice;
    }

    public int getNumberOfChoice() {
        return numberOfChoice;
    }

    public static RockPaperScissorsChoices fromNumber(int number) {
        for (RockPaperScissorsChoices choice : values()) {
            if (choice.getNumberOfChoice() == number) {
                return choice;
            }
        }
        throw new IllegalArgumentException("Invalid choice number: " + number);
    }

    @Override
    public String toString() {
        return description;
    }
}
