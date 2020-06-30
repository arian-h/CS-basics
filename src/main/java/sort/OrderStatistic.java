package sort;

import com.google.common.base.Preconditions;

public class OrderStatistic {

    /**
     * Find i-th order statistic.
     *
     * @param arr
     * @return the i-th smallest element in the arr
     */
    public static int find(int[] arr, int i) {
        Preconditions.checkArgument(i <= arr.length, "order must be smaller than the list size");
        int p = -1;
        int l = 0;
        int r = arr.length - 1;
        while (p != i) {
            p = partition(arr, l , r);
            if (p < i) {
                l = p + 1;
            } else if (p > i) {
                r = p - 1;
            }
        }
        return arr[p];
    }

    private static int partition(int[] arr, int l, int r) {
        int next = l;
        for (int i = l; i <= r; i++) {
            if (arr[i] < arr[r]) {
                swap(arr, i, next);
                next++;
            }
        }
        swap(arr, r, next);
        return next;
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
