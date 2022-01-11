package game;

public enum Positions {

    // alterar
    ATTACKED("X"),
    SHIP("N"),
    EMPTY(" "),
    SHIP_ON_ATTACK("*"),
    SHIP_ON_WATER("A");

    private final String name;

    Positions (String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
