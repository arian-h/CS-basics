package algorithm.famous;

public class ThreewayParition {

    /**
     * Think of the array as vertical list of elements written on a wall.
     * We have three stacks:
     * lower stack: grows from the bottom, having elements that are smaller than p
     * mid stack: is on top of the lower stack, having elements that are yet to be decided
     * top stack: grows from top to the bottom, and contains elements that are larger than p
     *
     * We start from the first element (on the bottom of our depicted list)
     * if it is less than p, we swap it with where the top of the lower stack points to
     * if it is larger than p, we swap it with where the bottom of the top stack points to
     * otherwise we just move to the next one
     *
     * This process loops until top of the middle stack goes over the bottom of the top stack.
     *
     * O(1) space, O(n) time.
     */
    public static void threewayPartition(int[] arr, int p) {
        int lowerTop = 0;
        int midTop = 0;
        int largerBottom = arr.length - 1;
        while (midTop <= largerBottom) {
            if (arr[midTop] < p) {
                swap(arr, lowerTop, midTop);
                midTop++;
                lowerTop++;
            } else if (arr[midTop] > p) {
                swap(arr, largerBottom, midTop);
                largerBottom--;
            } else {
                midTop++;
            }
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
