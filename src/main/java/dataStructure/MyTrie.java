package dataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTrie implements IMyTrie {

    private final Node root;

    public MyTrie() {
        this.root = new Node();
    }

    @Override
    public void insert(String s) {
        insert(s, 0, root);
    }

    @Override
    public void remove(String s) {
        remove(s, 0, root);
    }

    @Override
    public boolean contains(String s) {
        Node current = this.root;
        int index = 0;
        while (current != null && index < s.length()) {
            if (!current.children.containsKey(s.charAt(index))) {
                return false;
            }
            current = current.children.get(s.charAt(index));
            index++;
        }
        if (current == null) {
            return index == s.length();
        }
        return current.endNode;
    }

    @Override
    public List<String> getAll() {
        List<String> stringList = new ArrayList<>();
        getAll(root, stringList, new StringBuilder());
        return stringList;
    }

    private void getAll(Node node, List<String> stringList, StringBuilder string) {
        if (node == null) {
            return;
        }
        if (node.endNode) {
            stringList.add(string.toString());
        }
        for (char c: node.children.keySet()) {
            Node nextNode = node.children.get(c);
            string.append(c);
            getAll(nextNode, stringList, string);
            string.deleteCharAt(string.length() - 1);
        }
    }

    private boolean remove(String s, int index, Node node) {
        if (index == s.length()) {
            node.endNode = false;
            return node.children.size() == 0;
        }
        if (!node.children.containsKey(s.charAt(index))) {
            return false;
        }
        boolean removeChild = remove(s, index + 1, node.children.get(s.charAt(index)));
        if (removeChild) {
            node.children.remove(s.charAt(index));
            return node.children.size() == 0 && !node.endNode;
        }
        return false;
    }

    private void insert(String s, int index, Node node) {
        if (index == s.length()) {
            node.endNode = true;
        }
        if (index < s.length()) {
            char currentChar = s.charAt(index);
            node.children.putIfAbsent(currentChar, new Node());
            insert(s, index + 1, node.children.get(currentChar));
        }
    }

    private static class Node {
        Map<Character, Node> children;
        boolean endNode;
        Node() {
            this.children = new HashMap<>();
            this.endNode = false;
        }
    }
}
