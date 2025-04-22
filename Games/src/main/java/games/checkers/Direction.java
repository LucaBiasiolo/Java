package games.checkers;

public enum Direction {
    UPPER_RIGHT("e"), UPPER_LEFT("q"), LOWER_RIGHT("c"), LOWER_LEFT("z");

    Direction(String keyboardLetter) {
        this.keyboardLetter = keyboardLetter;
    }

    private String keyboardLetter;

    public String getKeyboardLetter() {
        return keyboardLetter;
    }
}
