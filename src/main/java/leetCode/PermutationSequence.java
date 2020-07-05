package leetCode;

import java.util.Arrays;

public class PermutationSequence {

    /**
     * What is the k-th number in the sequence of permutations of numbers from 1...n
     * For example, the 3rd number for 1,2,3,4 is:
     * 1234, 1243, 1324, ...
     * It is 1324
     *
     * Algorithm:
     * Starting from the first digit on left, algorithm checks if the digit may be different in the result
     * (given the order (k)).
     * If it won't be different, it goes to the next one, otherwise, it finds out how many times it changes. And sets it.
     * How does it find if it changes or not? If k is larger than factorial of digit counts on the right of the digit
     * then it means that it will definitely change.
     *
     * @param n
     * @param k
     * @return
     */
    public static int get(int n, int k) {
        int[] num = initializeNumberArray(n);
        for (int i = 0; i < n && k > 1; i++) {
            int factorial = factorial(n - i - 1); // permutations count for the digits on right of i-th digit
            if (k > factorial) { // i-th digit would change
                int change = (int) Math.ceil((double) k / factorial) - 1; // how many times it may change? 0 means no change
                changeDigit(num, i, change); // change the i-th digit for 'change' number of times
                k -= factorial * change;
            }
        }
        int total = 0;
        for (int i: num) {
            total = total * 10 + i;
        }
        return total;
    }

    private static int[] initializeNumberArray(int n) {
        int[] num = new int[n];
        for (int i = 1; i <= n; i++) {
            num[i - 1] = i;
        }
        return num;
    }

    private static int factorial(int n) {
        int r = 1;
        while (n > 0) {
            r *= n;
            n--;
        }
        return r;
    }

    private static void changeDigit(int[] num, int index, int change) {
        Arrays.sort(num, index, num.length);
        swap(num, index, index + change);
        Arrays.sort(num, index + 1, num.length);
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

}
