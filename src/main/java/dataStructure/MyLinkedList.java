package dataStructure;

import com.google.common.base.Preconditions;

public class MyLinkedList<T> {

    private Node<T> head, tail;
    private int size;

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void add(T t) {
        Node<T> node = new Node<>(t);
        if (this.tail != null) {
            this.tail.next = node;
        } else {
            this.head = node;
        }
        this.tail = node;
    }

    public T get(int index) {
        Preconditions.checkArgument(index >= 0, "index must be larger than or equal to 0");
        int i = 0;
        Node<T> p = this.head;
        while (i < index && p != null) {
            p = p.next;
            i++;
        }
        if (p == null) {
            throw new RuntimeException("index out of bound");
        }
        return p.value;
    }

    public T remove(int index) {
        Preconditions.checkArgument(index >= 0, "index must be larger than or equal to 0");
        int i = 0;
        Node<T> p = this.head;
        Node<T> prev = null;
        while (i < index && p != null) {
            prev = p;
            p = p.next;
            i++;
        }
        if (p == null) {
            throw new RuntimeException("index out of bound");
        }
        // if it's head
        if (prev == null) {
            this.head = p.next;
        } else {
            prev.next = p.next;
        }
        // if it's tail
        if (p.next == null) {
            this.tail = prev;
        }
        return p.value;
    }

    public boolean contains(T t) {
        Node<T> node = head;
        while (node != null && !node.value.equals(t)) {
            node = node.next;
        }
        return node != null;
    }

    public String print() {
        Node<T> p = this.head;
        if (p == null) {
            return "";
        }
        StringBuilder strbld = new StringBuilder();
        while (p != null) {
            strbld.append(p.value).append(", ");
            p = p.next;
        }
        return strbld.substring(0, strbld.length() - 2);
    }

    public int size() {
        int size = 0;
        Node<T> p = this.head;
        while (p != null) {
            size++;
            p = p.next;
        }
        return size;
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;
        public Node(T value) {
            this.value = value;
        }
    }
}
