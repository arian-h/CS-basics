package dataStructure;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MyPriorityQueueImpl<T> implements IMyPriorityQueue<T> {

    public enum Mode {
        MAX, MIN;
    }

    private List<T> arr;
    private Comparator<T> comparator;
    private Mode mode;

    private MyPriorityQueueImpl(Comparator<T> comparator, Mode mode) {
        this.arr = new ArrayList<>();
        this.comparator = comparator;
        this.mode = mode;
    }

    public static <T> IMyPriorityQueue<T> getInstance(Comparator<T> comparator, Mode mode) {
        return new MyPriorityQueueImpl<>(comparator, mode);
    }

    @Override
    public boolean contains(T t) {
        Preconditions.checkArgument(t != null, "argument cannot be null");
        return contains(t, 0);
    }

    @Override
    public T peek() {
        Preconditions.checkArgument(!isEmpty(), "Queue is empty");
        return arr.get(0);
    }

    @Override
    public T poll() {
        Preconditions.checkArgument(!isEmpty(), "Queue is empty");
        swap(0, arr.size() - 1);
        T t = removeLastChild();
        int index = 0;
        while (withinRange(index)) {
            int properElement = selectProperElement(index);
            if (properElement == index) {
                break;
            }
            swap(index, properElement);
            index = properElement;
        }
        return t;
    }

    @Override
    public void offer(T t) {
        arr.add(t);
        int index = arr.size() - 1;
        while (index > 0) {
            int parent = parent(index);
            if (mode.equals(Mode.MIN) ? comparator.compare(arr.get(index), arr.get(parent)) < 0 : comparator.compare(arr.get(index), arr.get(parent)) > 0) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public int size() {
        return this.arr.size();
    }

    @Override
    public T[] toArray() {
        return (T[]) arr.toArray();
    }

    /* Private methods */
    private int selectProperElement(int index) {
        int properElement = index;
        int leftChild = leftChild(index);
        int rightChild = rightChild(index);
        if (mode.equals(Mode.MIN)) {
            if (withinRange(leftChild) && comparator.compare(arr.get(leftChild), arr.get(properElement)) < 0) {
                properElement = leftChild;
            }
            if (withinRange(rightChild) && comparator.compare(arr.get(rightChild), arr.get(properElement)) < 0) {
                properElement = rightChild;
            }
        } else { // if (mode.equals(Mode.MAX))
            if (withinRange(leftChild) && comparator.compare(arr.get(leftChild), arr.get(properElement)) > 0) {
                properElement = leftChild;
            }
            if (withinRange(rightChild) && comparator.compare(arr.get(rightChild), arr.get(properElement)) > 0) {
                properElement = rightChild;
            }
        }
        return properElement;
    }

    private void swap(int index1, int index2) {
        T t = arr.get(index1);
        arr.set(index1, arr.get(index2));
        arr.set(index2, t);
    }

    private T removeLastChild() {
        T t = arr.get(arr.size() - 1);
        arr.remove(arr.size() - 1);
        return t;
    }

    private boolean contains(T t, int index) {
        if (!withinRange(index)) {
            return false;
        }
        if (arr.get(index).equals(t)) {
            return true;
        }
        if ((mode.equals(Mode.MIN) && comparator.compare(arr.get(index), t) < 0)
                || (mode.equals(Mode.MAX) && comparator.compare(arr.get(index), t) > 0)) {
            return false;
        }
        return contains(t, leftChild(index)) || contains(t, rightChild(index));
    }

    private int leftChild(int i) {
        int index = 2 * i + 1;
        return withinRange(index) ? index : -1;
    }

    private int rightChild(int i) {
        int index = 2 * i + 2;
        return withinRange(index) ? index : -1;
    }

    private boolean withinRange(int index) {
        return index < arr.size() && index >= 0;
    }

    private int parent(int i) {
        int index = Math.floorDiv((i - 1), 2);
        return withinRange(index) ? index : -1;
    }

}
