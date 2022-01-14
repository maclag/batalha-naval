package game;

import java.util.InputMismatchException;
import java.util.Scanner;

import static colors.ErrorMsgColors.ANSI_RED;
import static colors.ErrorMsgColors.ANSI_RESET;

public class UserBoard extends Board {

    public UserBoard (Scanner input) {
        super(input);
        setUsername();
        setGameMode();
    }

    private void setUsername () {
        String player;

        do {
            System.out.println("What is your name? ");
            System.out.print("# : ");
            player = this.input.nextLine();
            System.out.println();
        } while (player.length() <= 0);

        this.username = player;
    }

    private void setGameMode () {
        int choice = 0;
        String errorModeMsg = ANSI_RED + "Only type the recommended options: 1 or 2. Please, try again!" + ANSI_RESET;

        do {
            try {
                System.out.println("How do you prefer to position your ships?");
                System.out.println("     1 - MANUAL      2 - AUTOMATIC       ");
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

        this.gameMode = choice;
    }

    public String[] setPositionAttackUser() {
        String[] move;
        int columnByUser;
        String rowByUser;
        String errorPositionMsg = ANSI_RED + "Choose a possible row (A-J) and a column (0-9) without adding spaces. Please, try again!" + ANSI_RESET;

        while (true) {
            try {
                System.out.println("Where do you want to shoot?");
                System.out.println("- Choose a row and a column (RC)");
                System.out.print("# : ");
                move = this.input.nextLine().split("");
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
