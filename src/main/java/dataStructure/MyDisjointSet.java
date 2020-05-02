package dataStructure;

import java.util.HashMap;
import java.util.Map;

/**
 * Union by rank implementation of the disjoint set
 * Union two elements by rank:
 *  Find the set which each element belongs to. Union their sets by putting the one with the lower rank under the
 *  one with higher rank. If they are on equal ranks, make one child of another, and increase rank of parent.
 *  To find parent, first find root. Put all node in the path from given node to root directly under root.
 *      (The last part is to guarantee the average case time complexity)
 * @param <K>
 */
public class MyDisjointSet<K> implements IMyDisjointSet<K> {

    private final Map<K, Integer> rank;
    private final Map<K, K> parents;

    public static <K> IMyDisjointSet<K> getInstance() {
        return new MyDisjointSet<>();
    }

    private MyDisjointSet() {
        rank = new HashMap<>();
        parents = new HashMap<>();
    }

    @Override
    public boolean union(K k1, K k2) {
        K root1 = find(k1);
        K root2 = find(k2);
        if (root1 == root2) {
            return false;
        }
        // if the given node is the root of the set itself or it's a new node, put the rank in the map
        if (root1 == k1) {
            rank.put(root1, 0);
        }
        if (root2 == k2) {
            rank.put(root2, 0);
        }
        // always make sure roo1 has a higher or equal rank to roo2
        if (rank.get(root1) < rank.get(root2)) {
            K temp = root1;
            root1 = root2;
            root2 = temp;
        }
        // make k1 a child of k2
        parents.put(root2, root1);
        if (rank.get(root1).equals(rank.get(root2))) {
            rank.put(root1, rank.get(root1) + 1);
        }
        return true;
    }

    /**
     * First fine the root, and then make all the nodes on the path from given node to root a direct child of root
     * @param k
     * @return root of the set
     */
    private K find(K k) {
        K root = k;
        while (parents.containsKey(root) && !parents.get(root).equals(root)) {
            root = parents.get(root);
        }
        while (k != root) {
            K parent = parents.get(k);
            if (parent != root) {
                parents.put(k, root);
            }
            k = parent;
        }
        return root;
    }
}
