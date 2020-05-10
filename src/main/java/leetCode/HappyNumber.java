package leetCode;

public class HappyNumber {

    /**
     * Detect if a number is happy or not. A number is happy if as a result of chain of conversion becomes 1.
     * The conversion is sum of squares of the digits of the number. For example: 19 -> 82 -> 68 -> 100 -> 1
     *
     * There are three possible outcomes in this chain:
     *  1. The next number is 1
     *  2. Numbers gets into a loop and never gets to 1
     *  3. Numbers go up indefinitely
     *
     *  In this algorithm the floyd's cycle detection (using two pointers: fast, slow) is used to cover first two items
     *  The 3rd item doesn't happen, because next integer after any integer has no more than 3 digits (why?)
     *
     * @param n number to check if it is happy or not
     * @return whether number is happy
     */
    public static boolean isHappy(int n) {
        int slow = n;
        int fast = sumSquaresDigits(sumSquaresDigits(n));
        while (slow != 1 && slow != fast) {
            slow = sumSquaresDigits(slow);
            fast = sumSquaresDigits(sumSquaresDigits(fast));
        }
        return slow == 1;
    }

    private static int sumSquaresDigits(int n) {
        int sum = 0;
        while (n > 0) {
            sum += (n % 10) * (n % 10);
            n /= 10;
        }
        return sum;
    }
}
