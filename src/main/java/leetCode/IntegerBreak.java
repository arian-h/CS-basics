package leetCode;

public class IntegerBreak {
    /**
     * https://leetcode.com/problems/integer-break/
     *
     * Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of
     * those integers. Return the maximum product you can get.
     *
     * Input: 10
     * Output: 36
     * Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
     *
     * Solution: Let's say we have a number N. We know that for a large enough number,
     * the maximum we can get out of it is when it's broken down into number: N/2 * N/2 (if it is even)
     * and (N - 1) / 2 * (N + 1) / 2 when it's odd.
     *
     * This is true for all numbers >= 4 (just solve the equation and see it).
     * So as long as we see a number (or a factor) larger than 3, we need to break it down.
     * It doesn't make sense to break it to 1, so all the factors must be either 2 or 3. And 3 is actually preferred.
     * 3 * 3 > 2 * 2 * 2
     *
     */

    public static int integerBreak(int n) {
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        int product = 1;
        while (n > 4) {
            product *= 3;
            n -= 3;
        }
        return product * n;
    }
}
