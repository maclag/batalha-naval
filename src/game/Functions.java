package game;

public interface Functions {
    boolean cantAttack (String row, int column);
    void fillBoardAttack (String row, int column, Board opponentBoard);
    boolean isTheWinner();
}
