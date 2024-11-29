package cis415_nqueen;

public class CIS415_NQueen {

    public static boolean isSafe(int[][] board, int row, int col) {
        //Checking row
        for (int c = 0; c < board[row].length; c++) {
            if (board[row][c] == 1) {
                return false;
            }
        }
        //Check col
        for (int r = 0; r < board.length; r++) {
            if (board[r][col] == 1) {
                return false;
            }
        }

        //Check / up
        for (int c = col, r = row; r >= 0 && c < board.length; r--, c++) {
            if (board[r][c] == 1) {
                return false;
            }
        }
        //check / down
        for (int c = col, r = row; r < board.length && c >= 0; r++, c--) {
            if (board[r][c] == 1) {
                return false;
            }
        }
        //check \ up
        for (int c = col, r = row; r >= 0 && c >= 0; r--, c--) {
            if (board[r][c] == 1) {
                return false;
            }
        }
        //check \ down
        for (int c = col, r = row; r < board.length && c < board.length; r++, c++) {
            if (board[r][c] == 1) {
                return false;
            }
        }

        return true;
    }

    public static void clearBoard(int[][] board) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] != 0) {
                    board[r][c] = 0;
                }
            }
        }
    }

    public static void printBoard(int[][] arr) {
        for (int[] r : arr) {
            for (int c = 0; c < r.length; c++) {
                System.out.print(r[c] + "\t");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static void solver(int[][] board) {
        int n = board.length;
        int count = 0;
        while (count < n) {
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    boolean isValidSpot = isSafe(board, r, c);
                    if (isValidSpot) {
                        board[r][c] = 1;
                        count++;
                    }
                }
            }
        }

        if (count == n) {
            printBoard(board);
        }
    }

    public static void solver(int[][] board, int row, int col) {
        int n = board.length;
        int trail = 0;
        board[row][col] = 1;
        int count = 1;
        boolean isValidSpot = false;
        boolean isSolved = false;
        while (!isSolved && trail < n) {
            int tempRow = row == 0 ? row + 1 : 0;

            while (!isValidSpot) {
                isValidSpot = isSafe(board, tempRow, trail);
                if (!isValidSpot) {
                    trail++;
                }
            }
            board[tempRow][trail] = 1;
            count++;

            for (int r = tempRow + 1; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    isValidSpot = isSafe(board, r, c);
                    if (isValidSpot) {
                        board[r][c] = 1;
                        count++;
                        break;
                    }
                }
            }

            if (count < n) {
                clearBoard(board);
                board[row][col] = 1;
                count = 1;
                trail++;
                if (trail >= n) {
                    System.out.println("No solution found!");
                    break;
                }
            } else {
                isSolved = true;
                printBoard(board);
            }
        }
    }

    public static void main(String[] args) {
        int[][] board = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        System.out.println("Solved with an empty board: ");
        solver(board);
        clearBoard(board);
        System.out.println("Solved using the specified starting position:  ");
        solver(board, 2, 4);
    }
}




