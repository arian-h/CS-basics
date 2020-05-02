package graph;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IMyGraph<T extends Comparable<T>> {

    enum Mode {
        DIRECTED, UNDIRECTED;
    }

    static <T extends Comparable<T>> IMyGraph<T> getInstance(Mode mode) {
        return MyGraph.getInstance(mode);
    }

    void setRoot(IMyGraphNode<T> node);

    /**
     * Depth first search
     * @return a map from each node to the number of nodes visited or finished before it (finish time)
     */
    Map<T, Integer> dfs();

    /**
     * Breadth first search
     * @return an ordered list of visited nodes in the graph
     */
    List<T> bfs();

    /**
     * Finds the shortest path between root and all other reachable nodes
     * @return a map of nodes to their distance to the root
     */
    Map<T, Integer> dijkstra();

    /**
     * Sort nodes of a graph in topological order
     * @return nodes (that are reachable from source node) in topological order
     */
    List<T> topologicalSort();

    /**
     * Find minimum spanning tree over an undirected weighted graph
     * @return set of edges for a minimum spanning tree
     */
    Set<IMyGraphEdge<T>> kruskal();

    /**
     * Get list of all nodes reachable from the root
     * @return list of graph nodes
     */
    List<IMyGraphNode<T>> getNodes();

    /**
     * Calculate distance between source and target using floyd-warshall algorithm
     * Graph should be weighted, and
     * @param source source node
     * @param target target node
     * @return distance between source to target, null if there is not path
     */
    Integer floydWarshallDistance(IMyGraphNode<T> source, IMyGraphNode<T> target);

    /**
     * Find articulation points of an undirected graph
     * Articulation point is points of failures. In case they get removed, graphs gets disconnected
     * @return set of articulation graph nodes
     */
    Set<IMyGraphNode<T>> getArticulationPoints();

    /**
     * Find bridges of an undirected graph
     * Bridges are points of failures. In case they get removed, graphs gets disconnected
     * @return set of bridges
     */
    Set<IMyGraphEdge<T>> getBridges();

    /**
     * Return strongly connected components
     * @return list of sets of node, each set a strongly connected component
     */
    List<Set<IMyGraphNode<T>>> scc();

    /**
     * Add a node to graph
     * @param value must be unique within a graph
     * @return node object
     */
    IMyGraphNode<T> addNode(T value);

    /**
     * Get a node, given its value
     * @return node object
     */
    IMyGraphNode<T> getNode(T value);

    /**
     * Add a weighted edge
     * @param source source node
     * @param target target node
     * @return the edge
     */
    IMyGraphEdge<T> addEdge(IMyGraphNode<T> source, IMyGraphNode<T> target);

    /**
     * Remove the edge between source and target
     */
    void removeEdge(IMyGraphNode<T> source, IMyGraphNode<T> target);

    /**
     * Add an unweighted edge
     * @param source source node
     * @param target target node
     * @param weight weight
     * @return the edge
     */
    IMyGraphEdge<T> addEdge(IMyGraphNode<T> source, IMyGraphNode<T> target, int weight);

    /**
     * Whether graph has an Eulerian path
     */
    boolean hasEulerianPath();

    /**
     * Wether graph has an Eulerian circuit
     */
    boolean hasEulerianCircuit();

    /**
     * Whether graph is connected
     */
    boolean isConnected();

}
