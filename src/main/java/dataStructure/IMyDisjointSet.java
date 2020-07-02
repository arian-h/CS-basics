package dataStructure;

/**
 * Disjoint sets
 * @param <T>
 */
public interface IMyDisjointSet<T> {

    /**
     * Unions sets of two elements
     * @param t1 first element
     * @param t2 second element
     * @return False if given elements belong to the same set
     */
    boolean union(T t1, T t2);

    /**
     * Finds if two elements belong to the same set
     * @param t1 first element
     * @param t2 second element
     * @return True, if they belong to the same set
     */
    boolean sameSet(T t1, T t2);

    void makeSet(T t1);

    static <T> IMyDisjointSet<T> getInstance() {
        return new DisjointSet<>();
    }
}
