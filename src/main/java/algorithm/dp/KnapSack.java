package algorithm.dp;

import java.util.Arrays;

public class KnapSack {

    public static int select(int totalCapacity, int[] weight, int[] value) {
        int itemCount = weight.length;
        int[][] maxValue = new int[totalCapacity + 1][itemCount + 1];
        for (int i = 0; i < totalCapacity + 1; i++) {
            Arrays.fill(maxValue[i], 0);
        }
        for (int capacity = 1; capacity < totalCapacity + 1; capacity++) {
            for (int index = 1; index < itemCount + 1; index++) {
                int itemValue = value[index - 1];
                int itemWeight = weight[index - 1];
                if (capacity - itemWeight >= 0) {
                    maxValue[capacity][index] = Math.max(maxValue[capacity - itemWeight][index - 1] + itemValue,
                            maxValue[capacity][index - 1]);
                } else {
                    maxValue[capacity][index] = maxValue[capacity][index - 1];
                }
            }
        }
        return maxValue[totalCapacity][itemCount];
    }

}
