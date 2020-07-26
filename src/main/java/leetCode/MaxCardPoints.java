package leetCode;

import java.util.Arrays;

public class MaxCardPoints {
    /**
     * https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
     * There are several cards arranged in a row, and each card has an associated number of points The points are given in the integer array cardPoints.
     * In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
     * Your score is the sum of the points of the cards you have taken.
     * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
     *
     * The solution is simple. However you select the cards, the solution is always two subarrays, one starting from
     * the start of the array, and one starting from the other end of the array. The total size of these two subarrays
     * is k, and each subarray can be empty.
     *
     * Therefore we first select the first k subarray, as a candidate, and the remove one from the end of it,
     * and select one from the end. This is similar to moving a window of size n - k over the original array and
     * calculating sum of the elements not masked by the window.
     *
     * O(k) time complexity
     * O(1) space complexity
     */

    public static int maxScore(int[] arr, int k) {
        int i = k - 1;
        int j = arr.length - 1;
        int sum = Arrays.stream(arr).limit(k).sum();
        int max = Integer.MIN_VALUE;
        max = Math.max(max, sum);
        while (i > 0) {
            sum -= arr[i];
            sum += arr[j];
            max = Math.max(max, sum);
            i--;
            j--;
        }
        return max;
    }
}
