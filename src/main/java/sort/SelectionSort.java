package sort;

public class SelectionSort {

    /**
     * This is an unstable sort. At the i-th step, it finds the minimum element on [i, arr.length - 1]
     * and replace i-th element with it.
     * @param arr
     * @param <T>
     */
    public static <T extends Comparable<T>> void sort(T[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            swap(arr, i, findMin(arr, i));
        }
    }

    private static <T extends Comparable<T>> int findMin(T[] arr, int i) {
        int min = i;
        for (int j = i + 1; j < arr.length; j++) {
            if (arr[j].compareTo(arr[min]) < 0) {
                min = j;
            }
        }
        return min;
    }

    private static <T extends Comparable<T>> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
