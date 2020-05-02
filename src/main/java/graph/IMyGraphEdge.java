package graph;

public interface IMyGraphEdge<T extends Comparable<T>> extends Comparable<IMyGraphEdge<T>> {

    int getWeight();

    IMyGraphNode<T>[] getEnds();

    static <T extends Comparable<T>> IMyGraphEdge<T> getInstance(IMyGraphNode<T> e1, IMyGraphNode<T> e2, IMyGraph.Mode mode) {
        return MyGraphEdge.getInstance(e1, e2, mode);
    }

}
