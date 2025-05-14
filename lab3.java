import java.util.Arrays;
import java.util.Random;

public class lab3{

    static class Stats {
        int comparisons = 0;
        int swaps = 0;
    }

    // Iterative Insertion Sort
    public static void iterativeInsertionSort(int[] arr, Stats stats) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0) {
                stats.comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    stats.swaps++;
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
    }

    // Recursive Insertion Sort
    public static void recursiveInsertionSort(int[] arr, int n, Stats stats) {
        if (n <= 1) return;

        recursiveInsertionSort(arr, n - 1, stats);
        int last = arr[n - 1];
        int j = n - 2;

        while (j >= 0) {
            stats.comparisons++;
            if (arr[j] > last) {
                arr[j + 1] = arr[j];
                stats.swaps++;
                j--;
            } else {
                break;
            }
        }
        arr[j + 1] = last;
    }

    // Utility method to time and run sorts
    public static void runSort(String label, int[] data) {
        int[] copy1 = Arrays.copyOf(data, data.length);
        int[] copy2 = Arrays.copyOf(data, data.length);

        Stats statsIter = new Stats();
        Stats statsRec = new Stats();

        long startIter = System.nanoTime();
        iterativeInsertionSort(copy1, statsIter);
        long endIter = System.nanoTime();

        long startRec = System.nanoTime();
        recursiveInsertionSort(copy2, copy2.length, statsRec);
        long endRec = System.nanoTime();

        System.out.printf("%-25s | %-10.6f | %-12d | %-10d || %-10.6f | %-12d | %-10d%n",
                label,
                (endIter - startIter) / 1e6, statsIter.comparisons, statsIter.swaps,
                (endRec - startRec) / 1e6, statsRec.comparisons, statsRec.swaps);
    }

    public static void main(String[] args) {
        int[] alreadySorted = new int[25];
        for (int i = 0; i < 25; i++) alreadySorted[i] = i + 1;

        int[] reverseSorted = new int[25];
        for (int i = 0; i < 25; i++) reverseSorted[i] = 25 - i;

        int[] randomOrder = {16, 1, 4, 2, 12, 9, 10, 3, 5, 24, 14, 20, 6, 23, 7,
                             25, 19, 18, 8, 22, 11, 17, 13, 15, 21};

        int[] nearlySorted = {24, 25, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                              16, 17, 18, 19, 20, 21, 22, 23, 1, 2};

        int[] singleElementEnd = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
                                  17, 18, 19, 20, 21, 22, 23, 24, 25, 1};

        int[] largeInput = new Random().ints(1000, 1, 10000).toArray();

        System.out.printf("%-25s | %-10s | %-12s | %-10s || %-10s | %-12s | %-10s%n",
                "Input Type", "Iter Time", "Iter Compare", "Iter Swap",
                "Rec Time", "Rec Compare", "Rec Swap");
        System.out.println("---------------------------------------------------------------------------------------------");

        runSort("Already Sorted", alreadySorted);
        runSort("Reverse Sorted", reverseSorted);
        runSort("Random Order", randomOrder);
        runSort("Nearly Sorted", nearlySorted);
        runSort("Single Element at End", singleElementEnd);
        runSort("Large Random Input", largeInput);
    }
}
