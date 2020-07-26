package leetCode;

public class SingleNumber {

    /**
     * https://leetcode.com/problems/single-number-ii/
     *
     * Given a non-empty array of integers, every element appears three times except for one, which appears exactly once.
     * Find that single one.
     *
     * Solution: We know the answer is a 32 bit integer. This algorithm figures out whether each bit of the result is
     * 0 or 1. Iterate over each bit of the input numbers (32 * n), and count the number of 1's for that bit.
     * If it is a multiply of 3, it means that bit must be 0 in the result.
     *
     * @param arr
     * @return
     */
    public static int singleNumber(int[] arr) {
        int num = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int n: arr) {
                count += (n >> i) & 1;
            }
            if (count % 3 != 0) {
                num |= (1 << i);
            }
        }
        return num;
    }
}
