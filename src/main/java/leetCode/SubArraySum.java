package leetCode;

import java.util.HashMap;
import java.util.Map;

public class SubArraySum {

    /**
     * Given an array of integers, find how many subarray exist that their sum add up to k
     * e.g. [1, 1, 1], 2 --> there are two subarrays that their elements add up to 2 [1, 1], [1, 1]
     *
     * Algorithm:
     * To find the sum of a sub array (i, j) (inclusive), the algorithm subtracts sum of sub array (0, i - 1) from
     * (0, j). If it equals to k, then it increases the count of desired subarrays.
     * By moving over the elements, it hols the sum of the elements up to that element (0, j), and by using a map,
     * it counts, how many i's exist, that sum of (0, i - 1)'s are equal to k - sum(0, j).
     *
     * Time and space complexity: O(n)
     *
     * @param arr given array of integers
     * @param k desired sum of elements
     * @return the number of subarrays that their elements add up to k
     */
    public static int count(int[] arr, int k) {
        int count = 0;
        int sum = 0;
        Map<Integer, Integer> sumCount = new HashMap<>();
        sumCount.put(0, 1);
        for (int i: arr) {
            sum += i;
            count += sumCount.getOrDefault(sum - k, 0);
            sumCount.put(sum, sumCount.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
