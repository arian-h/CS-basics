package leetCode;

import java.util.ArrayList;
import java.util.List;

public class NumberFactors {

    /**
     * Find all sets of numbers that multiplying numbers in each set equals to the number.
     * For example: 12 : {{2, 2, 3}, {3, 4}, {2, 6}}
     * Do not include 1, or the number itself in the answer
     * For example: 37: {}
     *
     * To reduce the complexity of the problem, first find the divisors of the given number and recursively move over
     * these numbers to find the answer. In each step, if the number in the recursion can be divided by the divisor,
     * either include it or not, otherwise don't include it and move to the next one.
     * If the given number is 1, just check if the length of the path so far is equal to or larger than 0 put it in
     * the final answer
     *
     * @return A set of sets, each set consists of numbers that multiplying them would result the number
     */
    public static List<List<Integer>> getFactors(int n) {
        List<Integer> divisors = getDivisors(n);
        List<Integer> partialFactors = new ArrayList<>();
        List<List<Integer>> factors = new ArrayList<>();
        getFactors(n, divisors, 0, partialFactors, factors);
        return factors;
    }

    private static void getFactors(int n, List<Integer> divisors, int index, List<Integer> partialFactors,
                                   List<List<Integer>> factors) {
        if (n == 1) {
            if (partialFactors.size() > 0) {
                factors.add(new ArrayList<>(partialFactors));
            }
        } else {
            if (index < divisors.size()) {
                if (n % divisors.get(index) == 0) {
                    partialFactors.add(divisors.get(index));
                    getFactors(n / divisors.get(index), divisors, index, partialFactors, factors);
                    partialFactors.remove(partialFactors.size() - 1);
                }
                getFactors(n, divisors, index + 1, partialFactors, factors);
            }
        }
    }

    private static List<Integer> getDivisors(int n) {
        List<Integer> divisors = new ArrayList<>();
        for (int i = 2; i <= n / 2; i++) {
            divisors.add(i);
        }
        return divisors;
    }

}