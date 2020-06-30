package sort;

public class BubbleSort {


    /**
     * The elements bubbles up from bottom (left) to the surface (right) one element at a time.
     * This is a stable sort. If we have two elements equal, the order is preserved.
     * Space O(1), Time O(n ^ 2), for sorted or semi-sorted array: O(n)
     * @param arr
     * @param <T>
     */
    public static <T extends Comparable<T>> void sort(T[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        boolean keepOn = true;
        for (int i = 0; i < arr.length && keepOn; i++) {
            int index = 0;
            keepOn = false;
            while (index < arr.length - i - 1) {
                if (arr[index].compareTo(arr[index + 1]) > 0) {
                    swap(arr, index, index + 1);
                    keepOn = true;
                }
                index++;
            }
        }
    }

    private static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
