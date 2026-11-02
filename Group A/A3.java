import java.util.Arrays;
import java.util.Scanner;

 class KnapsackDP {

    public static int knapsackMemoization(int W, int[] wt, int[] val, int n, int[][] dp) {
        if (n == 0 || W == 0) {
            return 0;
        }

        if (dp[n][W] != -1) {
            return dp[n][W];
        }

        if (wt[n - 1] <= W) {
            dp[n][W] = Math.max(
                val[n - 1] + knapsackMemoization(W - wt[n - 1], wt, val, n - 1, dp),
                knapsackMemoization(W, wt, val, n - 1, dp)
            );
        } else {
            dp[n][W] = knapsackMemoization(W, wt, val, n - 1, dp);
        }

        return dp[n][W];
    }

    public static int knapsackTabulation(int W, int[] wt, int[] val, int n) {
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (wt[i - 1] <= w) {
                    dp[i][w] = Math.max(val[i - 1] + dp[i - 1][w - wt[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        System.out.println("\nTabulation DP Table:");
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                System.out.printf("%4d", dp[i][w]);
            }
            System.out.println();
        }

        return dp[n][W];
    }

    public static void runTest(int W, int[] wt, int[] val, int n) {
        System.out.println("----------------------------------------");
        System.out.println("Knapsack Capacity: " + W);
        System.out.println("Weights: " + Arrays.toString(wt));
        System.out.println("Values:  " + Arrays.toString(val));

        int[][] dp = new int[n + 1][W + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int maxProfitMemo = knapsackMemoization(W, wt, val, n, dp);
        System.out.println("\nMaximum Profit (Memoization): " + maxProfitMemo);

        int maxProfitTab = knapsackTabulation(W, wt, val, n);
        System.out.println("\nMaximum Profit (Tabulation): " + maxProfitTab);
        System.out.println("----------------------------------------");
    }

    public static void main(String[] args) {
        System.out.println("=== Static Verification Test ===");
        int[] staticVal = {3, 4, 5, 6};
        int[] staticWt = {2, 3, 4, 5};
        int staticW = 5;
        int staticN = staticVal.length;

        runTest(staticW, staticWt, staticVal, staticN);

        System.out.println("\n=== User Input Mode ===");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int userN = scanner.nextInt();

        int[] userVal = new int[userN];
        int[] userWt = new int[userN];

        for (int i = 0; i < userN; i++) {
            System.out.print("Enter value for item " + (i + 1) + ": ");
            userVal[i] = scanner.nextInt();
            System.out.print("Enter weight for item " + (i + 1) + ": ");
            userWt[i] = scanner.nextInt();
        }

        System.out.print("Enter capacity of knapsack: ");
        int userW = scanner.nextInt();

        runTest(userW, userWt, userVal, userN);

        scanner.close();
    }
}