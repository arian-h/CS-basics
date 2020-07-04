package algorithm.greedy;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SetCover {

    /**
     * Given n sets, and a universe that its elements are union of these sets, and a cost associated with each set,
     * find a subset of these sets that added together builds up the universe.
     *
     * This problem is NP-hard and this algorithm is a greedy approximation algorithm for it.
     *
     * This algorithm finds a solution that its cost is no worse than logn * optimal solution cost
     *
     * Consider an empty bag; On each step it adds a subset with minimum effective cost,
     * and continues doing this until the bag is same as the universe.
     * Effective cost is cost of that subset divided by the number of "new" elements that adding that set introduces
     * to the bag.
     *
     * @param subsets list of sets
     * @param costs array of costs associated with each set
     * @param <T>
     * @return an approximation of the sets that can build the universe with minimum cost
     */
    public static <T> List<Set<T>> getMinCostSubsets(List<Set<T>> subsets, double[] costs) {
        Preconditions.checkArgument(subsets != null);
        Preconditions.checkArgument(costs != null);
        Preconditions.checkArgument(subsets.size() == costs.length);
        subsets = new ArrayList<>(subsets);
        List<Set<T>> minSubsets = new ArrayList<>();
        int universeSize = subsets.stream().flatMap(Collection::stream).collect(Collectors.toSet()).size();
        Set<T> bag = new HashSet<>();
        while (bag.size() != universeSize) {
            Set<T> minCostSubset = minCostSubset(subsets, bag, costs);
            subsets.remove(minCostSubset);
            bag.addAll(minCostSubset);
            minSubsets.add(minCostSubset);
        }
        return minSubsets;
    }

    private static <T> Set<T> minCostSubset(List<Set<T>> subsets, Set<T> bag, double[] costs) {
        double min = Double.MAX_VALUE;
        Set<T> minCostSet = null;
        for (int i = 0; i < subsets.size(); i++) {
            Set<T> subset = subsets.get(i);
            double effectiveCost = costs[i] / (subset.size() - intersection(subset, bag).size());
            if (effectiveCost < min) {
                min = effectiveCost;
                minCostSet = subset;
            }
        }
        return minCostSet;
    }

    private static <T> Set<T> intersection(Set<T> a, Set<T> b) {
        return a.stream().filter(b::contains).collect(Collectors.toSet());
    }
}
