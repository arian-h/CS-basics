package dataStructure;

import javafx.util.Pair;

public interface IMyKDTree {

    Pair<Integer, Integer> findNearest(Pair<Integer, Integer> point);

    static IMyKDTree getInstance(Pair<Integer, Integer>[] points) {
        return MyKDTree.getInstance(points);
    }
}
