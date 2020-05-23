package leetCode;

import java.util.HashSet;
import java.util.Set;

public class SubArraySumMultiple {

    /**
     * Given an array, find if there is a subarray of at least length two, that sums up to a multiplication of given k
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
