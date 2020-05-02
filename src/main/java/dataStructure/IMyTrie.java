package dataStructure;

import java.util.List;

public interface IMyTrie {
    void insert(String s);
    void remove(String s);
    boolean contains(String s);
    List<String> getAll();

    static IMyTrie getInstance() {
        return MyTrie.getInstance();
    }
}
