package sort;

public class HeapSort {

    /**
     * An unstable, in-place sort
     * Time complexity: O(n * logn)
     * Space complexity: O(1)
     * @param arr
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        // add to the max heap
        for (int i = 0; i < arr.length; i++) {
            int index = i;
            while (index > 0 && arr[index] > arr[parent(index)]) {
                swap(arr, index, parent(index));
                index = parent(index);
            }
        }

        // extract from max heap one by one and put it at the end of the arr
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            int index = 0;
            while (index < i) {
                if (left(index) < i && arr[left(index)] > arr[index]) {
                    swap(arr, index, left(index));
                }
                if (right(index) < i && arr[right(index)] > arr[index]) {
                    swap(arr, index, right(index));
                }
                index++;
            }
        }
    }

    private static int parent(int i) {
        return i / 2;
    }

    private static int left(int i) {
        return 2 * i;
    }

    private static int right(int i) {
        return (2 * i) + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
