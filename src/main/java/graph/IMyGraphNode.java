package graph;

import java.util.List;

public interface IMyGraphNode<T extends Comparable<T>> {

    static <T extends Comparable<T>> IMyGraphNode<T> getInstance(T value) {
        return MyGraphNode.getInstance(value);
    }

    void addOutgoingNeighbor(IMyGraphNode<T> neighbor, int weight);
    void addIncomingNeighbor(IMyGraphNode<T> neighbor, int weight);

    Integer getWeight(IMyGraphNode<T> neighbor);
    T getValue();

    List<IMyGraphNode<T>> getOutgoingNeighbors();
    List<IMyGraphNode<T>> getIncomingNeighbors();
    void removeOutgoingNeighbor(IMyGraphNode<T> neighbor);
    void removeIncomingNeighbor(IMyGraphNode<T> neighbor);

}
