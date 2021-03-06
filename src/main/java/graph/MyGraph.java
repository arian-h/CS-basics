package graph;


import java.util.*;
import java.util.stream.Collectors;
import dataStructure.IMyDisjointSet;

public class MyGraph<T extends Comparable<T>> implements IMyGraph<T> {

    private IMyGraphNode<T> root;
    private final Map<IMyGraphNode<T>, Integer> fwNodeToIndex;
    private Integer[][] fwDistance;
    private int articulationPointTime;
    private int bridgeTime;
    private final Set<IMyGraphNode<T>> articulationPoints;
    private final Set<IMyGraphEdge<T>> bridges;
    private final Mode mode;
    private final Map<T, IMyGraphNode<T>> nodes;

    private MyGraph(Mode mode) {
        this.fwDistance = null;
        this.fwNodeToIndex = new HashMap<>();
        this.root = null;
        this.articulationPointTime = 0;
        this.articulationPoints = new HashSet<>();
        this.bridgeTime = 0;
        this.bridges = new HashSet<>();
        this.mode = mode;
        this.nodes = new HashMap<>();
    }

    static <T extends Comparable<T>> IMyGraph<T> getInstance(Mode mode) {
        return new MyGraph<>(mode);
    }

    @Override
    public void setRoot(IMyGraphNode<T> node) {
        this.root = node;
    }

    @Override
    public IMyGraphNode<T> getRoot() {
        return this.root;
    }

