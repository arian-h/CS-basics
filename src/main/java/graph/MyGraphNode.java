package graph;

import java.util.*;

public class MyGraphNode<T extends Comparable<T>> implements IMyGraphNode<T> {
    private final T value;
    Map<IMyGraphNode<T>, Integer> incomingEdgesNeighbors;
    Map<IMyGraphNode<T>, Integer> outgoingEdgesNeighbors;

    private MyGraphNode(T value) {
        this.value = value;
        this.incomingEdgesNeighbors = new HashMap<>();
        this.outgoingEdgesNeighbors = new HashMap<>();
    }

    static <T extends Comparable<T>> IMyGraphNode<T> getInstance(T value) {
        return new MyGraphNode<>(value);
    }

    @Override
    public void addOutgoingNeighbor(IMyGraphNode<T> neighbor, int weight) {
        if (hasNeighbor(neighbor)) {
            return;
        }
        this.outgoingEdgesNeighbors.put(neighbor, weight);
    }

    @Override
    public void addIncomingNeighbor(IMyGraphNode<T> neighbor, int weight) {
        if (this.incomingEdgesNeighbors.containsKey(neighbor)) {
            return;
        }
        this.incomingEdgesNeighbors.put(neighbor, weight);
    }

    @Override
    public List<IMyGraphNode<T>> getIncomingNeighbors() {
        List<IMyGraphNode<T>> neighbors = new ArrayList<>(this.incomingEdgesNeighbors.keySet());
        /*
        Do not consider this in the time complexity analysis. This sorting is not necessary. By sorting the outcome
        of the algorithms will be deterministic, making testing easier.
         */
        neighbors.sort(Comparator.comparing(IMyGraphNode::getValue));
        return neighbors;
    }

    @Override
    public void removeOutgoingNeighbor(IMyGraphNode<T> neighbor) {
        this.outgoingEdgesNeighbors.remove(neighbor);
    }

    @Override
    public void removeIncomingNeighbor(IMyGraphNode<T> neighbor) {
        this.incomingEdgesNeighbors.remove(neighbor);
    }

    @Override
    public Integer getWeight(IMyGraphNode<T> neighbor) {
        return this.outgoingEdgesNeighbors.get(neighbor);
    }

    @Override
    public List<IMyGraphNode<T>> getOutgoingNeighbors() {
        List<IMyGraphNode<T>> neighbors = new ArrayList<>(this.outgoingEdgesNeighbors.keySet());
        /*
        Do not consider this in the time complexity analysis. This sorting is not necessary. By sorting the outcome
        of the algorithms will be deterministic, making testing easier.
         */
        neighbors.sort(Comparator.comparing(IMyGraphNode::getValue));
        return neighbors;
    }

    @Override
    public T getValue() {
        return this.value;
    }

    private boolean hasNeighbor(IMyGraphNode<T> node) {
        return outgoingEdgesNeighbors.containsKey(node);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof MyGraphNode)) {
            return false;
        }
        MyGraphNode<T> node = (MyGraphNode<T>) o;
        return getValue().equals(node.getValue());
    }

    @Override
    public int hashCode() {
        return getValue().hashCode();
    }
}