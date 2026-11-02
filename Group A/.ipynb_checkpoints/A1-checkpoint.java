import java.util.Scanner;

class FibonacciAnalysis {

    public static long calculateRecursive(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return calculateRecursive(n - 1) + calculateRecursive(n - 2);
    }

    public static long calculateIterative(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        long prev2 = 0;
        long prev1 = 1;
        long current = 0;

        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return current;
    }

    public static void runTest(int n) {
        System.out.println("----------------------------------------");
        System.out.println("Testing for N = " + n);

        long startTimeIter = System.nanoTime();
        long iterResult = calculateIterative(n);
        long endTimeIter = System.nanoTime();
        long iterTime = endTimeIter - startTimeIter;

        System.out.println("Iterative Result: " + iterResult);
        System.out.println("Iterative Execution Time: " + iterTime + " ns");

        long startTimeRec = System.nanoTime();
        long recResult = calculateRecursive(n);
        long endTimeRec = System.nanoTime();
        long recTime = endTimeRec - startTimeRec;

        System.out.println("Recursive Result: " + recResult);
        System.out.println("Recursive Execution Time: " + recTime + " ns");
        System.out.println("----------------------------------------");
    }

    public static void main(String[] args) {
        System.out.println("=== Static Verification Test ===");
        runTest(8);
        runTest(30);

        System.out.println("\n=== User Input Mode ===");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter value of N: ");
        int userN = scanner.nextInt();

        runTest(userN);

        scanner.close();
    }
}