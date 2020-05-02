package algorithm.famous;

public class ThreewayParition {

    public static void threewayPartition(int[] arr, int p) {
        /*
            for the current element, compare it with pivot, if it is
                Smaller: swap it with smallerNext, and go to the next element in list,
                Larger: swap it with largerNext, but stay where you are
                (update the smallerNext and largerNext accordingly)
                Equal: just go to the next element
         */
        int smallerNext = 0;
        int largerNext = arr.length - 1;
        for (int i = 0; i < arr.length && i <= largerNext; i++) {
            if (arr[i] < p) {
                swap(arr, i, smallerNext);
                smallerNext++;
            } else if (arr[i] > p){
                swap(arr, i, largerNext);
                largerNext--;
                i--;
            }
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
