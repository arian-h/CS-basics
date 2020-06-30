package dataStructure;

import com.google.common.base.Preconditions;

import java.util.ArrayList;

public class MyStack<T> {

    private ArrayList<T> arr;

    public MyStack() {
        this.arr = new ArrayList<>();
    }

    public void push(T t) {
        this.arr.add(0, t);
    }

    public T pop() {
        Preconditions.checkArgument(!arr.isEmpty(), "Cannot pop an empty stack!");
        return this.arr.remove(0);
    }

    public T top() {
        Preconditions.checkArgument(!arr.isEmpty(), "No element in an empty stack");
        return this.arr.get(0);
    }

    public long size() {
        return this.arr.size();
    }

    public void clear() {
        this.arr.clear();
    }
}
