package dataStructure;

import java.util.HashMap;
import java.util.Map;

/**
 * Union by rank implementation of the disjoint set
 * Union two elements by rank:
 *  Find the set that each element belongs to. Union their sets by putting the one with the lower rank under the
 *  other one. If they have equal ranks, choose one as a parent and increment its rank.
 *  To find parent, recursively set parent of an element to its parent's root. This makes all the nodes in
 *  the path from that element to the root, pointing to the same root. This in turn guarantees the
 *  average time complexity.
 * @param <T>
 */
public class DisjointSet<T> implements IMyDisjointSet<T> {

    private final Map<T, Integer> rank;
    private final Map<T, T> parent;

    public DisjointSet() {
        this.rank = new HashMap<>();
        this.parent = new HashMap<>();
    }

    @Override
    public boolean union(T t1, T t2) {
        if (notExists(t1)) {
            init(t1);
        }
        if (notExists(t2)) {
            init(t2);
        }
        T root1 = findRoot(t1);
        T root2 = findRoot(t2);
        if (root1 == root2) {
            return false;
        }
        // t1's rank should always be less than or equal to t2's rank to make things easier
        if (rank.get(t1) > rank.get(t2)) {
            T tmp = t1;
            t1 = t2;
            t2 = tmp;
        }
        if (rank.get(t1).equals(rank.get(t2))) {
            rank.put(t2, rank.get(t2) + 1);
        }
        parent.put(t1, parent.get(t2));
        return true;
    }

    @Override
    public boolean sameSet(T t1, T t2) {
        return findRoot(t1) == findRoot(t2);
    }

    private T findRoot(T t) {
        if (parent.get(t) == t) {
            return t;
        }
        T root = findRoot(parent.get(t));
        parent.put(t, root);
        return root;
    }

    private boolean notExists(T t) {
        return !parent.containsKey(t);
    }

    private void init(T t) {
        rank.put(t, 0);
        parent.put(t, t);
    }
}
