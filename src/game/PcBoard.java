package game;

import java.util.Random;

public class PcBoard extends Board {

    public PcBoard() {
        username = "PC";
    }

    protected String setPositionAttackPC() {
        Random random = new Random();

        char letter;
        String row;
        int column;

        do {
            letter = (char)(random.nextInt(10) + 'a');
            row = Character.toString(letter);
            column = random.nextInt(10);
        } while (cantAttack(row, column));

        return row + column;
    }

    public void fillBoardAttack (Board opponentBoard) {
        String position = setPositionAttackPC();
        String row = Character.toString(position.charAt(0));
        int column = position.charAt(1) - 48;
        fillBoardAttack(row, column, opponentBoard);
    }

}
