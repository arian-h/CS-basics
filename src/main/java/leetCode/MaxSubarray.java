package leetCode;

import javafx.util.Pair;

public class MaxSubarray {

    /**
     * Given an array of integers, find the contiguous subarray that has the maximum sum
     *
     * This is a greedy approach to the maximum subarray problem.
     * The algorithm moves over the array, and on each element it asks itself a question:
     *
     * Should I start a subarray from here or not (i.e. keep the current subarray)?
     * The answer depends on whether the sum of the subarray is contributing (it is positive) or not (it's negative)
     *
     * If it contributes it keeps it (i.e. doesn't change left index), otherwise, it starts a new subarray from here.
     *
     * There are some other details as well like keeping and sum and comparing it with the global sum,
     * and updating the global optimal subarray.
     *
     * @param arr an array of n integer
     * @return subarray with maximum sum
     */
    public static Pair<Integer, Integer> findSubarray(int[] arr) {
        int left = 0, right = 0; // inclusive
        int sum = arr[0], maxSum = arr[0];
        int index = 0;
        for (int i = 1; i < arr.length; i++) {
            if (sum < 0) {
                index = i;
                sum = arr[i];
            } else {
                sum = sum + arr[i];
            }
            if (sum > maxSum) {
                right = i;
                left = index;
                maxSum = sum;
            }
        }
        return new Pair<>(left, right);
    }

    /**
     * Same as the algorithm above, without too much details.
     * The algorithm updates the sum on each element. If sum so far was positive, it adds it to the current element
     * otherwise, it doesn't add it. It updates the global maximum as well.
     *
     * @param arr
     * @return sum of the subarray with maximum sum
     */
    public static int getMaxSum(int[] arr) {
        int sum = arr[0], maxSum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum = arr[i] + Math.max(sum, 0);
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
    }

}
