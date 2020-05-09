package algorithm.dp;

import java.util.Arrays;

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


    /**
     * Given probability of seeing n elements, what is the minimum average number of comparisons to check existence of
     * these elements in a bst. An array of probabilities, sorted in increasing order of the node keys, is given.
     *
     * This algorithm is a dynamic programming approach to this problem. First let's prove:
     *          C(T) = C(L) + C(R) + SUM(probabilities of nodes in T)
     * C(T) = SUM(probability * (depth + 1))
     * C(L) = SUM(probability * (depth + 1 + 1))
     * C(R) = SUM(probability * (depth + 1 + 1))
     *
     * Each summing over the nodes in their own trees, depth is depth of the original tree root
     * C(L) + C(R) + root probability + probability of left subtree nodes + probability of right subtree nodes= C(T)
     *
     * Time complexity: O(N ^ 3), Space complexity: O(N ^ 2)
     * 
     * @param probability list of probability of seeing each node, sorted in increasing order of node values
     * @return minimum average cost of checking existence of nodes
     */
    public static double minCost(double[] probability) {
        int n = probability.length;
        double[][] cost = new double[n][n];
        for (int i = 0; i < n; i++) {
            cost[i][i] = probability[i];
        }
        for (int l = 1; l < n; l++) {
            for (int i = 0; i + l < n; i++) {
                double min = Double.MAX_VALUE;
                for (int k = i; k <= i + l; k++) {
                    if (k == i) {
                        min = Math.min(min, cost[i + 1][i + l]);
                    } else if (k == i + l) {
                        min = Math.min(min, cost[i][i + l - 1]);
                    } else {
                        min = Math.min(min, cost[i][k - 1] + cost[k + 1][i + l]);
                    }
                }
                double pSum = Arrays.stream(probability).skip(i).limit(l + 1).sum();
                cost[i][i + l] = min + pSum;
            }
        }
        return cost[0][n-1];
    }

}
