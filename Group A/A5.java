import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class QuickSortAnalysis {

    static int deterministicPartition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    static void deterministicQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = deterministicPartition(arr, low, high);
            deterministicQuickSort(arr, low, pi - 1);
            deterministicQuickSort(arr, pi + 1, high);
        }
    }

    static int randomizedPartition(int[] arr, int low, int high) {
        Random rand = new Random();
        int randomIndex = low + rand.nextInt(high - low + 1);

        int temp = arr[randomIndex];
        arr[randomIndex] = arr[high];
        arr[high] = temp;

        return deterministicPartition(arr, low, high);
    }

    static void randomizedQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = randomizedPartition(arr, low, high);
            randomizedQuickSort(arr, low, pi - 1);
            randomizedQuickSort(arr, pi + 1, high);
        }
    }

    static void runTest(int[] inputArr) {
        System.out.println("----------------------------------------");
        System.out.println("Original Array: " + Arrays.toString(inputArr));

        int[] detArr = inputArr.clone();
        long startDet = System.nanoTime();
        deterministicQuickSort(detArr, 0, detArr.length - 1);
        long endDet = System.nanoTime();

        System.out.println("\nDeterministic Quick Sort Result: " + Arrays.toString(detArr));
        System.out.println("Deterministic Execution Time: " + (endDet - startDet) + " ns");

        int[] randArr = inputArr.clone();
        long startRand = System.nanoTime();
        randomizedQuickSort(randArr, 0, randArr.length - 1);
        long endRand = System.nanoTime();

        System.out.println("\nRandomized Quick Sort Result:    " + Arrays.toString(randArr));
        System.out.println("Randomized Execution Time:    " + (endRand - startRand) + " ns");
        System.out.println("----------------------------------------");
    }

    public static void main(String[] args) {
        System.out.println("=== Static Verification Test ===");
        int[] staticArr = {10, 7, 8, 9, 1, 5, 2, 4, 3, 6};
        runTest(staticArr);

        System.out.println("\n=== User Input Mode ===");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter size of array: ");
        int n = scanner.nextInt();

        int[] userArr = new int[n];
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            userArr[i] = scanner.nextInt();
        }

        runTest(userArr);

        scanner.close();
    }
}