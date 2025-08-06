public class TicTacToeBoard {
    private char[][] board;
    private final int size = 3;

    public TicTacToeBoard() {
        board = new char[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                board[i][j] = ' ';
    }

    public boolean placeMark(int row, int col, char player) {
        if (row < 0 || row >= size || col < 0 || col >= size) return false;
        if (board[row][col] != ' ') return false;
        board[row][col] = player;
        return true;
    }

    public boolean checkWin(char player) {
        for (int i = 0; i < size; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true;
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true;
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true;
        return false;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (board[i][j] == ' ') return false;
        return true;
    }

    public boolean isGameOver() {
        return checkWin('X') || checkWin('O') || isBoardFull();
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            System.out.print("|");
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TicTacToeBoard game = new TicTacToeBoard();
        game.placeMark(0, 0, 'X');
        game.placeMark(0, 1, 'O');
        game.placeMark(1, 1, 'X');
        game.placeMark(2, 2, 'O');
        game.placeMark(2, 0, 'X');
        game.printBoard();
        System.out.println("X 獲勝: " + game.checkWin('X'));
        System.out.println("O 獲勝: " + game.checkWin('O'));
        System.out.println("遊戲結束: " + game.isGameOver());
    }
}
