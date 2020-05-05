package algorithm.greedy;

import com.google.common.base.Preconditions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public class FractionalKnapsack {

    private final static double epsilon = 0.001;

    /**
     * This problem has a greedy solution, meaning choosing a locally optimum solution for its sub-problems lead to
     * globally optimum solution for the problem
     *
     * Move over the materials in decreasing order of their values per weight, take as much as you can
     * Why it works? Very simple, let's say it doesn't lead to a globally optimum solution, then we can prove that
     * by replacing one of the selected materials with a higher value, we get a better solution.
     *
     * Time complexity: O(nlogn), n number of materials
     *
     * @param weight how much we can take from the sack
     * @param sack a mpa from value of each material (per unit of weight) to how much material we have for it
     * @return maximum value we can get from the sack
     * @throws IllegalArgumentException
     */
    public static double choose(double weight, Map<Double, Double> sack) throws IllegalArgumentException {
        Preconditions.checkArgument(sack != null);
        Preconditions.checkArgument(weight > 0);
        Double[] values = sack.keySet().toArray(new Double[0]);
        Arrays.sort(values, Comparator.reverseOrder());
        double value = 0;
        int index = 0;
        while (!isEqual(weight, 0) && index < values.length) {
            double maxUsableWeight = Math.min(sack.get(values[index]), weight);
            weight -= maxUsableWeight;
            value += values[index] * maxUsableWeight;
            index++;
        }
        return value;
    }

    private static boolean isEqual(double a, double b) {
        return Math.abs(a - b) < epsilon;
    }
}
