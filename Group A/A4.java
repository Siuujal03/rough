import java.util.Scanner;

class NQueensBacktracking {

    public static boolean isSafe(int[][] board, int row, int col, int N) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }

        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        for (int i = row, j = col; i >= 0 && j < N; i--, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    public static boolean solveNQueens(int[][] board, int row, int N) {
        if (row >= N) {
            return true;
        }

        if (rowHasQueen(board, row, N)) {
            return solveNQueens(board, row + 1, N);
        }

        for (int col = 0; col < N; col++) {
            if (isSafe(board, row, col, N)) {
                board[row][col] = 1;

                if (solveNQueens(board, row + 1, N)) {
                    return true;
                }

                board[row][col] = 0;
            }
        }

        return false;
    }

    public static boolean rowHasQueen(int[][] board, int row, int N) {
        for (int col = 0; col < N; col++) {
            if (board[row][col] == 1) {
                return true;
            }
        }
        return false;
    }

    public static void printBoard(int[][] board, int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void runTest(int N, int firstRow, int firstCol) {
        System.out.println("----------------------------------------");
        System.out.println("Grid Size: " + N + "x" + N);
        System.out.println("First Queen placed at Row: " + firstRow + ", Col: " + firstCol);

        int[][] board = new int[N][N];
        board[firstRow][firstCol] = 1;

        if (solveNQueens(board, 0, N)) {
            System.out.println("\nFinal Board Configuration:");
            printBoard(board, N);
        } else {
            System.out.println("No solution exists for the given first queen placement.");
        }
        System.out.println("----------------------------------------");
    }

    public static void main(String[] args) {
        System.out.println("=== Static Verification Test ===");
        runTest(4, 0, 1);

        System.out.println("\n=== User Input Mode ===");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter size of matrix (N): ");
        int N = scanner.nextInt();

        System.out.print("Enter row index for first Queen (0 to " + (N - 1) + "): ");
        int firstRow = scanner.nextInt();

        System.out.print("Enter column index for first Queen (0 to " + (N - 1) + "): ");
        int firstCol = scanner.nextInt();

        runTest(N, firstRow, firstCol);

        scanner.close();
    }
}