package graph;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class MyGraphUT {

    @Test
    public void testDFS_Cyclic() {
        IMyGraph<Integer> graph = create_Undirected_Unweighted_Graph();
        Map<Integer, Integer> dfs = graph.dfs();
        Assert.assertEquals(new HashMap<Integer, Integer>() {
            {
                put(1, 15);
                put(2, 13);
                put(3, 14);
                put(4, 11);
                put(5, 10);
                put(6, 8);
                put(7, 9);
                put(8, 6);
            }
        }, dfs);
    }

    @Test
    public void testDFS() {
        IMyGraph<Integer> graph = create_Directed_Unweighted_Graph();
        Map<Integer, Integer> dfs = graph.dfs();
        Assert.assertEquals(new HashMap<Integer, Integer>() {
            {
                put(1, 9);
                put(3, 6);
                put(2, 8);
                put(5, 4);
                put(4, 5);
            }
        }, dfs);
    }

    @Test
    public void testBFS() {
        IMyGraph<Integer> graph = create_Directed_Unweighted_Graph();
        List<Integer> bfs = graph.bfs();
        Assert.assertEquals(new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
        }}, bfs);
    }

    @Test
    public void testTopologicalSort() {
        IMyGraph<Integer> graph = create_Directed_Unweighted_Graph();
        List<Integer> sortedList = graph.topologicalSort();
        Assert.assertEquals(new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
        }}, sortedList);
    }

    @Test
    public void testDijkstra() {
        IMyGraph<Integer> graph = create_Directed_Weighted_Graph();
        Map<Integer, Integer> shortestPath = graph.dijkstra();
        Assert.assertEquals(new HashMap<Integer, Integer>() {{
            put(1, 0);
            put(3, 3);
            put(2, 2);
            put(5, 106);
            put(4, 6);
        }}, shortestPath);
    }

    @Test
    public void testKruskal() {
        IMyGraph<Integer> graph = create_Undirected_Weighted_Graph();
        Set<IMyGraphEdge<Integer>> mst = graph.kruskal();
        Set<Pair<Integer, Integer>> actualEdges = new HashSet<>();
        for (IMyGraphEdge<Integer> edge: mst) {
            int smallerEnd = edge.getEnds()[0].getValue();
            int biggerEnd = edge.getEnds()[1].getValue();
            if (smallerEnd > biggerEnd) {
                int t = biggerEnd;
                biggerEnd = smallerEnd;
                smallerEnd = t;
            }
            actualEdges.add(new Pair(smallerEnd, biggerEnd));
        }
        Assert.assertEquals(new HashSet<Pair<Integer, Integer>>() {{
            add(new Pair<>(1, 2));
            add(new Pair<>(2, 3));
            add(new Pair<>(3, 4));
            add(new Pair<>(3, 5));
        }}, actualEdges);
    }

    @Test
    public void testFloydWarshall_Undirected() {
        IMyGraph<Integer> graph = IMyGraph.getInstance(IMyGraph.Mode.UNDIRECTED);
        IMyGraphNode<Integer> node1 = graph.addNode(1);
        IMyGraphNode<Integer> node2 = graph.addNode(2);
        IMyGraphNode<Integer> node3 = graph.addNode(3);
        IMyGraphNode<Integer> node4 = graph.addNode(4);
        IMyGraphNode<Integer> node5 = graph.addNode(5);
        graph.addEdge(node1, node2, 2);
        graph.addEdge(node1, node3, 6);
        graph.addEdge(node2, node3, 1);
        graph.addEdge(node2, node4, 8);
        graph.addEdge(node3, node4, 3);
        graph.addEdge(node4, node5, 100);
        graph.addEdge(node5, node3, 0);
        graph.setRoot(node1);

        Assert.assertEquals(Integer.valueOf(0), graph.floydWarshallDistance(node1, node1));
        Assert.assertEquals(Integer.valueOf(2), graph.floydWarshallDistance(node1, node2));
        Assert.assertEquals(Integer.valueOf(1), graph.floydWarshallDistance(node2, node5));
        Assert.assertEquals(Integer.valueOf(3), graph.floydWarshallDistance(node4, node5));
    }

    @Test
    public void testFloydWarshall_Directed() {
        IMyGraph<Integer> graph = IMyGraph.getInstance(IMyGraph.Mode.DIRECTED);
        IMyGraphNode<Integer> node1 = graph.addNode(1);
        IMyGraphNode<Integer> node2 = graph.addNode(2);
        IMyGraphNode<Integer> node3 = graph.addNode(3);
        IMyGraphNode<Integer> node4 = graph.addNode(4);
        IMyGraphNode<Integer> node5 = graph.addNode(5);
        graph.addEdge(node1, node2, 2);
        graph.addEdge(node1, node3, 6);
        graph.addEdge(node2, node3, 1);
        graph.addEdge(node2, node4, 8);
        graph.addEdge(node3, node4, 3);
        graph.addEdge(node4, node5, 100);
        graph.addEdge(node5, node3, 0);
        graph.setRoot(node1);

        Assert.assertEquals(Integer.valueOf(0), graph.floydWarshallDistance(node1, node1));
        Assert.assertEquals(Integer.valueOf(2), graph.floydWarshallDistance(node1, node2));
        Assert.assertEquals(Integer.valueOf(104), graph.floydWarshallDistance(node2, node5));
        Assert.assertEquals(Integer.valueOf(100), graph.floydWarshallDistance(node4, node5));
    }

    @Test
    public void testArticulationPoint_noArticulationPoint() {
        IMyGraph<Integer> graph = create_Undirected_Weighted_Graph();
        Set<IMyGraphNode<Integer>> articulationPoints = graph.getArticulationPoints();
        Assert.assertEquals(0, articulationPoints.size());
    }

    @Test
    public void testArticulationPoint() {
        IMyGraph<Integer> graph = create_Undirected_Unweighted_Graph();
        Set<IMyGraphNode<Integer>> articulationPoints = graph.getArticulationPoints();
        Set<Integer> articulationNodeValues = articulationPoints.stream().map(IMyGraphNode::getValue).collect(Collectors.toSet());
        Assert.assertEquals(new HashSet<Integer>() {{
            add(3);
            add(4);
            add(5);
            add(7);
        }}, articulationNodeValues);
    }

    @Test
    public void testGetBridges() {
        IMyGraph<Integer> graph = IMyGraph.getInstance(IMyGraph.Mode.UNDIRECTED);
        IMyGraphNode<Integer> node1 = graph.addNode(1);
        IMyGraphNode<Integer> node2 = graph.addNode(2);
        IMyGraphNode<Integer> node3 = graph.addNode(3);
        IMyGraphNode<Integer> node4 = graph.addNode(4);
        IMyGraphNode<Integer> node5 = graph.addNode(5);
        IMyGraphNode<Integer> node6 = graph.addNode(6);
        IMyGraphNode<Integer> node7 = graph.addNode(7);
        IMyGraphNode<Integer> node8 = graph.addNode(8);
        graph.addEdge(node1, node2);
        graph.addEdge(node1, node3);
        graph.addEdge(node2, node3);
        graph.addEdge(node3, node4);
        graph.addEdge(node4, node5);
        graph.addEdge(node5, node6);
        graph.addEdge(node5, node7);
        graph.addEdge(node6, node7);
        graph.addEdge(node7, node8);
        graph.setRoot(node1);
        Assert.assertEquals(new HashSet<IMyGraphEdge<Integer>>() {{
            add(IMyGraphEdge.getInstance(node4, node5, IMyGraph.Mode.UNDIRECTED));
            add(IMyGraphEdge.getInstance(node3, node4, IMyGraph.Mode.UNDIRECTED));
            add(IMyGraphEdge.getInstance(node7, node8, IMyGraph.Mode.UNDIRECTED));
        }}, graph.getBridges());
    }

    @Test
    public void testSCCS_directed() {
        Assert.assertEquals(Arrays.asList(
                new HashSet<IMyGraphNode<Integer>>() {{
                    add(IMyGraphNode.getInstance(1));
                }},
                new HashSet<IMyGraphNode<Integer>>() {{
                    add(IMyGraphNode.getInstance(2));
                }},
                new HashSet<IMyGraphNode<Integer>>() {{
                    add(IMyGraphNode.getInstance(3));
                    add(IMyGraphNode.getInstance(4));
                    add(IMyGraphNode.getInstance(5));
                }}
        ), create_Directed_Unweighted_Graph().scc());
    }

    @Test
    public void testSCCS_undirected() {
        Assert.assertEquals(1, create_Undirected_Weighted_Graph().scc().size());
    }

    @Test
    public void testEulerianPath_Undirected_noPath() {
        Assert.assertFalse(create_NonEulerian_Path_Undirected_Graph().hasEulerianPath());
    }

    @Test
    public void testEulerianPath_Undirected_withPath() {
        Assert.assertTrue(create_Eulerian_Path_Undirected_Graph().hasEulerianPath());
    }

    @Test
    public void testEulerianPath_Directed_withPath() {
        Assert.assertTrue(create_Eulerian_Path_Directed_Graph().hasEulerianPath());
    }

    @Test
    public void testEulerianPath_directed_noPath() {
        Assert.assertTrue(create_NonEulerian_Path_Directed_Graph().hasEulerianPath());
    }

    @Test
    public void testEulerianCircuit_undirected_noCircuit() {
        Assert.assertFalse(create_NonEulerian_Circuit_Undirected_Graph().hasEulerianCircuit());
    }

    @Test
    public void testEulerianCircuit_undirected_withCircuit() {
        Assert.assertTrue(create_Eulerian_Circuit_Undirected_Graph().hasEulerianCircuit());
    }

    @Test
    public void testEulerianCircuit_directed_withCircuit() {
        Assert.assertTrue(create_Eulerian_Circuit_Directed_Graph().hasEulerianCircuit());
    }

    @Test
    public void testEulerianCircuit_directed_noCircuit() {
        Assert.assertFalse(create_NonEulerian_Circuit_Directed_Graph().hasEulerianCircuit());
    }

    @Test
    public void testIsConnected_connected() {
        IMyGraph<Integer> graph = IMyGraph.getInstance(IMyGraph.Mode.DIRECTED);
        IMyGraphNode<Integer> node1 = graph.addNode(1);
        IMyGraphNode<Integer> node2 = graph.addNode(2);
        graph.addEdge(node2, node1);
        graph.setRoot(node1);
        Assert.assertTrue(graph.isConnected());
    }

    @Test
    public void testIsConnected_disconnected() {
        IMyGraph<Integer> graph = IMyGraph.getInstance(IMyGraph.Mode.DIRECTED);
        IMyGraphNode<Integer> node1 = graph.addNode(1);
        IMyGraphNode<Integer> node2 = graph.addNode(2);
        graph.addNode(3);
        graph.addEdge(node2, node1);
        graph.setRoot(node1);
        Assert.assertFalse(graph.isConnected());
    }

    @Test
    public void testMinColors_1() {
        IMyGraph<Integer> graph = create_Undirected_Weighted_Graph();
        int minColors = graph.minColors();
        Assert.assertTrue(minColors <= 4 && minColors >= 3);
    }

    @Test
    public void testMinColors_2() {
        IMyGraph<Integer> graph = create_Undirected_Unweighted_Graph();
        int minColors = graph.minColors();
        Assert.assertTrue(minColors <= 4 && minColors >= 3);
    }

    /*  Used astrix * instead of arrow pointing towards the closer node
                            5
                          /   *
                         *     \
                        3 ---->4
                        ^ *    ^
                        |   \  |
                        |    \ |
                        1------>2
     */
    private IMyGraph<Integer> create_Directed_Unweighted_Graph() {
        IMyGraph<Integer> graph = IMyGraph.getInstance(IMyGraph.Mode.DIRECTED);
        IMyGraphNode<Integer> node1 = graph.addNode(1);
        IMyGraphNode<Integer> node2 = graph.addNode(2);
        IMyGraphNode<Integer> node3 = graph.addNode(3);
        IMyGraphNode<Integer> node4 = graph.addNode(4);
        IMyGraphNode<Integer> node5 = graph.addNode(5);
        graph.addEdge(node1, node2);
        graph.addEdge(node1, node3);
        graph.addEdge(node2, node3);
        graph.addEdge(node2, node4);
        graph.addEdge(node3, node4);
        graph.addEdge(node4, node5);
        graph.addEdge(node5, node3);
        graph.setRoot(node1);
        return graph;
    }

    /*  Used astrix * instead of arrow pointing towards the closer node
                        5
                      /   *
                     *     \
                    3 ---->4
                    ^ *    ^
                    |   \  |
                    |    \ |
                    1------>2
    */
    private IMyGraph<Integer> create_Directed_Weighted_Graph() {
        IMyGraph<Integer> graph = IMyGraph.getInstance(IMyGraph.Mode.DIRECTED);
        IMyGraphNode<Integer> node1 = graph.addNode(1);
        IMyGraphNode<Integer> node2 = graph.addNode(2);
        IMyGraphNode<Integer> node3 = graph.addNode(3);
        IMyGraphNode<Integer> node4 = graph.addNode(4);
        IMyGraphNode<Integer> node5 = graph.addNode(5);
        graph.addEdge(node1, node2, 2);
        graph.addEdge(node1, node3, 6);
        graph.addEdge(node2, node3, 1);
        graph.addEdge(node2, node4, 8);
        graph.addEdge(node3, node4, 3);
        graph.addEdge(node4, node5, 100);
        graph.addEdge(node5, node3, 0);
        graph.setRoot(node1);
        return graph;
    }

    /*
            2                7--- 8
          /   \            /  \
         1-----3-----4----5----6
    */
    private IMyGraph<Integer> create_Undirected_Unweighted_Graph() {
        IMyGraph<Integer> graph = IMyGraph.getInstance(IMyGraph.Mode.UNDIRECTED);
        IMyGraphNode<Integer> node1 = graph.addNode(1);
        IMyGraphNode<Integer> node2 = graph.addNode(2);
        IMyGraphNode<Integer> node3 = graph.addNode(3);
        IMyGraphNode<Integer> node4 = graph.addNode(4);
        IMyGraphNode<Integer> node5 = graph.addNode(5);
        IMyGraphNode<Integer> node6 = graph.addNode(6);
        IMyGraphNode<Integer> node7 = graph.addNode(7);
        IMyGraphNode<Integer> node8 = graph.addNode(8);
        graph.addEdge(node1, node2);
        graph.addEdge(node1, node3);
        graph.addEdge(node2, node3);
        graph.addEdge(node3, node4);
        graph.addEdge(node4, node5);
        graph.addEdge(node5, node6);
        graph.addEdge(node5, node7);
        graph.addEdge(node6, node7);
        graph.addEdge(node7, node8);
        graph.setRoot(node1);
        return graph;
    }

    /*  Used astrix * instead of arrow pointing towards the closer node
                    5
                  /   \
                 /     \
                3 -----4
                | \    |
                |  \   |
                |    \ |
                1------2
    */
    private IMyGraph<Integer> create_Undirected_Weighted_Graph() {
        IMyGraph<Integer> graph = IMyGraph.getInstance(IMyGraph.Mode.UNDIRECTED);
        IMyGraphNode<Integer> node1 = graph.addNode(1);
        IMyGraphNode<Integer> node2 = graph.addNode(2);
        IMyGraphNode<Integer> node3 = graph.addNode(3);
        IMyGraphNode<Integer> node4 = graph.addNode(4);
        IMyGraphNode<Integer> node5 = graph.addNode(5);
        graph.addEdge(node1, node2, 2);
        graph.addEdge(node1, node3, 6);
        graph.addEdge(node2, node3, 1);
        graph.addEdge(node2, node4, 8);
        graph.addEdge(node3, node4, 3);
        graph.addEdge(node4, node5, 100);
        graph.addEdge(node5, node3, 0);
        graph.setRoot(node1);
        return graph;
    }

    private IMyGraph<Integer> create_NonEulerian_Circuit_Undirected_Graph() {
        IMyGraph<Integer> graph = create_Eulerian_Circuit_Undirected_Graph();
        graph.removeEdge(graph.getNode(1), graph.getNode(5));
        return graph;
    }

    private IMyGraph<Integer> create_Eulerian_Circuit_Undirected_Graph() {
        IMyGraph<Integer> graph = IMyGraph.getInstance(IMyGraph.Mode.UNDIRECTED);
        IMyGraphNode<Integer> node1 = graph.addNode(1);
        IMyGraphNode<Integer> node2 = graph.addNode(2);
        IMyGraphNode<Integer> node3 = graph.addNode(3);
        IMyGraphNode<Integer> node4 = graph.addNode(4);
        IMyGraphNode<Integer> node5 = graph.addNode(5);

        graph.addEdge(node1, node2);
        graph.addEdge(node1, node3);
        graph.addEdge(node1, node4);
        graph.addEdge(node1, node5);

        graph.addEdge(node2, node3);
        graph.addEdge(node2, node4);
        graph.addEdge(node2, node5);

        graph.addEdge(node3, node4);
        graph.addEdge(node3, node5);

        graph.addEdge(node4, node5);

        graph.setRoot(node1);
        return graph;
    }

    private IMyGraph<Integer> create_Eulerian_Circuit_Directed_Graph() {
        IMyGraph<Integer> graph = create_NonEulerian_Circuit_Directed_Graph();
        graph.removeEdge(graph.getNode(1), graph.getNode(3));
        return graph;
    }

    private IMyGraph<Integer> create_NonEulerian_Circuit_Directed_Graph() {
        IMyGraph<Integer> graph = IMyGraph.getInstance(IMyGraph.Mode.DIRECTED);
        IMyGraphNode<Integer> node1 = graph.addNode(1);
        IMyGraphNode<Integer> node2 = graph.addNode(2);
        IMyGraphNode<Integer> node3 = graph.addNode(3);

        graph.addEdge(node1, node2);
        graph.addEdge(node2, node3);
        graph.addEdge(node3, node1);
        graph.addEdge(node1, node3);

        graph.setRoot(node1);
        return graph;
    }

    private IMyGraph<Integer> create_NonEulerian_Path_Undirected_Graph() {
        IMyGraph<Integer> graph = IMyGraph.getInstance(IMyGraph.Mode.UNDIRECTED);
        IMyGraphNode<Integer> node1 = graph.addNode(1);
        IMyGraphNode<Integer> node2 = graph.addNode(2);
        IMyGraphNode<Integer> node3 = graph.addNode(3);
        IMyGraphNode<Integer> node4 = graph.addNode(4);
        IMyGraphNode<Integer> node5 = graph.addNode(5);
        graph.addEdge(node1, node2);
        graph.addEdge(node2, node3);
        graph.addEdge(node3, node4);
        graph.addEdge(node4, node5);
        graph.addEdge(node2, node5);
        graph.addEdge(node3, node5);
        graph.addEdge(node1, node4);
        graph.setRoot(node1);
        return graph;
    }

    private IMyGraph<Integer> create_Eulerian_Path_Undirected_Graph() {
        IMyGraph<Integer> graph = create_NonEulerian_Path_Undirected_Graph();
        graph.removeEdge(graph.getNode(2), graph.getNode(3));
        return graph;
    }

    private IMyGraph<Integer> create_Eulerian_Path_Directed_Graph() {
        IMyGraph<Integer> graph = IMyGraph.getInstance(IMyGraph.Mode.DIRECTED);
        IMyGraphNode<Integer> node1 = graph.addNode(1);
        IMyGraphNode<Integer> node2 = graph.addNode(2);
        IMyGraphNode<Integer> node3 = graph.addNode(3);
        IMyGraphNode<Integer> node4 = graph.addNode(4);
        graph.addEdge(node1, node2);
        graph.addEdge(node2, node3);
        graph.addEdge(node3, node1);
        graph.addEdge(node3, node4);
        graph.addEdge(node4, node1);
        graph.setRoot(node1);
        return graph;
    }

    private IMyGraph<Integer> create_NonEulerian_Path_Directed_Graph() {
        IMyGraph<Integer> graph = create_Eulerian_Path_Directed_Graph();
        graph.removeEdge(graph.getNode(1), graph.getNode(4));
        return graph;
    }
}
