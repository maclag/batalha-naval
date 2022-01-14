package game;

import java.util.Scanner;

import static colors.ErrorMsgColors.ANSI_RED;
import static colors.ErrorMsgColors.ANSI_RESET;

public class UserBoard extends Board {

    public UserBoard (String username, int gameMode, Scanner input) {
        super(username, gameMode, input);
    }

    public String[] setPositionAttackUser (Scanner input) {
        String[] move;
        int columnByUser;
        String rowByUser;
        String errorPositionMsg = ANSI_RED + "Choose a possible row (A-J) and a column (0-9) without adding spaces. Please, try again!" + ANSI_RESET;

        while (true) {
            try {
                System.out.println("Where do you want to shoot?");
                System.out.println("- Choose a row and a column (RC)");
                System.out.print("# : ");
                move = input.nextLine().split("");
                rowByUser = move[0];
                columnByUser = Integer.parseInt(move[1]);
                if (rowByUser.matches("[a-jA-J]") && String.valueOf(columnByUser).matches("[0-9]") && move.length == 2) {
                    break;
                } else {
                    System.out.println("\n" + errorPositionMsg);
                }
            } catch (Exception e) {
                System.out.println("\n" + errorPositionMsg);
            }
        }

        return move;
    }

}
