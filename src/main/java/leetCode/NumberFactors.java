package leetCode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NumberFactors {

    /**
     * Find all sets of numbers that multiplying numbers in each set equals to the number.
     * For example: 12 : {{2, 2, 3}, {3, 4}, {2, 6}}
     * Do not include 1, or the number itself in the answer
     * For example: 37: {}
     *
     *
     * @return A list of factors, each set consists of numbers that multiplying them would result the number
     */
    public static List<List<Integer>> getFactors(int n) {
        return getFactors(n, n).stream().filter(l -> !l.isEmpty()).collect(Collectors.toList());
    }

    private static List<List<Integer>> getFactors(int n, int original) {
        List<List<Integer>> factorsList = new ArrayList<>();
        if (n == 1) {
            factorsList.add(new ArrayList<>());
            return factorsList;
        }
        for (int i = 2; i <= n && i < original; i++) {
            if (n % i == 0) {
                for (List<Integer> subFactor: getFactors(n / i, original)) {
                    List<Integer> clonedSubFactor = new ArrayList<>(subFactor);
                    clonedSubFactor.add(i);
                    factorsList.add(clonedSubFactor);
                }
            }
        }
        return dedupFactorsList(factorsList);
    }

    static List<List<Integer>> dedupFactorsList(List<List<Integer>> factorsList) {
        Map<String, List<Integer>> factorsMap = new HashMap<>();
        for (List<Integer> factors: factorsList) {
            factors.sort(Comparator.comparingInt(n2 -> n2));
            factorsMap.put(factors.toString(), factors);
        }
        return new ArrayList<>(factorsMap.values());
    }
}