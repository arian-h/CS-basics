package algorithm.dp;

import com.google.common.base.Preconditions;

import java.util.Arrays;

public class RodCutter {

    /**
     * Dynamic programming solution to find out what is value of a rod if cut into smaller pieces
     *
     * Like the coin changing problem, starting from price for the smallest piece, find out how adding it to another
     * piece would increase the value for sum of the two pieces. Continue doing that and find the value for
     * the whole piece. 
     *
     * Note that the given rod is of size prices.length
     *
     * @param price price list for all the smaller or equal pieces
     * @return the maximum value of the given rod
     */
    public static int maxGain(int[] price) {
        Preconditions.checkArgument(price != null);
        Preconditions.checkArgument(price.length > 0);
        Preconditions.checkArgument(!Arrays.stream(price).filter(p -> p < 0).findAny().isPresent());
        int[] mem = Arrays.copyOf(price, price.length);
        for (int i = 1; i < price.length; i++) {
            for (int j = i; i + j < price.length; j++) {
                mem[i + j] = Math.max(mem[i + j], mem[i] + mem[j]);
            }
        }
        return mem[price.length - 1];
    }

}
