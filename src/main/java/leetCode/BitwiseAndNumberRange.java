package leetCode;

public class BitwiseAndNumberRange {

    /**
     * https://leetcode.com/problems/bitwise-and-of-numbers-range/
     *
     * Write the numbers in binary format, their common prefix doesn't change.
     * the rest of them will change, because at least they become 0 once in the path of and'ing.
     *
     * Find the common prefix between the numbers, keep it and make the rest zero.
     *
     * @param m range lower bound
     * @param n range upper bound
     * @return bitwise of numbers between m and n (inclusive)
     */
    public static int bitwiseAnd(int m, int n) {
        if (countDigits(n) != countDigits(m)) {
            return 0;
        }
        int d = countDigits(n) - 1;
        int k = 0;
        while (d >= 0 && getDigit(m, d) == getDigit(n, d)) {
            k <<= 1;
            k += getDigit(m, d);
            d--;
        }
        while (d >= 0) {
            k <<= 1;
            d--;
        }
        return k;
    }

    private static int countDigits(int n) {
        return (int) (Math.ceil(Math.log(n) / Math.log(2)));
    }

    private static int getDigit(int m, int i) {
        return (m & (1 << i)) > 0 ? 1 : 0;
    }
}
