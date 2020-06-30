package sort;

public class BubbleSort {

    public static <T extends Comparable<T>> void sort(T[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            int index = i;
            while (index > 0 && arr[index].compareTo(arr[index - 1]) < 0) {
                swap(arr, index, index -1);
                index--;
            }
        }
    }

    private static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
