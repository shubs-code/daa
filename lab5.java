import java.util.Random;

public class lab5 {

    static int totalComparisons = 0;
    static int totalSwaps = 0;
    static int pass = 1;

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int[] partitionResult = partition(arr, low, high);
            int pivotIndex = partitionResult[0];
            int comparisons = partitionResult[1];
            int swaps = partitionResult[2];

            // Print comparisons and swaps for this pass
            System.out.println("Pass " + pass++ + ": Comparisons = " + comparisons + ", Swaps = " + swaps);

            totalComparisons += comparisons;
            totalSwaps += swaps;

            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int[] partition(int[] arr, int low, int high) {
        Random rand = new Random();
        int pivotIndex = low + rand.nextInt(high - low + 1); // random pivot
        swap(arr, pivotIndex, high); // move pivot to end
        int pivot = arr[high];

        int i = low - 1;
        int comparisons = 0;
        int swaps = 0;

        for (int j = low; j < high; j++) {
            comparisons++;
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
                swaps++;
            }
        }

        swap(arr, i + 1, high); // put pivot in correct position
        swaps++;

        return new int[]{i + 1, comparisons, swaps}; // return pivot index, comparisons, swaps
    }

    private static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    // Driver code
    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5};
        System.out.println("Original array:");
        printArray(arr);

        quickSort(arr, 0, arr.length - 1);

        System.out.println("\nSorted array:");
        printArray(arr);
        System.out.println("\nTotal Comparisons: " + totalComparisons);
        System.out.println("Total Swaps: " + totalSwaps);
    }

    private static void printArray(int[] arr) {
        for (int value : arr)
            System.out.print(value + " ");
        System.out.println();
    }
}
