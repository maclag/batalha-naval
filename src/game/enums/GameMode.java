package game.enums;

public enum GameMode {

    MANUAL("1"),
    AUTOMATIC("2");

    private final String name;

    GameMode (String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