    @Override
    public Map<T, Integer> dfs() {
        return dfs_visit(this.root).entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey().getValue(), Map.Entry::getValue));
    }


    private Map<IMyGraphNode<T>, Integer> dfs_visit_reverse(IMyGraphNode<T> node) {
        return dfs_visit(node, true);
    }

    private Map<IMyGraphNode<T>, Integer> dfs_visit(IMyGraphNode<T> node) {
        return dfs_visit(node, false);
    }

    /**
     * Time complexity: O(|V| + |E|): each node and edge is visited once
     * @return a map from node values to their nodes' visit order
     */
    private Map<IMyGraphNode<T>, Integer> dfs_visit(IMyGraphNode<T> node, boolean reverse) {
        if (node == null) {
            return Collections.emptyMap();
        }
        Map<IMyGraphNode<T>, Integer> finishTime = new HashMap<>();
        Map<IMyGraphNode<T>, Integer> color = new HashMap<>();
        Stack<IMyGraphNode<T>> toVisit = new Stack<>();
        // push root on the stack
        toVisit.push(node);
        int time = 0;
        while (!toVisit.isEmpty()) {
            // pop the node on top of the stack
            IMyGraphNode<T> current = toVisit.pop();
            if (!color.containsKey(current)) { // it's white
                toVisit.push(current); // push it back
                color.put(current, 1); // color it grey
                // explore the neighbors
                for (IMyGraphNode<T> neighbor: reverse ? current.getIncomingNeighbors() : current.getOutgoingNeighbors()) {
                    if (!color.containsKey(neighbor)) { // only push in white neighbors
                        toVisit.push(neighbor);
                    }
                }
                time++;
            } else if (color.get(current) == 1) { // it's grey
                color.put(current, 2); // make it black
                finishTime.put(current, time++);
            }
        }
        return finishTime;
    }

    /**
     * Time complexity: O(|V| + |E|): each node and edge is visited once
     * @return a list of node values in visit order
     */
    @Override
    public List<T> bfs() {
        if (this.root == null) {
            return Collections.emptyList();
        }
        Queue<IMyGraphNode<T>> toVisit = new LinkedList<>();
        Set<IMyGraphNode<T>> visited = new HashSet<>();
        toVisit.offer(this.root);
        List<T> bfs = new ArrayList<>();
        while (!toVisit.isEmpty()) {
            IMyGraphNode<T> current = toVisit.poll();
            bfs.add(current.getValue());
            for (IMyGraphNode<T> neighbor: current.getOutgoingNeighbors()) {
                if (!visited.contains(neighbor)) {
                    toVisit.offer(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return bfs;
    }

    @Override
    public List<IMyGraphNode<T>> getNodes() {
        return new ArrayList<>(nodes.values());
    }

    /**
     * Space complexity: O(n)
     * Time complexity: O(|V|^2). We could improve it to O(|V| + |E|log|V|) by using a Priority Queue, which is useful
     * in sparse matrix, where O|E| ~= O|V|
     * In that approach, first put all the nodes in a min priority queue, with infinite distance to source,
     * the source having 0 distance to itself.
     * Extract the min, update all of its neighbors with new distance (if they were not 'done' before)
     * It takes O(|V|) to create the priority queue, O(log|V|) to update each neighbor's distance in the queue
     * and maximum of O(|E|) updates
     * @return a map from each node to its distance to the initial node (root)
     */
    @Override
    public Map<T, Integer> dijkstra(IMyGraphNode<T> node) {
        if (node == null) {
            return Collections.emptyMap();
        }
        // initialize
        Map<IMyGraphNode<T>, Integer> distance = new HashMap<>();
        Set<IMyGraphNode<T>> done = new HashSet<>();
        distance.put(node, 0);
        IMyGraphNode<T> current = node;

        while (current != null) {
            IMyGraphNode<T> closest = null;
            for (IMyGraphNode<T> neighbor: current.getOutgoingNeighbors()) {
                if (!done.contains(neighbor)) {
                    closest = updateClosest(current, neighbor, closest, distance);
                }
            }
            done.add(current);
            current = closest;
        }
        return distance.entrySet().stream().collect(Collectors.toMap(e -> e.getKey().getValue(), Map.Entry::getValue));
    }

    /**
     * Time complexity: O(|V| + |E|) to do the dfs + O(|V|log|V|) to sort the nodes), in total: O(|E| + |V|log|V|)
     * @return a topologically sorted list of nodes
     */
    @Override
    public List<T> topologicalSort() {
        return topologicalSort_sub().stream().map(IMyGraphNode::getValue).collect(Collectors.toList());
    }

    private List<IMyGraphNode<T>> topologicalSort_sub() {
        Map<IMyGraphNode<T>, Integer> fTime = new HashMap<>();
        for (IMyGraphNode<T> node: nodes.values()) {
            if (!fTime.containsKey(node)) {
                fTime.putAll(dfs_visit(node));
            }
        }
        return fTime.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
    /**
     * Use DFS to find all the edges in the graph and sort them in increasing order based on their weights
     * (Time complexity: O(|V| + |E|) to find all the edges, O(|E| log|E|) to sort them based on the edge
     * Iterate over this ordered list of edges and add them to the MST, if it creates a cycle disregard it,
     * if it doesn't then consider it as one of the edges of the MST
     * (Time complexity: The 'Union by rank' implementation of the disjoint set takes O(nlogn) operations time-wise
     * to do o(n) operations, so the time complexity for O(|E|) operations will be O(|E|log|E|))
     *
     * Time complexity: Considering the two time complexity analysis mentioned above, the total time complexity will be
     * O(|V| + |E|log|V|)
     * @return The set of edges in the Minimum Spanning Tree
     */
    @Override
    public Set<IMyGraphEdge<T>> kruskal() {
        // first do a slightly-modified dfs to visit all nodes, and therefore edges
        Set<IMyGraphEdge<T>> sortedEdges = new TreeSet<>(getEdges());
        //iterate over ordered set of edges, and select the ones that connect two nodes from different subtrees
        IMyDisjointSet<IMyGraphNode<T>> disjointSet = IMyDisjointSet.getInstance();
        Set<IMyGraphEdge<T>> mst = new HashSet<>();
        for (IMyGraphEdge<T> edge: sortedEdges) {
            IMyGraphNode<T> n1 = edge.getEnds()[0];
            IMyGraphNode<T> n2 = edge.getEnds()[1];
            disjointSet.makeSet(n1);
            disjointSet.makeSet(n2);
            if (disjointSet.union(n1, n2)) {
                mst.add(edge);
            }
        }
        return mst;
    }

    /**
     * Lazily calculate the distance between two given nodes
     */
    @Override
    public Integer floydWarshallDistance(IMyGraphNode<T> source, IMyGraphNode<T> target) {
        // lazy calculation
        if (fwDistance == null) {
            computeFloydWarshallDistance();
        }
        return fwDistance[fwNodeToIndex.get(source)][fwNodeToIndex.get(target)];
    }

    @Override
    public Set<IMyGraphNode<T>> getArticulationPoints() {
        Map<IMyGraphNode<T>, Integer> startTime = new HashMap<>();
        Map<IMyGraphNode<T>, Integer> lowTime = new HashMap<>();
        findArticulationPoints(this.root, null, startTime, lowTime);
        return this.articulationPoints;
    }

    @Override
    public Set<IMyGraphEdge<T>> getBridges() {
        Map<IMyGraphNode<T>, Integer> startTime = new HashMap<>();
        Map<IMyGraphNode<T>, Integer> lowTime = new HashMap<>();
        findBridges(this.root, null, startTime, lowTime);
        return this.bridges;
    }

    @Override
    public IMyGraphNode<T> addNode(T value) {
        if (nodes.containsKey(value)) {
            throw new IllegalArgumentException("Node value must be unique within a graph");
        }
        IMyGraphNode<T> node = IMyGraphNode.getInstance(value);
        this.nodes.put(value, node);
        return node;
    }

    @Override
    public IMyGraphNode<T> getNode(T value) {
        if (!nodes.containsKey(value)) {
            throw new IllegalArgumentException("Node doesn't exist in graph");
        }
        return nodes.get(value);
    }

    @Override
    public IMyGraphEdge<T> addEdge(IMyGraphNode<T> source, IMyGraphNode<T> target) {
        return addEdge(source, target, 0);
    }

    @Override
    public void removeEdge(IMyGraphNode<T> source, IMyGraphNode<T> target) {
        source.removeOutgoingNeighbor(target);
        target.removeIncomingNeighbor(source);
        if (mode == Mode.UNDIRECTED) {
            target.removeOutgoingNeighbor(source);
            source.removeIncomingNeighbor(target);
        }
    }

    @Override
    public IMyGraphEdge<T> addEdge(IMyGraphNode<T> source, IMyGraphNode<T> target, int weight) {
        if (!nodes.containsValue(source) || !nodes.containsValue(target)) {
            throw new IllegalArgumentException("source or target node does not exist in the graph");
        }
        if (this.mode == Mode.UNDIRECTED) {
            source.addIncomingNeighbor(target, weight);
            target.addOutgoingNeighbor(source, weight);
        }
        source.addOutgoingNeighbor(target, weight);
        target.addIncomingNeighbor(source, weight);
        return IMyGraphEdge.getInstance(source, target, mode);
    }

    /**
     * This algorithm only uses the node degrees
     *
     * For undirected graphs:
     *  all nodes have even degrees, except maybe for 2 nodes, that could be odd
     * For directed graphs:
     *  all vertices have equal number of in-degree and out-degree OR
     *      there is one node vertex that has out-degree = in-degree + 1 and one
     *      which is in-degree = out-degree + 1
     */
    @Override
    public boolean hasEulerianPath() {
        if (!isConnected()) {
            return false;
        }
        int oddCount = 0;
        if (mode == Mode.DIRECTED) {
            for (IMyGraphNode<T> node: getNodes()) {
                if (node.getIncomingNeighbors().size() - node.getOutgoingNeighbors().size() == 1) {
                    oddCount++;
                } else if (node.getIncomingNeighbors().size() - node.getOutgoingNeighbors().size() == -1){
                    oddCount--;
                }
                if (oddCount > 2 || oddCount < -2) {
                    return false;
                }
            }
            return oddCount == 0;
        } else {
            for (IMyGraphNode<T> node: getNodes()) {
                if (node.getIncomingNeighbors().size() % 2 == 1) {
                    oddCount++;
                }
            }
            return oddCount == 0 || oddCount == 2;
        }
    }

    /**
     * This algorithm only uses the node degrees
     *
     * If graph is directed, check if in-degree == out-degree
     * if it is undirected, check if indegree (or outdegree) is even
     */
    @Override
    public boolean hasEulerianCircuit() {
        if (!isConnected()) {
            return false;
        }
        if (mode == Mode.DIRECTED) {
            for (IMyGraphNode<T> node: getNodes()) {
                if (node.getIncomingNeighbors().size() != node.getOutgoingNeighbors().size()) {
                    return false;
                }
            }
        } else {
            for (IMyGraphNode<T> node: getNodes()) {
                if (node.getIncomingNeighbors().size() % 2 == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * If the graph is undirected, then doing a single pass of dfs is enough
     *
     * If the graph is directed, first we need to clarify which type of connected-ness we mean:
     * Weakly connected: if we replace all edges with undirected edges, the graph is connected
     * Connected: For every pair of nodes, there is a way from one to another
     * Strongly connected: Each node, can reach all other nodes in the graph
     *
     * Time complexity: O(|V| + |E|) (dfs time)
     * Therefore in total: O(|E| + |V|)
     */
    @Override
    public boolean isConnected() {
        if (this.nodes.size() == 0 || this.root == null) {
            return true;
        }
        if (mode == Mode.UNDIRECTED) {
            return dfs_visit(this.root).size() == this.nodes.size();
        }
        // directed: do a dfs, and then a reverse dfs. If there is a node that was not visited, then graph is not
        // connected
        Map<IMyGraphNode<T>, Integer> visited = dfs_visit(this.root, true);
        visited.putAll(dfs_visit(this.root, true));
        return visited.size() == this.nodes.size();
    }

    /**
     * This is a greedy approximation to a problem that is known to be NP problem.
     * Though it guarantees that the minimum number of colorings is no more than d + 1 and no less than d.
     *
     * Time complexity: O(|V| + |E|) we visit each node once, and each edge twice
     * Space complexity: O(|V|) to keep the colors for nodes.
     *
     * There exists a better approximation called Welsh-Powell algorithm.
     * In that algorithm, first nodes are sorted based on their degree in decreasing order,
     * the first node in the list is selected, and colored to a minimum number and all the nodes in the list
     * that are not adjacent to that node are painted with the same color. This process continues for nodes without
     * color until all nodes are painted.
     *
     */
    @Override
    public int minColors() {
        List<IMyGraphNode<T>> nodes = this.nodes.values().stream().sorted((node1, node2) -> {
            if (this.mode == Mode.DIRECTED) {
                return node2.getOutgoingNeighbors().size() + node2.getIncomingNeighbors().size() -
                        node1.getOutgoingNeighbors().size() - node1.getIncomingNeighbors().size();
            } else {
                return node2.getOutgoingNeighbors().size() - node1.getOutgoingNeighbors().size();
            }
        }).collect(Collectors.toList());
        int c = 0;
        Map<IMyGraphNode<T>, Integer> color = new HashMap<>();
        for (int i = 0; i < nodes.size(); i++) {
            if (!color.containsKey(nodes.get(i))) {
                color.put(nodes.get(i), c);
                for (int j = i + 1; j < nodes.size(); j++) {
                    if (!nodes.get(i).getIncomingNeighbors().contains(nodes.get(j)) &&
                            !nodes.get(i).getOutgoingNeighbors().contains(nodes.get(j))) {
                        color.put(nodes.get(j), c);
                    }
                }
                c++;
            }
        }
        return c;
    }

    private int findMinimumColor(IMyGraphNode<T> node, Map<IMyGraphNode<T>, Integer> colors) {
        Set<Integer> neighborsColors = new HashSet<>();
        if (mode == Mode.DIRECTED) {
            for (IMyGraphNode<T> neighbor: node.getIncomingNeighbors()) {
                neighborsColors.add(colors.get(neighbor));
            }
        }
        for (IMyGraphNode<T> neighbor: node.getOutgoingNeighbors()) {
            neighborsColors.add(colors.get(neighbor));
        }
        int minColor = 1;
        while (neighborsColors.contains(minColor)) {
            minColor++;
        }
        return minColor;
    }

    /**
     * Sort the nodes in topological order, so we see the nodes in order of flow with the minimum back edges
     * Then do a dfs based on this order, but reverse the edges
     * Why? let's say the graph has N strongly connected components
     * if N = 1, then there is no issue with reversing the edges
     * if N > 1, then between two strongly connected components there is one edge, by topologically sorting the
     * nodes, and reversing the edges, we make sure to see the end of edge sooner than head of edge, and therefore
     * not leaking from one side to another
     * Time complexity:
     *  Topological sort: O(|V| + |E|log|V|)
     *  DFS: O(|V| + |E|)
     *      Total time: O(|V| + |E|log|V| + |E|) = O (|V| + |E|log|V|)
     * Space complexity: O(n)
     * @return list of Strongly Connected Components
     */
    @Override
    public List<Set<IMyGraphNode<T>>> scc() {
        List<IMyGraphNode<T>> nodes = topologicalSort_sub();
        Set<IMyGraphNode<T>> visited = new HashSet<>();
        List<Set<IMyGraphNode<T>>> sccs = new ArrayList<>();
        for (IMyGraphNode<T> node: nodes) {
            if (!visited.contains(node)) {
                Set<IMyGraphNode<T>> scc = new HashSet<>();
                Stack<IMyGraphNode<T>> toVisit = new Stack<>();
                toVisit.add(node);
                while (!toVisit.isEmpty()) {
                    IMyGraphNode<T> current = toVisit.pop();
                    visited.add(current);
                    scc.add(current);
                    for (IMyGraphNode<T> neighbor: current.getIncomingNeighbors()) {
                        if (!visited.contains(neighbor)) {
                            toVisit.push(neighbor);
                        }
                    }
                }
                sccs.add(scc);
            }
        }
        return sccs;
    }

    /**
     * This algorithm works for undirected graphs.
     * One very naive approach is to to do dfs for each node, and find the number of tree edges connected to
     * that node, if there are more than one, then it's an articulation point.
     *
     * For each node, find the lowest reachable discovery time (the minimum discovery time inside the subtree rooted
     * at that node).
     *
     * Iterate through the neighbors, ignore parent
     *  If it is a back edge, just update the low time: min(lowTime[node], discoveryTime[neighbor])
     *      (NOTE here that we only compare node's low time with the discovery time of an ancestor. We don't care what
     *      the low time is for that node, as it's not in the subtree rooted at current node)
     *
     *  Otherwise (If the edge you are looking at is a dfs tree edge) min(lowTime[node], lowTime[neighbor])
     *      recur over that node
     *      if it has a low time higher than or equal to node's discovery time, it means there is no back edge from
     *      that subtree to ancestors of the current node, which implies it depends on the current node, thus the node
     *      is articulation point
     *      Update the low time for the current node, using low time of the adjacent nodes
     *
     *  At the end check if current node is articulation node:
     *      if the current node is the root, and it has more than one dfs tree edge connected to it in dfs tree
     *      if the current node is not the root, but was detected as articulation point within the algorithm
     *
     * Time complexity: same as dfs O(V+E)
     */
    private void findArticulationPoints(IMyGraphNode<T> node, IMyGraphNode<T> parent,
                                        Map<IMyGraphNode<T>, Integer> startTime,
                                        Map<IMyGraphNode<T>, Integer> lowTime) {
        startTime.put(node, articulationPointTime);
        lowTime.put(node, articulationPointTime);
        articulationPointTime++;
        boolean isArticulationPoint = false;
        int childrenCount = 0;
        /*
            iterate over neighbors, ignore if neighbor is parent
         */
        for (IMyGraphNode<T> neighbor: node.getOutgoingNeighbors()) {
            if (neighbor != parent) { // ignore parent
                if (visitedBefore(neighbor, startTime)) { // back edge
                    /*
                        If it is a back edge, we compare the current node's low time and neighbor's discovery time.
                        Low time is the minimum discovery time of a node that the current node can reach.
                        We don't care what is the low time for the other end of the edge, because that's the furthest
                        we can go back in a subtree rooted at that point
                     */
                    lowTime.put(node, Math.min(lowTime.get(node), startTime.get(neighbor)));
                } else { // dfs tree edge
                    childrenCount++;
                    // recur on the dfs tree edge child (neighbor)
                    findArticulationPoints(neighbor, node, startTime, lowTime);
                    // the current node is an articulation point for a child that doesn't have a back edge
                    // to the ancestors of current node
                    if (lowTime.get(neighbor) >= startTime.get(node)) {
                        isArticulationPoint = true;
                    }
                    // update the current low time, with the low of the neighbors that are in the dfs subtree
                    // rooted at the current node
                    lowTime.put(node, Math.min(lowTime.get(node), lowTime.get(neighbor)));
                }
            }
        }
        if ((parent == null && childrenCount >= 2) || (parent != null && isArticulationPoint)) {
            articulationPoints.add(node);
        }
    }

    /**
     *
     * 0. To find bridges, use DFS
     * 1. To find bridges, we don't care about the finish time, we care about the discovery time. Why? because we
     * just care about the order of visits.
     * 2. This algorithm works for undirected graphs.
     * 3. (u,v) is a bridge, if it there is no way to get to v other than through this edge.
     * This means there is no back edge from v to any ancestor of u.
     *
     * The idea is same as articulation point, with minor modifications:
     * 1. The number of children doesn't matter, so it's not counted
     * 2. An edge is considered bridge, if it is a dfs tree edge, and the discovery time of its source is less than
     *    the low time of its target
     *
     * Basically, we update the "lowest reachable discovery time" for each node using dfs.
     * We ignore the edge that we came to this node through.
     * For each tree edge, we check the condition to see if it is a bridge.
     * Otherwise, we just update the discovery time
     *
     * To update the discovery time:
     *      Tree edge: min(lowestReachableDiscoveryTime[neighbor], lowestReachableDiscoveryTime[node])
     *      Back edge: min(discoveryTime[neighbor], lowestReachableDiscoveryTime[node])
     *
     * Time complexity: same as dfs O(V+E)
     */
    private void findBridges(IMyGraphNode<T> node, IMyGraphNode<T> parent, Map<IMyGraphNode<T>, Integer> discoveryTime,
                                        Map<IMyGraphNode<T>, Integer> lowestReachableDiscoveryTime) {
        discoveryTime.put(node, bridgeTime);
        lowestReachableDiscoveryTime.put(node, bridgeTime);
        bridgeTime++;
        for (IMyGraphNode<T> neighbor: node.getOutgoingNeighbors()) {
            if (!visitedBefore(neighbor, discoveryTime)) { // dfs tree edge
                findBridges(neighbor, node, discoveryTime, lowestReachableDiscoveryTime);
                lowestReachableDiscoveryTime.put(node,
                        Math.min(lowestReachableDiscoveryTime.get(node), lowestReachableDiscoveryTime.get(neighbor)));
                if (lowestReachableDiscoveryTime.get(neighbor) > discoveryTime.get(node)) {
                    bridges.add(IMyGraphEdge.getInstance(node, neighbor, mode));
                }
            } else if (neighbor != parent) { // back edge
                /*
                    We don't care what is the low time for the other end of the edge, because that node is the
                    furthest the node can reach
                 */
                lowestReachableDiscoveryTime.put(node,
                        Math.min(lowestReachableDiscoveryTime.get(node), discoveryTime.get(neighbor)));
            }
        }
    }

    private boolean visitedBefore(IMyGraphNode<T> node, Map<IMyGraphNode<T>, Integer> startTime) {
        return startTime.containsKey(node);
    }

    /*
     * A slightly modified version of the other dfs algorithm
     * Visit each node (pop out of stack), explore it neighbors, and put them in toVisit (stack) if they were
     * not in the stack before. Put its adjacent edges in set
     *
     * @return set of edges in the graph
     */
    private Set<IMyGraphEdge<T>> getEdges() {
        Stack<IMyGraphNode<T>> toVisit = new Stack<>();
        Set<IMyGraphNode<T>> visited = new HashSet<>();
        Set<IMyGraphEdge<T>> edges = new HashSet<>();

        //get set of all edges, put them in a tree, ordered by their weight
        toVisit.add(this.root);
        visited.add(this.root); // used to check if a given node is or was in the stack
        while (!toVisit.isEmpty()) {
            IMyGraphNode<T> current = toVisit.pop();
            for (IMyGraphNode<T> neighbor: current.getOutgoingNeighbors()) {
                if (!visited.contains(neighbor)) {
                    toVisit.push(neighbor);
                    visited.add(neighbor);
                }
                edges.add(IMyGraphEdge.getInstance(current, neighbor, mode));
            }
        }
        return edges;
    }

    private IMyGraphNode<T> updateClosest(IMyGraphNode<T> node, IMyGraphNode<T> neighbor, IMyGraphNode<T> closest,
                                          Map<IMyGraphNode<T>, Integer> distance) {
        if (!distance.containsKey(neighbor) || node.getWeight(neighbor) + distance.get(node) < distance.get(neighbor)) {
            distance.put(neighbor, node.getWeight(neighbor) + distance.get(node));
        }
        if (closest == null || distance.get(neighbor) < distance.get(closest)) {
            closest = neighbor;
        }
        return closest;
    }

    /**
     * Assume that distance(i, j, k) represents the distance between nodes i and j, using all the nodes in {1,...,k}
     * Recursively we can say: distance(i, j, k) = min( distance(i, j, k - 1), distance(i, k, k - 1) + distance(k, j, k - 1) )
     * This formula is the heart of the floyd warshall algorithm.
     *
     * To implement it, we take a matrix of size |V| * |V|, and initialize it with edge weights.
     * Then we increment k by one at each step. and follow the algorithm.
     *
     * The time complexity is: O(|V| ^ 3) and the space complexity is O(|V| ^ 2)
     *
     */
    private void computeFloydWarshallDistance() {
        // create a list of nodes
        List<IMyGraphNode<T>> nodes = new ArrayList<>(this.nodes.values());
        int v = nodes.size();
        // mem for the algorithm's dynamic programming solution
        Integer[][] distance = new Integer[v][v];
        // initialize the matrix and fill in the fwNodeToIndex
        for (int i = 0; i < v; i++) {
            fwNodeToIndex.put(nodes.get(i), i);
            for (int j = 0; j < v; j++) {
                if (i == j) {
                    distance[i][i] = 0;
                } else {
                    distance[i][j] = nodes.get(i).getWeight(nodes.get(j));
                }
            }
        }
        // loop over each pair, for V times
        for (int k = 0; k < v; k++) {
            for (int i = 0; i < v; i++) {
                for (int j = 0; j < v; j++) {
                    if (distance[i][j] == null) {
                        if (distance[i][k] != null && distance[k][j] != null) {
                            distance[i][j] = distance[i][k] + distance[k][j];
                        }
                    } else {
                        if (distance[i][k] != null && distance[k][j] != null) {
                            distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                        }
                    }
                }
            }
        }
        fwDistance = distance;
    }

}
