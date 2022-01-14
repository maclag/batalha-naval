package aplication;

import game.PcBoard;
import game.UserBoard;

import java.util.Scanner;

public class Main {

    public static void main (String[] args) {

        Scanner input = new Scanner(System.in);

        String[] move;
        String row;
        int column;

        // Create both boards
        UserBoard userBoard = new UserBoard(input);
        PcBoard pcBoard = new PcBoard();

        // Print user board
        userBoard.printBoard();
        //pcBoard.printBoard();

        // Starting the game!
        do {
            do {
                move = userBoard.setPositionAttackUser();
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
