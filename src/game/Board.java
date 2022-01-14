package game;

import game.enums.GameMode;
import game.enums.Positions;

import java.util.Objects;
import java.util.Scanner;

import static colors.ErrorMsgColors.ANSI_RED;
import static colors.ErrorMsgColors.ANSI_RESET;

public abstract class Board implements Functions {

    protected static final int BOARD_SIZE = 10;
    private static final String LINE = "---------------------------------------------";

    protected Scanner input;
    protected int gameMode;
    protected String username;
    protected final String[][] gameBoard = new String[BOARD_SIZE][BOARD_SIZE];

    protected Board (Scanner input) {
        this.input = input;
        fillBoardWithSpaces();
        fillBoardWithShips(this.gameMode);
    }
    protected Board() {
        fillBoardWithSpaces();
        fillBoardWithShips(2);
    }

    protected String[][] getBoard() {
        return gameBoard;
    }

    private void fillBoardWithSpaces() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gameBoard[i][j] = String.valueOf(Positions.EMPTY);
            }
        }
    }

    public void fillBoardWithShips (int mode) {
        String[] positions = String.valueOf(mode).equals(String.valueOf(GameMode.MANUAL)) ? placingShipsManual() : placingShipsAutomatic();

        for (int i = 0; i < 10; i++) {
            int row = positions[i].charAt(0) - 48;
            int column = positions[i].charAt(1) - 48;

            gameBoard[row][column] = String.valueOf(Positions.SHIP);
        }
    }

    private String[] placingShipsManual() {
        String[] ships = new String[10];
        String[] move;
        int row;
        int columnByUser;
        String rowByUser;
        String errorPositionMsg = ANSI_RED + "Choose a possible row (A-J) and a column (0-9) without adding spaces. Please, try again!" + ANSI_RESET;

        int counter = 0;
        while (counter != 10) {
            try {
                System.out.println("\n" + "Where do you want to place the ships? Choose 10 positions");
                System.out.printf("- Choose a row and a column (RC) (%d)%n", counter);
                System.out.print("# : ");
                move = input.nextLine().split("");
                rowByUser = move[0];
                columnByUser = Integer.parseInt(move[1]);
                if (rowByUser.matches("[a-jA-J]") && String.valueOf(columnByUser).matches("[0-9]") && move.length == 2) {
                    row = rowByUser.toLowerCase().charAt(0) - 97;
                    ships[counter] = row + String.valueOf(columnByUser);
                    counter++;
                } else {
                    System.out.println("\n" + errorPositionMsg);
                }
            } catch (Exception e) {
                System.out.println("\n" + errorPositionMsg);
            }
        }

        return ships;
    }

    private String[] placingShipsAutomatic() {
        String[] ships = new String[10];
        int total = 0;

        while (total != 10) {
            int linha = (int) (Math.random() * 10);
            int coluna = (int) (Math.random() * 10);

            boolean isPlaced = false;
            String positionStr = Integer.toString(linha) + coluna;

            for (String ship : ships) {
                if (Objects.equals(ship, positionStr)) {
                    isPlaced = true;
                    break;
                }
            }

            if (!isPlaced) {
                ships[total] = positionStr;
                total++;
            }
        }

        return ships;
    }

    @Override
    public boolean cantAttack (String row, int column) {
        int linha = row.toLowerCase().charAt(0) - 97;
        return !Objects.equals(gameBoard[linha][column], " ")
                && !Objects.equals(gameBoard[linha][column], "N");
    }

    @Override
    public void fillBoardAttack (String row, int column, Board opponentBoard) {
        int linha = row.toLowerCase().charAt(0) - 97;
        String[][] otherBoard = opponentBoard.getBoard();
        String content = otherBoard[linha][column];
        String myBoardContent = gameBoard[linha][column];

        switch (content) {
            case "N":
                gameBoard[linha][column] = (Objects.equals(myBoardContent, "N")) ? String.valueOf(Positions.SHIP_ON_ATTACK) : String.valueOf(Positions.ATTACK);
                otherBoard[linha][column] = String.valueOf(Positions.EMPTY);
                break;
            case "*":
            case "-":
            case " ":
                gameBoard[linha][column] = (Objects.equals(myBoardContent, "N")) ? String.valueOf(Positions.SHIP_ON_WATER) : String.valueOf(Positions.WATER);
                break;
            case "X":
            case "n":
                gameBoard[linha][column] = (Objects.equals(myBoardContent, "N")) ? String.valueOf(Positions.SHIP_ON_ATTACK) : String.valueOf(Positions.ATTACK);
                otherBoard[linha][column] = String.valueOf(Positions.WATER);
                break;
            default:
                System.out.println("Something went wrong...");
        }

        //printBoard();
    }

    @Override
    public boolean isTheWinner() {
        int points = 0;

        for (String[] rows : gameBoard) {
            for (String element : rows) {
                if (Objects.equals(element, String.valueOf(Positions.ATTACK))
                        || Objects.equals(element, String.valueOf(Positions.SHIP_ON_ATTACK))) {
                    points++;
                }
            }
        }

        return points == 10;
    }

    public void printBoard() {
        char counter = 'A';
        final String zeroRow = "|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |";

        System.out.println("\n" + LINE);
        System.out.println(addSpace(username));
        System.out.println(LINE);

        System.out.println(zeroRow);
        for (String[] rows : gameBoard) {
            System.out.println(LINE);
            System.out.printf("| %c |", counter);
            for (String cell : rows) {
                System.out.printf(" %s |", cell);
            }
            System.out.println();
            counter++;
        }
        System.out.println(LINE + "\n");
    }

    private String addSpace(String word) {
        StringBuilder strCentralized = new StringBuilder();
        int lineLength = LINE.length();
        int wordLength = word.length();

        strCentralized.append(" ".repeat(Math.max(0, (lineLength - wordLength) / 2)));
        strCentralized.append(word);

        return strCentralized.toString();
    }
}
