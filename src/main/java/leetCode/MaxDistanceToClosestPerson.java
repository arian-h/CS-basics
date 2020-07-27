package leetCode;

public class MaxDistanceToClosestPerson {
    /**
     * https://leetcode.com/problems/maximize-distance-to-closest-person/
     *
     * In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty.
     *
     * There is at least one empty seat, and at least one person sitting.
     *
     * Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
     *
     * Return that maximum distance to closest person.
     *
     * Example 1:
     *
     * Input: [1,0,0,0,1,0,1]
     * Output: 2
     * Explanation:
     * If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
     * If Alex sits in any other open seat, the closest person has distance 1.
     * Thus, the maximum distance to the closest person is 2.
     * Example 2:
     *
     * Input: [1,0,0,0]
     * Output: 3
     * Explanation:
     * If Alex sits in the last seat, the closest person is 3 seats away.
     * This is the maximum distance possible, so the answer is 3.
     */
    public static int maxDistToClosest(int[] seats) {
        int[] left = new int[seats.length];
        int last = -1;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                last = i;
            } else {
                if (last == -1) {
                    left[i] = -1;
                } else {
                    left[i] = i - last;
                }
            }
        }
        int[] right = new int[seats.length];
        last = -1;
        for (int i = seats.length - 1; i >= 0; i--) {
            if (seats[i] == 1) {
                last = i;
            } else {
                if (last == -1) {
                    right[i] = -1;
                } else {
                    right[i] = last - i;
                }
            }
        }
        int max = 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 0) {
                if (left[i] == -1) {
                    max = Math.max(max, right[i]);
                } else if (right[i] == -1) {
                    max = Math.max(max, left[i]);
                } else {
                    max = Math.max(max, Math.min(right[i], left[i]));
                }
            }
        }
        return max;
    }
}
