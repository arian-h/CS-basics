package algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class FFCache {


    /**
     * A greedy solution to the offline caching problem. We have the list of requested elements, and cache size at
     * hand, we would like to get a list of elements that are evicted in response to each request.
     *
     * This algorithm results optimum solution. If cache is full and it misses the request, algorithm choose the element
     * in cache that is requested farthest.
     *
     * Time complexity for this implementation is: O(N * M * T), N: length of requests, M: cache size, T: number
     * of unique elements
     *
     * Space complexity: O(N)
     *
     * @param size
     * @param requests
     * @param <T>
     * @return
     */
    public static <T> List<T> getEvictions(int size, T[] requests) {
        Map<T, Queue<Integer>> elementLocations = new HashMap<>();
        for (int i = 0; i < requests.length; i++) {
            elementLocations.putIfAbsent(requests[i], new LinkedList<>());
            elementLocations.get(requests[i]).offer(i);
        }
        Set<T> cacheMem = new HashSet<>();
        List<T> evictions = new ArrayList<>();
        for (T request: requests) {
            elementLocations.get(request).remove();
            if (isCacheFull(cacheMem, size)) {
                if (cacheMem.contains(request)) { // cache hit
                    evictions.add(null);
                } else { // cache miss, replace an element
                    T t = findElementToEvict(elementLocations, cacheMem);
                    cacheMem.remove(t);
                    cacheMem.add(request);
                    evictions.add(t);
                }
            } else { // cache is not full yet, no need for eviction
                evictions.add(null);
                cacheMem.add(request);
            }
        }
        return evictions;
    }

    private static <T> T findElementToEvict(Map<T, Queue<Integer>> elementLocations, Set<T> cacheMem) {
        T furthest = null;
        int location = -1;
        for (T element: cacheMem) {
            if (elementLocations.get(element).size() == 0) {
                return element;
            } else {
                if (elementLocations.get(element).peek() > location) {
                    location = elementLocations.get(element).peek();
                    furthest = element;
                }
            }
        }
        return furthest;
    }

    private static <T> boolean isCacheFull(Set<T> cache, int size) {
        return cache.size() >= size;
    }
}
