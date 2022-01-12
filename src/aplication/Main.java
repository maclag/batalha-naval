package aplication;

import game.Board;
import game.GameMode;
import game.PcBoard;
import game.UserBoard;

import java.util.Scanner;

import static aplication.Questions.*;

public class Main {

    public static void main (String[] args) {

        Scanner input = new Scanner(System.in);

        String player = setName(input);
        int choice = setMode(input);

        GameMode mode;
        if (choice == 1) {
            mode = GameMode.MANUAL;
            System.out.println("Manual mode on!");
        } else if (choice == 2) {
            mode = GameMode.AUTOMATIC;
            System.out.println("Automatic mode on!");
        }

        // Create boards
        Board userBoard = new UserBoard(player);
        Board pcBoard = new UserBoard("PC");

        // Print user board without placing ships
        userBoard.printBoard();
        pcBoard.printBoard();

        String[] move = setPositionAttack(input);
        String row = move[0];
        int column = Integer.parseInt(move[1]);


        //userBoard.fillBoard();
        userBoard.fillBoard(row, column, pcBoard);

        input.close();
    }



}
