package dataStructure;

import com.google.common.base.Preconditions;

import java.util.LinkedList;

public class MyHashMapImpl<K, V> implements IMyHashMap<K, V> {

    private final static int INIT_SIZE = 10;
    private final static int GROWTH_FACTOR = 2;

    private LinkedList<Node<K, V>>[] arr;
    private int filled;

    public MyHashMapImpl() {
        Preconditions.checkArgument(GROWTH_FACTOR > 1, "Growth factor must be larger than 1");
        Preconditions.checkArgument(INIT_SIZE > 0, "Initial size must be larger than 0");
        arr = new LinkedList[INIT_SIZE];
        filled = 0;
    }

    @Override
    public void put(K key, V value) {
        growArray();
        int hash = hash(key);
        if (arr[hash] == null) {
            arr[hash] = new LinkedList<>();
        }
        Node<K, V> node = getNode(key);
        if (node == null) {
            arr[hash].add(new Node<>(key, value));
            filled++;
        } else {
            node.value = value;
        }
    }

    @Override
    public V get(K key) {
        Node<K, V> node = getNode(key);
        if (node == null) {
            throw new RuntimeException("key doesn't exist in map");
        } else {
            return node.value;
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return filled;
    }

    @Override
    public boolean contains(K k) {
        return getNode(k) != null;
    }

    @Override
    public void remove(K k) {
        if (contains(k)) {
            int hash = hash(k);
            int index = 0;
            while (index < arr[hash].size() && !arr[hash].get(index).key.equals(k)) {
                index++;
            }
            if (index < arr[hash].size()) {
                arr[hash].remove(index);
                filled--;
            }
        } else {
            throw new RuntimeException("key doesn't exist in map");
        }
    }

    private Node<K, V> getNode(K key) {
        LinkedList<Node<K, V>> list = arr[hash(key)];
        if (list != null) {
            for (Node<K, V> node: list) {
                if (node.key.equals(key)) {
                    return node;
                }
            }
        }
        return null;
    }

    private int hash(K key) {
        return hash(key, arr.length);
    }

    private int hash(K key, int size) {
        return key.hashCode() % size;
    }

    private static class Node<K, V> {
        private K key;
        private V value;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private void growArray() {
        if (Math.pow(filled, 2) > this.arr.length) {
            LinkedList<Node<K, V>>[] newArr = new LinkedList[2 * arr.length];
            for (LinkedList<Node<K, V>> list: arr) {
                if (list != null) {
                    for (Node<K, V> node: list) {
                        int hash = hash(node.key, newArr.length);
                        if (newArr[hash] == null) {
                            newArr[hash] = new LinkedList<>();
                        }
                        newArr[hash].add(new Node<>(node.key, node.value));
                    }
                }
            }
            this.arr = newArr;
        }
    }
}
