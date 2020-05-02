package dataStructure;

public interface IMyPriorityQueue<T> {
    boolean contains(T t);
    T peek();
    T poll();
    void offer(T t);
    boolean isEmpty();
    int size();
    T[] toArray();
}
