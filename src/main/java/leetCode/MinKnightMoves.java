package leetCode;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MinKnightMoves {

    /**
     * On an infinite chess board, there is a knight at (0,0)
     * what is the minimum number of moves to get from (0,0) to (x,y)
     *
     * Algorithm: This algorithm uses BFS. As 4 direction of the an infinite chess board are symmetric, we
     * map destination to the top right quadrant and search for it there (it reduces time and space by a constant factor)
     * We should take care about some points that on the way to get to them the knight
     * may need to go out of the top right quadrant by -1.
     *
     * @param x x coordinate of destination
     * @param y y coordination of destination
     * @return minimum number of moves from 0,0 to x,y
     */
    public static int minMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        Map<Pair<Integer, Integer>, Pair<Integer, Integer>> parent = new HashMap<>();
        Pair<Integer, Integer> destination = new Pair<>(x, y);
        Pair<Integer, Integer> origin = new Pair<>(0, 0);
        Queue<Pair<Integer, Integer>> toVisit = new LinkedList<>();
        visited.add(origin);
        parent.put(origin, null);
        toVisit.offer(origin);
        while (!toVisit.isEmpty()) {
            Pair<Integer, Integer> current = toVisit.poll();
            if (current.equals(destination)) {
                break;
            }
            for (Pair<Integer, Integer> next: getNextMoves(current)) {
                if (!visited.contains(next)) {
                    toVisit.offer(next);
                    parent.put(next, current);
                    visited.add(next);
                }
            }
        }
        int movesCount = 0;
        Pair<Integer, Integer> current = destination;
        while (parent.get(current) != null) {
            current = parent.get(current);
            movesCount++;
        }
        return movesCount;
    }

    private static List<Pair<Integer, Integer>> getNextMoves(Pair<Integer, Integer> square) {
        int x = square.getKey();
        int y = square.getValue();
        return Stream.of(
                new Pair<>(x + 2, y + 1),
                new Pair<>(x + 2, y - 1),
                new Pair<>(x + 1, y + 2),
                new Pair<>(x + 1, y - 2),
                new Pair<>(x - 2, y + 1),
                new Pair<>(x - 2, y - 1),
                new Pair<>(x - 1, y + 2),
                new Pair<>(x - 1, y - 2)
        ).filter(p -> p.getValue() >= -1 && p.getKey() >= -1).collect(Collectors.toList());
    }


    /**
     * This is a A* algorithm (AI) to find an approximate of the answer. It doesn't always return the optimal
     * answer, but it's a good approximation of it.
     *
     * @param x x coordinate of destination
     * @param y y coordination of destination
     * @return an approximation of minimum number of moves from 0,0 to x,y
     */
    public static int minMoves_AStar(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        Map<Pair<Integer, Integer>, Integer> step = new HashMap<>();
        Pair<Integer, Integer> destination = new Pair<>(x, y);
        Pair<Integer, Integer> origin = new Pair<>(0, 0);
        PriorityQueue<Pair<Integer, Integer>> toVisit = new PriorityQueue<>((p1, p2) ->
            3 * step.get(p1) + ManhattanDistance(p1, destination) - 3 * step.get(p2) - ManhattanDistance(p2, destination)
        );
        visited.add(origin);
        step.put(origin, 0);
        toVisit.offer(origin);
        while (!toVisit.isEmpty()) {
            Pair<Integer, Integer> current = toVisit.poll();
            if (current.equals(destination)) {
                break;
            }
            for (Pair<Integer, Integer> next: getNextMoves_AStar(current)) {
                if (!visited.contains(next)) {
                    step.put(next, step.get(current) + 1);
                    toVisit.offer(next);
                    visited.add(next);
                }
            }
        }
        return step.get(destination);
    }

    private static List<Pair<Integer, Integer>> getNextMoves_AStar(Pair<Integer, Integer> square) {
        int x = square.getKey();
        int y = square.getValue();
        return Arrays.asList(
                new Pair<>(x + 2, y + 1),
                new Pair<>(x + 2, y - 1),
                new Pair<>(x + 1, y + 2),
                new Pair<>(x + 1, y - 2),
                new Pair<>(x - 2, y + 1),
                new Pair<>(x - 2, y - 1),
                new Pair<>(x - 1, y + 2),
                new Pair<>(x - 1, y - 2)
        );
    }

    private static int ManhattanDistance(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
        return Math.abs(p1.getKey() - p2.getKey()) + Math.abs(p1.getValue() - p2.getValue());
    }

}
