package PracticeQuestions;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Q0871 {
    public static void main(String[] args) {
        Q0871 obj = new Q0871();
        System.out.println(obj.minRefuelStops(100, 10, new int[][]{{10, 60}, {20, 30}, {30, 30}, {60, 40}}));
    }

    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int travelled = 0;
        int stops = -1;
        int stationsVisited = 0;
        PriorityQueue<Integer> availableFuels = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        availableFuels.add(startFuel);

        while (availableFuels.size() > 0) {
            int fuelToFill = availableFuels.poll();
            stops++;
            travelled += fuelToFill;

            if (travelled >= target) {
                return stops;
            }

            while (stationsVisited < stations.length) {
                if (stations[stationsVisited][0] <= travelled) {
                    availableFuels.add(stations[stationsVisited][1]);
                    stationsVisited++;
                } else {
                    break;
                }
            }

        }

        return -1;
    }
}
