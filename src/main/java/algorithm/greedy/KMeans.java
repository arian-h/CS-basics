package algorithm.greedy;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class KMeans {

    private final static double EPSILON = 0.001;
    private final static int MAX_STEPS = 1000;

    /**
     * K-means algorithm, to cluster given nodes
     * Initialize the centers with random points in the plane
     * Until the sum of distance between points and the center of clusters they belong to, doesn't improve, do the following:
     * 1. For each point, find the cluster it belongs to (closest center point)
     * 2. Update center of each cluster to the mean of the points that belong to that cluster
     *
     * Note that this is an approximation problem, and it needs more care in order not to get in to an infinite loop
     * (for example, making sure that no two random centers are same)
     *
     * The time complexity is O(N * d * k) for a fixed number of steps, where N is number of points to cluster, k
     * is the number of clusters, and d is the dimensionality of the space that those points belong to
     *
     * @param points points to cluster
     * @param count the number of requested clusters
     * @return list of cluster center points
     */
    public static List<Point> findCenters(List<Point> points, int count) {
        checkArgs(points, count);
        int min_x = getMin(points.stream().map(p -> p.x).collect(Collectors.toList()));
        int max_x = getMax(points.stream().map(p -> p.x).collect(Collectors.toList()));
        int min_y = getMin(points.stream().map(p -> p.y).collect(Collectors.toList()));
        int max_y = getMax(points.stream().map(p -> p.y).collect(Collectors.toList()));
        List<Point> centers = generateRandomCenters(min_x, max_x, min_y, max_y, count);
        double prevCoherence = Double.MAX_VALUE;
        int step = 0;
        while (true) {
            Map<Point, List<Point>> clusters = new HashMap<>();
            for (Point point: points) {
                Point closest = findClosest(point, centers);
                clusters.putIfAbsent(closest, new ArrayList<>());
                clusters.get(closest).add(point);
            }
            centers = clusters.values().stream().map(KMeans::findCenter).collect(Collectors.toList());
            double coherence = coherence(clusters);
            if (Math.abs(coherence - prevCoherence) < EPSILON && step == MAX_STEPS) {
                break;
            }
            step++;
            prevCoherence = coherence;
        }
        return centers;
    }

    private static double coherence(Map<Point, List<Point>> clusters) {
        int d = 0;
        for (Point center: clusters.keySet()) {
            for (Point point: clusters.get(center)) {
                d += distance(center, point);
            }
        }
        return d;
    }

    private static Point findCenter(List<Point> points) {
        return new Point(points.stream().map(point -> point.x).reduce(0, Integer::sum) / points.size(),
                points.stream().map(point -> point.y).reduce(0, Integer::sum) / points.size());
    }

    private static Point findClosest(Point point, List<Point> points) {
        Point closest = null;
        for (Point center: points) {
            if (closest == null || distance(center, point) < distance(closest, point)) {
                closest = center;
            }
        }
        return closest;
    }

    private static double distance(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    private static void checkArgs(List<Point> points, int count) {
        Preconditions.checkArgument(points != null);
        Preconditions.checkArgument(count > 0);
        points = points.stream().filter(Objects::nonNull).collect(Collectors.toList());
        Preconditions.checkArgument(points.size() >= count);
    }

    private static List<Point> generateRandomCenters(int min_x, int max_x, int min_y, int max_y, int count) {
        Random random = new Random();
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            points.add(new Point(random.nextInt(max_x - min_x + 1) + min_x, random.nextInt(max_y - min_y + 1) + min_y));
        }
        return points;
    }

    private static int getMin(List<Integer> points) {
        return points.stream().min(Comparator.comparingInt(p -> p)).get();
    }

    private static int getMax(List<Integer> points) {
        return points.stream().max(Comparator.comparingInt(p -> p)).get();
    }

    public static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Point)) {
                return false;
            }
            return ((Point) other).x == x && ((Point) other).y == y;
        }
    }
}
