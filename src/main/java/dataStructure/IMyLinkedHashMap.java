package dataStructure;

import javafx.util.Pair;

public interface IMyLinkedHashMap<K, V> extends Iterable<Pair<K, V>> {

    void put(K key, V value);

    V get(K key);

    boolean contains(K key);

    int size();

    V remove(K key);

    static <K, V> IMyLinkedHashMap<K, V> getInstance(int size) {
        return MyLinkedHashMap.getInstance(size);
    }
}

