package algorithm.dp;

public class BinarySearchTree {

    /**
     * Given number of nodes, how many different binary trees can one create? the nodes are distinct.
     *
     * Dynamic programming approach to this problem. This algorithm chooses on node as a root, splits tree into
     * left and right subtrees, and multiply the number of left/ right subtrees. It considers different roots (and therefore
     * different number of nodes for left and right subtrees)
     *
     * Time complexity: O(N ^ 2), Space complexity: O(N)
     *
     * @param n number of nodes in the tree
     * @return number of different binary trees that can be created
     */
    public static int getCount(int n) {
        int[] count = new int[n + 1];
        count[0] = 1;
        count[1] = 1;
        for (int index = 2; index <= n; index++) {
            int l = 0;
            int r = index - 1;
            while (l <= r) {
                if (l == r) {
                    count[index] += count[l] * count[r];
                } else {
                    count[index] += 2 * count[l] * count[r];
                }
                l++;
                r--;
            }
        }
        return count[n];
    }

}
