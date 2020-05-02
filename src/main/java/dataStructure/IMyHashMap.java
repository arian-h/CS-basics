package dataStructure;

public interface IMyHashMap<K, V> {
    void put(K key, V value);
    V get(K key);
    boolean isEmpty();
    int size();
    boolean contains(K k);
    void remove(K k);
}
