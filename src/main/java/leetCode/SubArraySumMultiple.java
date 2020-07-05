package leetCode;

import java.util.HashSet;
import java.util.Set;

public class SubArraySumMultiple {

    /**
     * https://leetcode.com/problems/continuous-subarray-sum/
     *
     * Given a list of non-negative numbers and a target integer k, write a function to check if the array
     * has a continuous subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k
     * where n is also an integer.
     *
     * Input: [23, 2, 4, 6, 7],  k=6
     * Output: True
     * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
     *
     * The idea is same as Sub array sum, but with a simple tweak of storing remainder of sums, instead of the sums.
     *
     * Time and space complexity: O(n)
     *
     * @param arr given array of integers
     * @param k
     * @return whether array has a subarray of at least length two, summing up to a multiplication of k
     */
    public static boolean hasSubArray(int[] arr, int k) {
        int sumRemainder = 0;
        Set<Integer> sumRemainders = new HashSet<>();
        for (int i: arr) {
            sumRemainder = (sumRemainder + i) % k;
            if (sumRemainders.contains((k - sumRemainder) % k)) {
                return true;
            }
            sumRemainders.add(sumRemainder);
        }
        return false;
    }
}
