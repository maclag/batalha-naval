package game;

import java.util.Objects;

public abstract class Board {

    public static final int BOARD_SIZE = 10;
    private static final String LINE = "---------------------------------------------";

    private String username;
    private final String[][] gameBoard = new String[BOARD_SIZE][BOARD_SIZE];

    protected Board (String username) {
        String[] position = placingShips();
        fillBoardWithShips(position);
        this.username = username;
    }

    protected Board() {
        String[] position = placingShips();
        fillBoardWithShips(position);
    }

    public String[][] getBoard() {
        return gameBoard;
    }

    public String[] placingShips() {
        String[] ships = new String[10];
        int total = 0;

        while (total != 10) {
            int linha = (int) (Math.random()*10);
            int coluna = (int) (Math.random()*10);

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

    public void fillBoardWithShips (String[] positions) {
        for (int i = 0; i < 10; i++) {
            int row = positions[i].charAt(0) - 48;
            int column = positions[i].charAt(1) - 48;

            gameBoard[row][column] = String.valueOf(Positions.SHIP);
        }
    }

    public void fillBoard (String position) {
        int row = position.toLowerCase().charAt(0) - 97; // valor inteiro do char 'a' = 97 -> 'a' se torna 0
        int column = position.charAt(1) - 48;

        System.out.println("Linha: " + row);
        System.out.println("Coluna: " + column);

        gameBoard[row][column] = String.valueOf(Positions.SHIP_ON_WATER);

        printBoard();
    }

    public void fillBoard (String row, int column, Board opponentBoard) {
        int linha = row.toLowerCase().charAt(0) - 97;
        String[][] otherBoard = opponentBoard.getBoard();
        String content = otherBoard[linha][column];
        String myBoardContent = gameBoard[linha][column];

        System.out.println("Linha: " + linha);
        System.out.println("Coluna: " + column);

        // corrigir: NullPointerException
//        switch (content) {
//            case "N":
//                myBoardContent = Objects.equals(myBoardContent, "N") ? String.valueOf(Positions.SHIP_ON_ATTACK) : String.valueOf(Positions.ATTACK);
//                break;
//            case " ":
//                myBoardContent = Objects.equals(myBoardContent, "N") ? String.valueOf(Positions.SHIP_ON_WATER) : String.valueOf(Positions.WATER);
//                break;
//            default:
//                System.out.println("Nothing has changed.");
//        }

        printBoard();
    }

    public void printBoard() {
        char counter = 'A';
        final String zeroRow = "|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |";

        System.out.println(LINE);
        System.out.println(addSpace(username));
        System.out.println(LINE);


        System.out.println(zeroRow);
        for (String[] rows : gameBoard) {
            System.out.println(LINE);
            System.out.printf("| %c |", counter);
            for (String cell : rows) {
                System.out.print(cell == null ? "   |" : " " + cell + " |");
            }
            System.out.println();
            counter++;
        }
        System.out.println(LINE);
    }

    private String addSpace (String word) {
        StringBuilder strCentralized = new StringBuilder();
        int lineLength = LINE.length();
        int wordLength = word.length();

        strCentralized.append(" ".repeat(Math.max(0, (lineLength - wordLength) / 2)));
        strCentralized.append(word);

        return strCentralized.toString();
    }
}
