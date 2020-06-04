package design.composite;

import java.util.ArrayList;
import java.util.List;

public class InternalNode implements Node {

    private final int val;
    private final List<Node> children;

    public InternalNode(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }

    @Override
    public void addChild(Node node) {
        this.children.add(node);
    }

    @Override
    public void removeChild(Node node) {
        this.children.remove(node);
    }

    @Override
    public int sum() {
        return this.children.stream().map(Node::sum).reduce(0, Integer::sum);
    }

    @Override
    public int getValue() {
        return val;
    }
}
