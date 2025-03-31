import java.util.Arrays;

public class SudokuSolver {

    public static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    for (int number = 1; number <= 9; number++) {
                        if (isValid(board, row, col, number)) {
                            board[row][col] = number;

                            if (solveSudoku(board)) {
                                return true; // Solution found
                            } else {
                                board[row][col] = 0; // Backtrack
                            }
                        }
                    }
                    return false; // No valid number found, backtrack
                }
            }
        }
        return true; // All cells filled, solution found
    }

    public static boolean isValid(int[][] board, int row, int col, int number) {
        // Check row
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == number) {
                return false;
            }
        }

        // Check column
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == number) {
                return false;
            }
        }

        // Check 3x3 box
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (board[i][j] == number) {
                    return false;
                }
            }
        }

        return true; // Number is valid in the given cell
    }

    public static void printBoard(int[][] board) {
        for (int[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void main(String[] args) {
        int[][] board = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        if (solveSudoku(board)) {
            System.out.println("Solution:");
            printBoard(board);
        } else {
            System.out.println("No solution exists.");
        }
    }
}