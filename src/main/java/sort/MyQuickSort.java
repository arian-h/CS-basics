package sort;

/**
 * Time complexity analysis:
 * T(n) = 2 T(n/2) + n
 * The depth of the tree (if pivot approximately halves the array) is O(logn)
 * Each level involves O(n) operations. Therefore the whole time complexity is O(nlogn)
 */
public class MyQuickSort {

    public static <T extends Comparable<T>> void sort(T[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> void sort(T[] arr, int l, int r) {
        // if indices are correct do it, otherwise skip
        if (l < r) {
            // find the pivot (next element in its right place)
            int pivot = partition(arr, l, r);
            // recursively sort the left hand side of pivot
            sort(arr, l, pivot - 1);
            // recursively sort the right hand side of pivot
            sort(arr, pivot + 1, r);
        }
    }

    /**
     * Find an index that all elements before it in the array is less than last element in the array.
     */
    private static <T extends Comparable<T>> int partition(T[] arr, int l, int r) {
        int next = l;
        // move over the elements and if it is less than the last element, swap it with next, and increase next
        for (int i = l; i <= r; i++) {
            if (arr[i].compareTo(arr[r]) < 0) {
                swap(arr, next, i);
                next++;
            }
        }
        // swap next and r, because we know arr[next] is not less than arr[r], so it's fine to move it to the right
        // of next
        swap(arr, r, next);
        return next;
    }

    private static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}