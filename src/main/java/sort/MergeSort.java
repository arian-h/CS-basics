package sort;

public class MergeSort {

    public static void sort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int l, int r) {
        if (r == l) {
            return;
        }
        int mid = (l + r) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        int[] sorted = merge(arr, l, mid, r);
        for (int i = 0; i < sorted.length; i++) {
            arr[i + l] = sorted[i];
        }
    }

    private static int[] merge(int[] arr, int l, int mid, int r) {
        int[] merged = new int[r - l + 1];
        int i = l;
        int j = mid + 1;
        int index = 0;
        while (i <= mid && j <= r) {
            if (arr[i] < arr[j]) {
                merged[index] = arr[i];
                i++;
            } else {
                merged[index] = arr[j];
                j++;
            }
            index++;
        }
        while (i <= mid) {
            merged[index] = arr[i];
            i++;
            index++;
        }
        while (j <= r) {
            merged[index] = arr[j];
            j++;
            index++;
        }
        return merged;
    }
}
