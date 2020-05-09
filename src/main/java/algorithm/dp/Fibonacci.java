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
        Preconditions.checkArgument(n > 0);
        if (n <= 2) {
            return 1;
        }
        long a = 1, b = 1;
        long f = 0;
        for (int i = 3; i <= n; i++) {
            f = a + b;
            a = b;
            b = f;
        }
        return f;
    }

}
