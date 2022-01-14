package aplication;

import game.PcBoard;
import game.UserBoard;

import java.util.Scanner;

import static aplication.Questions.*;

public class Main {

    public static void main (String[] args) {

        Scanner input = new Scanner(System.in);

        String[] move;
        String row;
        int column;

        String player = setName(input);
        int mode = setMode(input);

        // Create both boards
        UserBoard userBoard = new UserBoard(player, mode, input);
        PcBoard pcBoard = new PcBoard();

        // Print user board
        userBoard.printBoard();
        //pcBoard.printBoard();

        // Starting the game!
        do {
            do {
                move = userBoard.setPositionAttackUser(input);
                row = move[0];
                column = Integer.parseInt(move[1]);
            } while (userBoard.cantAttack(row, column));
            userBoard.fillBoardAttack(row, column, pcBoard);
            pcBoard.fillBoardAttack(userBoard);
            userBoard.printBoard();
        } while (!userBoard.isTheWinner() && !pcBoard.isTheWinner());

        System.out.println();
        if (userBoard.isTheWinner()) {
            System.out.println("Congratulations! You won the game!!!");
        } else {
            System.out.println("You lost... try again :(");
        }

        userBoard.printBoard();
        pcBoard.printBoard();

        input.close();
    }
}
