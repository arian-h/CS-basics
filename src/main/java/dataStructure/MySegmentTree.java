package dataStructure;
import com.google.common.base.Preconditions;

import java.util.Arrays;

/**
 * Segment trees are used to query aggregated data on a large number of items
 * e.g. we have an N elements, and we want to aggregate (Max or Min) a sub-array [i, j] of these items
 * How to construct:
 *  1. First create an array of size 2 * (a power of 2 number larger than or equal to size of input)
 *  2. Leave the first half of array and copy the input into the next half of it, leave rest as null
 *      e.g. input = [1,2,3,4,5] --> [null, null, null, null, null, null, null, null, 1, 2, 3, 4, 5, null, null, null]
 *  3. Treat this array as a tree, with first half as internal nodes, and second half as leaves
 *  4. Construct the tree (using the array) recursively, each node is aggregate of left and right children
 *
 *  Time complexity: O(n), Space complexity: O(n)
 *
 * How to query:
 *  1. Start from the root, having the query range, and range of original input
 *  2. Recursively check query range with node range:
 *       - If there is no overlap, return nothing
 *       - If node is fully within query range, return aggregated value for that node (arr[index])
 *       - else (If node is partially within query range), aggregate left and right child values
 *         by keeping the query range, and breaking up the node ranges
 *
 *  Time complexity: O(logn)
 *      We prove that the algorithm doesn't cover more than 4 nodes on each level of the tree, therefore its O(logn)
 *      At the top it starts with 1 node, worst case it goes to both children on second level,
 *      worst case it goes to 4 nodes (each of the nodes going to their children) on third level
 *      from here, the middle children are not continues, as they are completely encompassed within the query range
 *      (case 2 on How to query), therefore they return, and only two children on right and left continue
 *      recursively we see it's impossible to visit more than 4 nodes on each tree level
 *      there are O(logn) levels, therefore time complexity is O(logn)
 */
public class MySegmentTree implements IMySegmentTree {

    private final int inputSize;
    private final Integer[] arr;
    private final Mode mode;

    @Override
    public int query(int low, int high) {
        Preconditions.checkArgument(low <= high, "bad input range");
        Integer q = query(low, high, 0, inputSize - 1, 0);
        return q == null ? (mode == Mode.MAX ? Integer.MIN_VALUE : Integer.MAX_VALUE) : q;
    }

    public static IMySegmentTree getInstance(int[] arr, IMySegmentTree.Mode mode) {
        Preconditions.checkArgument(arr != null && arr.length > 0, "array cannot be null or empty");
        return new MySegmentTree(arr, mode);
    }

    private MySegmentTree(int[] input, Mode mode) {
        this.inputSize = input.length;
        this.mode = mode;
        this.arr = new Integer[2 * (int) Math.pow(2, Math.ceil(Math.log(this.inputSize) / Math.log(2)))];
        construct(Arrays.stream(input).boxed().toArray(Integer[]::new));
    }

    private void construct(Integer[] input) {
        System.arraycopy(input, 0, this.arr, (this.arr.length / 2) - 1, input.length);
        construct(this.arr, 0);
    }

    private Integer construct(Integer[] arr, int index) {
        if (!inRange(index)) {
            return null;
        }
        if (arr[index] != null) {
            return arr[index];
        }
        arr[index] = aggregate(construct(arr, left(index)), construct(arr, right(index)));
        return arr[index];
    }

    private Integer aggregate(Integer a, Integer b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        switch (mode) {
            case MAX:
                return Math.max(a, b);
            case MIN:
                return Math.min(a, b);
            default: // never happens
                return null;
        }
    }

    private boolean inRange(int index) {
        return index >= 0 && index <= arr.length / 2 + inputSize;
    }

    private int left(int index) {
        return 2 * index + 1;
    }

    private int right(int index) {
        return 2 * index + 2;
    }

    private Integer query(int qLow, int qHigh, int low, int high, int index) {
        if (fullOverlap(qLow, qHigh, low, high)) {
            return arr[index];
        }
        if (noOverlap(qLow, qHigh, low, high)) {
            return null;
        }
        int mid = (low + high) / 2;
        //partial overlap
        return aggregate(query(qLow, qHigh, low, mid, 2 * index + 1),
                query(qLow, qHigh, mid + 1, high, 2 * index + 2));
    }

    /**
     * whether [bLow, bHigh] is within [aLow, aHigh]
     */
    private boolean fullOverlap(int aLow, int aHigh, int bLow, int bHigh) {
        return bLow >= aLow && bHigh <= aHigh && aLow <= aHigh && bLow <= bHigh;
    }

    /**
     * whether [bLow, bHigh] has no intersection with [aLow, aHigh]
     */
    private boolean noOverlap(int aLow, int aHigh, int bLow, int bHigh) {
        return (bLow <= bHigh && aLow <= aHigh) && (bHigh < aLow || bLow > aHigh);
    }

}
