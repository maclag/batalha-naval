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

        return choice;
    }
}
