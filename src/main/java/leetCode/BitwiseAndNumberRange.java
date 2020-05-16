package leetCode;

public class BitwiseAndNumberRange {

    /**
     * Write the numbers in binary format, there is a common prefix between these strings (binary representations)
     * that doesn't change after and'ing all the numbers. the rest of them will change, because at least they
     * became 0 once.
     * Find the common prefix between the numbers, keep it and make the rest zero.
     *
     * @param m range lower bound
     * @param n range upper bound
     * @return bitwise of numbers between m and n (inclusive)
     */
    public static int bitwiseAnd(int m, int n) {
        int shift = 0;
        while (m != n) {
            m = m >> 1;
            n = n >> 1;
            shift++;
        }
        while (shift > 0) {
            m = m << 1;
            shift--;
        }
        return m;
    }
}
