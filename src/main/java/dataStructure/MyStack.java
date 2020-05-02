package dataStructure;

import com.google.common.base.Preconditions;

import java.util.ArrayList;

public class MyStack<T> {

    private ArrayList<T> arr;
    private int next;

    public MyStack() {
        this.next = 0;
        this.arr = new ArrayList<>();
    }

    public void push(T t) {
        if (next == arr.size()) {
            arr.add(null);
        }
        this.arr.set(next, t);
        next++;
    }

    public T pop() {
        Preconditions.checkArgument(next > 0, "Cannot pop an empty stack!");
        T result = this.arr.get(next - 1);
        next--;
        return result;
    }

    public T top() {
        Preconditions.checkArgument(next > 0, "No element in an empty stack");
        return this.arr.get(next - 1);
    }

    public long size() {
        return next;
    }

    public void clear() {
        this.next = 0;
    }
}
