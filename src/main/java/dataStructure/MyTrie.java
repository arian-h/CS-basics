package dataStructure;

import com.google.common.base.Preconditions;

import java.util.*;

public class MyTrie implements IMyTrie {

    private Node root;

    private MyTrie() {
        this.root = new Node();
    }

    public static IMyTrie getInstance() {
        return new MyTrie();
    }

    @Override
    public void insert(String s) {
        Preconditions.checkArgument(s != null, "cannot insert null");
        Node node = root;
        int index = 0;
        while (index < s.length() && node.map.containsKey(s.charAt(index))) {
            node = node.map.get(s.charAt(index));
            index++;
        }
        while (index < s.length()) {
            Node n = new Node();
            node.map.put(s.charAt(index), n);
            node = n;
            index++;
        }
        node.leaf = true;
    }

    @Override
    public void remove(String s) {
        Preconditions.checkArgument(s != null, "there is no null stored");
        Preconditions.checkArgument(contains(s), "string is not stored");
        remove(root, s, -1);
    }

    @Override
    public boolean contains(String s) {
        Preconditions.checkArgument(s != null, "cannot insert null");
        Node node = root;
        int index = 0;
        while (index < s.length() && node.map.containsKey(s.charAt(index))) {
            node = node.map.get(s.charAt(index));
            index++;
        }
        return node.leaf;
    }

    @Override
    public List<String> getAll() {
        List<String> list = new ArrayList<>();
        getAll_rec(root, new StringBuilder(), list);
        return Collections.unmodifiableList(list);
    }

    private boolean remove(Node node, String s, int index) {
        if (index >= s.length() - 1) {
            node.leaf = false;
        } else {
            if (remove(node.map.get(s.charAt(index + 1)), s, index + 1)) {
                node.map.remove(s.charAt(index + 1));
            }
        }
        return !node.leaf && node.map.size() == 0;
    }

    private void getAll_rec(Node n, StringBuilder strbld, List<String> list) {
        if (n.leaf) {
            list.add(strbld.toString());
        } else {
            for (char c: n.map.keySet()) {
                strbld.append(c);
                getAll_rec(n.map.get(c), strbld, list);
                strbld.deleteCharAt(strbld.length() - 1);
            }
        }
    }

    private static class Node {
        private Map<Character, Node> map;
        private boolean leaf;
        public Node() {
            this.map = new HashMap<>();
            this.leaf = false;
        }
    }
}
