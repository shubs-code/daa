// Selection sort algorithm

import java.util.Arrays;
import java.util.Random;

public class lab2{

    static class Stats {
        int comparisons = 0;
        int swaps = 0;
    }

    // Iterative Selection Sort
    public static void iterativeSelectionSort(int[] arr, Stats stats) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                stats.comparisons++;
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
                stats.swaps++;
            }
        }
    }

    // Recursive Selection Sort
    public static void recursiveSelectionSort(int[] arr, int start, Stats stats) {
        int n = arr.length;
        if (start >= n - 1) return;

        int minIdx = start;
        for (int i = start + 1; i < n; i++) {
            stats.comparisons++;
            if (arr[i] < arr[minIdx]) {
                minIdx = i;
            }
        }

        if (minIdx != start) {
            int temp = arr[start];
            arr[start] = arr[minIdx];
            arr[minIdx] = temp;
            stats.swaps++;
        }

        recursiveSelectionSort(arr, start + 1, stats);
    }

    // Utility method to run and time a sort
    public static void runSort(String label, int[] data) {
        int[] copy1 = Arrays.copyOf(data, data.length);
        int[] copy2 = Arrays.copyOf(data, data.length);

        Stats statsIter = new Stats();
        Stats statsRec = new Stats();

        long startIter = System.nanoTime();
        iterativeSelectionSort(copy1, statsIter);
        long endIter = System.nanoTime();

        long startRec = System.nanoTime();
        recursiveSelectionSort(copy2, 0, statsRec);
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
