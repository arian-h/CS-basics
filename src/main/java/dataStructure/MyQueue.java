package dataStructure;

import com.google.common.base.Preconditions;

/**
 * a limited size queue, for Integer
 */
public class MyQueue<T> {

    private int head;
    private int tail;
    private int size;
    private final int initSize;
    private final Object[] arr;

    public MyQueue(int initSize) {
        Preconditions.checkArgument(initSize > 0, "size must be equal to or larger than 0");
        this.head = 0;
        this.tail = 0;
        this.head = 0;
        this.initSize = initSize;
        this.arr = new Object[initSize];
    }

    public void offer(T t) {
        Preconditions.checkArgument(!isFull(), "queue is full");
        arr[tail] = t;
        this.tail = updateIndex(tail);
        this.size++;
    }

    public int updateIndex(int index) {
        return (index + 1) % this.initSize;
    }

    public boolean isFull() {
        return this.size == this.initSize;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    @SuppressWarnings("unchecked")
    public T poll() {
        Preconditions.checkArgument(!isEmpty(), "queue is empty");
        T result = (T) arr[head];
        this.head = updateIndex(this.head);
        this.size--;
        return result;
    }

    public String print() {
        if (isEmpty()) {
            return "";
        }
        int index = this.head;
        StringBuilder strbld = new StringBuilder();
        do {
            strbld.append(this.arr[index]).append(", ");
            index = updateIndex(index);
        } while (index != tail);
        return strbld.substring(0, strbld.length() - 2);
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        Preconditions.checkArgument(!isEmpty(), "queue is empty");
        return (T) this.arr[head];
    }

}
