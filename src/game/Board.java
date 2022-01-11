package game;

public abstract class Board {

    public static final int BOARD_SIZE = 10;
    private static final String LINE = "---------------------------------------------";

    private String username;
    private final String[][] gameBoard = new String[BOARD_SIZE][BOARD_SIZE];

    public Board (String username) {
        this.username = username;
    }

    public Board() {
        fillBoard();
    }

    public void fillBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                gameBoard[i][j] = "-";
            }
        }
        //printBoard();
    }

    public void fillBoard (String position) {
        int row = position.toLowerCase().charAt(0) - 97; // valor inteiro do char 'a' = 97 -> 'a' se torna 0
        int column = position.charAt(1) - 48;

        System.out.println("Linha: " + row);
        System.out.println("Coluna: " + column);

        gameBoard[row][column] = "A";

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
            System.out.println("");
            counter++;
        }
        System.out.println(LINE);
    }

    private String addSpace (String word) {
        StringBuilder strCentralized = new StringBuilder();
        int lineLength = LINE.length();
        int wordLength = word.length();

        for (int i = 0; i < (lineLength - wordLength) / 2; i++) {
            strCentralized.append(" ");
        }
        strCentralized.append(word);

        return strCentralized.toString();
    }
}
