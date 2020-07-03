package algorithm.dp;

import com.google.common.base.Preconditions;

public class Fibonacci {

    /**
     * Compute n-th fibonacci number
     *
     * Dynamic programming approach for fibonacci problem.
     *
     * Time complexity: O(n)
     *
     * Space complexity: O(1)
     *
     * @param n
     * @return
     */
    public static long compute(int n) {
        int[] fib = new int[] {0, 1};
        int i = 1;
        while (i < n) {
            int t = fib[0] + fib[1];
            fib[0] = fib[1];
            fib[1] = t;
            i++;
        }
        return fib[1];
    }

}
