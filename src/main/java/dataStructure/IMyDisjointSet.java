package dataStructure;

/**
 * Disjoint sets
 * @param <T>
 */
public interface IMyDisjointSet<T> {

    /**
     * Union sets of two nodes
     * @param t1 first element
     * @param t2 second element
     * @return False if given elements belong to a same set
     */
    boolean union(T t1, T t2);

    static <T> IMyDisjointSet<T> getInstance() {
        return MyDisjointSet.getInstance();
    }
}
