package aplication;

import java.util.InputMismatchException;
import java.util.Scanner;

import static colors.ErrorMsgColors.ANSI_RED;
import static colors.ErrorMsgColors.ANSI_RESET;

/* UTILITY CLASS */
public final class Questions {

    private Questions() {
    }

    static String setName (Scanner input) {
        String player;

        do {
            System.out.println("What is your name? ");
            System.out.print("# : ");
            player = input.nextLine();
            System.out.println();
        } while (player.length() <= 0);

        return player;
    }

    static int setMode (Scanner input) {
        int choice = 0;
        String errorModeMsg = ANSI_RED + "Only type the recommended options: 1 or 2. Please, try again!" + ANSI_RESET;

        do {
            try {
                System.out.println("How do you prefer to position your ships?");
                //System.out.printf("%s", String.format("%1$5s", "1 - MANUAL"));
                System.out.printf("%s %s %n", "1 - MANUAL", "2 - AUTOMATIC"); // falta centralizar!
                System.out.print("# : ");
                choice = input.nextInt();
                input.nextLine();

                if (choice != 1 && choice != 2) {
                    System.out.println("\n" + errorModeMsg);
                }
            } catch (InputMismatchException e) {
                System.out.println("\n" + errorModeMsg);
                input.next();
            }
        } while (choice != 1 && choice != 2);

        return choice;
    }

    static String[] setPositionAttack (Scanner input) {
        String[] move;
        int columnByUser;
        String rowByUser;
        String errorPositionMsg = ANSI_RED + "Choose a possible row (A-H) and a column (0-9) without adding spaces. Please, try again!" + ANSI_RESET;

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
