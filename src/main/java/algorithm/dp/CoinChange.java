package algorithm.dp;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoinChange {

    public static List<Integer> change(int val, Integer[] coins) {
        Preconditions.checkArgument(coins != null && coins.length > 0);
        Preconditions.checkArgument(val > 0);
        Integer[] mem = new Integer[val + 1];
        Integer[] parent = new Integer[val + 1];
        Arrays.fill(mem, null);
        Arrays.fill(parent, null);
        for (int coin: coins) {
            if (coin < mem.length) {
                mem[coin] = 1;
                parent[coin] = coin;
            }
        }
        for (int i = 1; i < mem.length; i++) {
            for (int c: coins) {
                if (mem[i] != null) {
                    if (i + c < mem.length) {
                        if (mem[i + c] == null || mem[i] + 1 < mem[i + c]) {
                            mem[i + c] = mem[i] + 1;
                            parent[i + c] = c;
                        }
                    }
                }
            }
        }
        if (mem[val] == null) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        int index = val;
        while (index > 0) {
            list.add(parent[index]);
            index = index - parent[index];
        }
        return list;
    }
}
