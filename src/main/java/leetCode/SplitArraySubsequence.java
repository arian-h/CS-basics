package leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class SplitArraySubsequence {

    /**
     * https://leetcode.com/problems/split-array-into-consecutive-subsequences/
     *
     * Given an array of integers, sorted in ascending order, is it possible to split this array into subsequences
     * of length at least 3?
     *
    **/
    public static boolean isSplittable(int[] nums) {
        List<List<Integer>> subsequences = new ArrayList<>();
        for (int n: nums) {
            int index = findSubsequence(subsequences, n);
            List<Integer> subsequence;
            if (index == -1) {
                subsequence = new ArrayList<>();
                subsequences.add(subsequence);
            } else {
                subsequence = subsequences.get(index);
            }
            subsequence.add(n);
        }
        for (List<Integer> subsequence: subsequences) {
            if (subsequence.size() < 3) {
                return false;
            }
        }
        return true;
    }

    private static int findSubsequence(List<List<Integer>> subsequences, int n) {
        int index = -1;
        for (int i = 0; i < subsequences.size(); i++) {
            List<Integer> subsequence = subsequences.get(i);
            if (subsequence.get(subsequence.size() - 1) == n - 1 &&
                    (index == -1 || subsequence.size() < subsequences.get(index).size())) {
                index = i;
            }
        }
        return index;
    }

}
