package game.enums;

public enum Positions {

    SHIP("N"),
    ATTACK("*"),
    WATER("-"),
    SHIP_ON_ATTACK("X"),
    SHIP_ON_WATER("n"),
    EMPTY(" ");

    private final String name;

    Positions (String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
