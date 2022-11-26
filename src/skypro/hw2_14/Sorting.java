package skypro.hw2_14;

import java.util.Random;
import java.util.function.Consumer;

public class Sorting {

    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        double timeForBubbleSort = measure(10, Sorting::sortBubble);
        System.out.printf("Среднее время пузырька: %.2f%n", timeForBubbleSort);
        double timeForSelectionSort = measure(10, Sorting::sortSelection);
        System.out.printf("Среднее время выбором: %.2f%n", timeForSelectionSort);
        double timeForInsertionSort = measure(10, Sorting::sortInsertion);
        System.out.printf("Среднее время выбором: %.2f%n", timeForInsertionSort);
    }

    private static double measure(int iterations, Consumer<int[]> sort) {
        long times = 0;
        for (int i = 0; i < iterations; i++) {
            int[] array = generateArray(100_000);
            long start = System.currentTimeMillis();
            sort.accept(array);
            times = times + (System.currentTimeMillis() - start);
        }
        return times / (double) iterations;
    }

    private static int[] generateArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = RANDOM.nextInt();
        }
        return array;
    }

    public static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    public static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

}
