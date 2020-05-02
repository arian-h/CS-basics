package graph;

public class MyGraphEdge<T extends Comparable<T>> implements IMyGraphEdge<T> {

    final private IMyGraphNode<T> e1, e2;
    final private IMyGraph.Mode mode;

    private MyGraphEdge(IMyGraphNode<T> e1, IMyGraphNode<T> e2, IMyGraph.Mode mode) {
        this.e1 = e1;
        this.e2 = e2;
        this.mode = mode;
    }

    static <T extends Comparable<T>> IMyGraphEdge<T> getInstance(IMyGraphNode<T> e1, IMyGraphNode<T> e2, IMyGraph.Mode mode) {
        return new MyGraphEdge<>(e1, e2, mode);
    }

    @Override
    public int compareTo(IMyGraphEdge<T> edge) {
        return getWeight() - edge.getWeight();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MyGraphEdge)) {
            return false;
        }
        MyGraphEdge<T> edge = (MyGraphEdge<T>) o;
        boolean oneDirectionEquality = e1.equals(edge.e1) && e2.equals(edge.e2);
        if (mode == IMyGraph.Mode.DIRECTED) {
            return oneDirectionEquality;
        }
        return oneDirectionEquality || e2.equals(edge.e1) && e1.equals(edge.e2);
    }

    @Override
    public int hashCode() {
        String id;
        if (mode == IMyGraph.Mode.DIRECTED) {
            id = e1.getValue() + "" + e2.getValue();
        } else {
            String smallerId = e1.getValue().toString();
            String biggerId = e2.getValue().toString();
            if (biggerId.compareTo(smallerId) < 0) {
                String t = biggerId;
                biggerId = smallerId;
                smallerId = t;
            }
            id = smallerId + biggerId;
        }
        return id.hashCode();
    }

    @Override
    public int getWeight() {
        return e1.getWeight(e2);
    }

    @SuppressWarnings("unchecked")
    @Override
    public IMyGraphNode<T>[] getEnds() {
        IMyGraphNode<T>[] ends = new IMyGraphNode[2];
        ends[0] = e1;
        ends[1] = e2;
        return ends;
    }
}
