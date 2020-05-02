package dataStructure;

import java.util.Iterator;

public interface IMyDoublyLinkedList<T> extends  Iterable<T> {

    enum Direction {
        FORWARD, REVERSE;
    }

    void add(T t, Direction direction);

    boolean remove(T t);

    boolean contains(T t);

    boolean isEmpty();

    Iterator<T> iterator(Direction direction);

    static <T> IMyDoublyLinkedList<T> getInstance() {
        return MyDoublyLinkedList.getInstance();
    }
}
