package dataStructure;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Iterator;

public class MyLinkedHashMap<K, V> implements IMyLinkedHashMap<K, V> {

    private final int cacheSize;
    private final HashMap<K, Node<K, V>> map;
    private Node<K, V> head;
    private Node<K, V> tail;

    private MyLinkedHashMap(int size) {
        this.cacheSize = size;
        this.map = new HashMap<>();
        this.head = null;
    }

    public static <K, V> IMyLinkedHashMap<K, V> getInstance(int size) {
        return new MyLinkedHashMap<>(size);
    }

    @Override
    public void put(K key, V value) {
        if (contains(key)) {
            removeNode(map.get(key));
        } else {
            if (exceedsSize()) {
                removeNode(head);
            }
        }
        Node<K, V> node = new Node<>(key, value);
        addToEnd(node);
        map.put(key, node);
    }

    @Override
    public V get(K key) {
        if (!map.containsKey(key)) {
            throw new RuntimeException("map doesn't contain key");
        }
        Node<K, V> node = map.get(key);
        removeNode(node);
        addToEnd(node);
        return node.pair.getValue();
    }


    @Override
    public boolean contains(K key) {
        return map.containsKey(key);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public V remove(K key) {
        if (!map.containsKey(key)) {
            throw new RuntimeException("map doesn't contain key");
        }
        Node<K, V> node = map.get(key);
        removeNode(node);
        return node.pair.getValue();
    }

    private boolean exceedsSize() {
        return cacheSize == size();
    }

    private void removeNode(Node<K, V> node) {
        map.remove(node.pair.getKey());
        if (!isHead(node)) {
            node.prev.next = node.next;
        } else {
            head = head.next;
        }
        if (!isTail(node)) {
            node.next.prev = node.prev;
        } else {
            tail = tail.prev;
        }
    }

    private boolean isTail(Node<K, V> node) {
        return node.next == null;
    }

    private boolean isHead(Node<K, V> node) {
        return node.prev == null;
    }

    private void addToEnd(Node<K, V> node) {
        if (tail != null) {
            tail.next = node;
            node.prev = tail;
        } else {
            head = node;
        }
        tail = node;
    }

    @Override
    public Iterator<Pair<K, V>> iterator() {
        return new MyLinkedHashMapIterator();
    }

    private class MyLinkedHashMapIterator implements Iterator<Pair<K, V>> {
        Node<K, V> node;

        public MyLinkedHashMapIterator() {
            this.node = head;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public Pair<K, V> next() {
            Node<K, V> t = node;
            node = node.next;
            return t.pair;
        }
    }

    private static class Node<K, V> {
        private Node<K, V> next;
        private Node<K, V> prev;
        private final Pair<K, V> pair;
        public Node(K key, V value) {
            this.pair = new Pair<>(key, value);
        }
    }
}
