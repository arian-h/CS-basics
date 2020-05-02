package dataStructure;

import java.util.Comparator;
import java.util.Iterator;

public interface IMyBinarySearchTree<T> extends Iterable<T> {
    void build(T[] arr);
    void insert(T t);
    void remove(T t);
    boolean contains(T t);
    Iterator<T> iterator();
    void clear();
    boolean isEmpty();
    int size();
    static <T> IMyBinarySearchTree<T> getInstance(Comparator<T> comparator) {
        return MyBinarySearchTree.getInstance(comparator);
    }
}
