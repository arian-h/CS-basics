package leetCode;

import java.util.HashMap;
import java.util.Map;

public class EqualZeroOne {

    /**
     * https://leetcode.com/problems/contiguous-array/
     * 
     * Find the subarray that has equal number of 0's and 1's
     *
     * Iterate over the array, and find the earliest index that had the same different in the count of 0's and 1's
     * from the beginning of the array. If this difference didn't change between these two indices,
     * then there are equal number of 0's and 1's in that subarray.
     *
     * For example: Given [ 0, 1 , 1 , 1, 0, 0, 0, 1 ] count the
     * 0th index: one 0, no 1 --> +1
     * 1st index: one 0, one 1 --> 0
     * 2nd index: one 0, two 1's --> -1
     * 3rd index: one 0, three 1's --> -2
     * 4th index: two 0's, three 1's --> -1
     * 5th index: three 0's, three 1's --> 0
     * 6th index: four 0's, three 1's --> +1
     * 7th index: four 0's, four 1's --> 0
     *
     * as you can see 3rd number in the list (index 2) and 5th number in the list (index 4) have the same difference,
     * therefore the list between them didn't contribute to this difference, which means it has the same number of
     * 0's and 1's. By storing the first index, for each of these "differences" we can find the maximum length of
     * the subarray that has equal number of 0's and 1's.
     *
     * Time complexity: O(n)
     * Space complexity: O(n)
     *
     * @param nums an array of 0's and 1's
     * @return length of the longest subarray with equal number of 0's and 1's
     */
    public static int maxLength(int[] nums) {
        Map<Integer, Integer> earliestIndex = new HashMap<>();
        earliestIndex.put(0, -1);
        int countOnes = 0;
        int max = 0;
        for (int index = 0; index < nums.length; index++) {
            countOnes += nums[index] == 1 ? 1 : -1;
            if (earliestIndex.containsKey(countOnes)) {
                max = Math.max(max , index - earliestIndex.get(countOnes));
            } else {
                earliestIndex.put(countOnes, index);
            }
        }
        return max;
    }

}
