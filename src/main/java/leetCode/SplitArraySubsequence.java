package leetCode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class SplitArraySubsequence {

    /**
     * Given an array of integers, sorted in ascending order, is it possible to split this array into subsequences
     * of length at least 3?
     *
     * This algorithm keeps two maps:
     *  From last value of a short sequence (length < 3) to its list of indices
     *  From last value of a long sequence (length >= 3) to its number of elements that are not part of a sequence
     *
     *  For example:
     *      Short sequence map: 1: [0, 3] <-- there are two short subsequence that end with 1
     *      Long sequence map: -1: 4 <-- this entry means there are four long subsequences that end with -1
     *
     *  The algorithm uses an array too, to keep the length of subsequences ending at each element.
     *
     *  At the end it checks to see if there is any short subsequence.
     *
     *  Time and space complexity: O(n)
     *
     * @return whether array can be split into subsequences with at least length 3 each
     */
    public static boolean isSplittable(int[] nums) {
        int[] seqLength = new int[nums.length];
        Map<Integer, Queue<Integer>> shortSeqLastElementIndex = new HashMap<>();
        Map<Integer, Integer> longSeqLastFreeElementCount = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (shortSeqLastElementIndex.containsKey(nums[i] - 1)) {
                int index = shortSeqLastElementIndex.get(nums[i] - 1).poll();
                if (shortSeqLastElementIndex.get(nums[i] - 1).size() == 0) {
                    shortSeqLastElementIndex.remove(nums[i] - 1);
                }
                seqLength[i] = seqLength[index] + 1;
                if (seqLength[i] >= 3) {
                    longSeqLastFreeElementCount.put(nums[i], longSeqLastFreeElementCount.getOrDefault(nums[i], 0) + 1);
                } else {
                    shortSeqLastElementIndex.putIfAbsent(nums[i], new LinkedList<>());
                    shortSeqLastElementIndex.get(nums[i]).offer(i);
                }
            } else if (longSeqLastFreeElementCount.containsKey(nums[i] - 1)) {
                if (longSeqLastFreeElementCount.get(nums[i] - 1) == 1) {
                    longSeqLastFreeElementCount.remove(nums[i] - 1);
                } else {
                    longSeqLastFreeElementCount.put(nums[i] - 1, longSeqLastFreeElementCount.get(nums[i] - 1) - 1);
                }
                longSeqLastFreeElementCount.put(nums[i], longSeqLastFreeElementCount.getOrDefault(nums[i], 0) + 1);
            } else {
                seqLength[i] = 1;
                shortSeqLastElementIndex.putIfAbsent(nums[i], new LinkedList<>());
                shortSeqLastElementIndex.get(nums[i]).offer(i);
            }
        }
        return shortSeqLastElementIndex.isEmpty();
    }

}
