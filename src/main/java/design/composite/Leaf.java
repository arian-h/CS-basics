package design.composite;

public class Leaf implements Node {

    private final int val;

    public Leaf(int val) {
        this.val = val;
    }

    @Override
    public void addChild(Node node) {}

    @Override
    public void removeChild(Node node) {}

    @Override
    public int sum() {
        return val;
    }

    @Override
    public int getValue() {
        return val;
    }
}
