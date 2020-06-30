package sort;

public class InsertionSort {

    /**
     * It bubbles down. Each element on the left (starting from the closest element to the element at hand)
     * is compared with the element at hand, and if it is larger it goes place right.
     * Finally the element at hand is placed in the right position.
     *
     * Time complexity: O(N ^ 2), best case: if the array is semi-sorted
     * Space complexity: O(N)
     *
     * @param arr
     * @param <T>
     */
    public static <T extends Comparable<T>> void sort(T[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            int j = i;
            T current = arr[i];
            while (j > 0 && current.compareTo(arr[j - 1]) < 0) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = current;
        }
    }

}
