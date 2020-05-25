package leetCode;

public class GasStation {

    /**
     * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
     *
     * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its
     * next station (i+1). You begin the journey with an empty tank at one of the gas stations.
     *
     * Return the starting gas station's index if you can travel around the circuit once in the clockwise direction,
     * otherwise return -1.
     *
     * Algorithm:
     * Each link has an effective value, the gas provided at its origin station minus the cost of the road between
     * origin and destination.
     * If the total sum of the link values is less than 0, it means it's not possible to visit all stations,
     * regardless of where the journey begins. So the answer is -1.
     *
     * Otherwise, iterate through nodes, and remove stations from possible start station options.
     *
     * How ? Iterate through the stations and assume that each is the starting station.
     * Sum up the links until it gets back to point 0, or it is less than 0.
     * If it successfully gets to station 0, then the selected station is the answer. Otherwise, not that station
     * neither any station in between could be the starting station.
     *
     * Reason 1: (this reason only shows why the stations in between the assumed start point and the point where
     * tank is negative can be ignored)
     * Why? Because when the car gets to a station with non-negative tank, the path so far is actually contributing,
     * and when later it gets to negative, it means the stations in between could get to negative sooner, if that
     * contribution was not made, so definitely they are not the answer.
     *
     * Reason 2:
     * We can also prove that there is no station after station 0, that the car cannot get to.
     * How? let's assume that there is a station k, that starting from S (the answer found) cannot get to.
     * This means that the sum of link values from 0 to k is negative, in a way that if we sum it up with sum of
     * link values from S to 0, it goes to negative. This is not possible, why? because we know the sum of all links
     * for whole cycle is non-negative. if S -> 0 and 0 -> k is negative, then k -> S would be non-negative, which means
     * S had to be k, which is wrong.
     *
     * @param gas how much gas each station has
     * @param cost what is the cost (required gas) of going from each station, to the next one
     * @return Station that if the car starts from it, can visit all stations and get back to the start point.
     */
    public static int getStation(int[] gas, int[] cost) {
        int total = 0;
        int current = 0;
        int n = gas.length;
        int start = 0;
        for (int i = 0; i < n; i++) {
            current += gas[i] - cost[i];
            total += gas[i] - cost[i];
            if (current < 0) {
                current = 0;
                start = i + 1;
            }
        }
        return total >= 0 ? start : -1;
    }

}
