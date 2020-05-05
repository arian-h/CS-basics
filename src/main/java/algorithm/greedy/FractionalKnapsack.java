package algorithm.greedy;

import com.google.common.base.Preconditions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public class FractionalKnapsack {

    private final static double epsilon = 0.001;

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
