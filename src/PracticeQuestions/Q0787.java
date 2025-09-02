package PracticeQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Q0787 {

    public static void main(String[] args) {

    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        PriorityQueue<Distance> queue = new PriorityQueue<>();
        int[] hopCounts = new int[n];
        Arrays.fill(hopCounts, Integer.MAX_VALUE);

        ArrayList<Integer>[] edgeList = new ArrayList[n];
        for (int i = 0; i < edgeList.length; i++) {
            edgeList[i] = new ArrayList<>();
        }

        for (int i = 0; i < flights.length; i++) {
            int[] flight = flights[i];

            int source = flight[0];
            int dest = flight[1];
            int distance = flight[2];

            edgeList[source].add(dest);
            edgeList[source].add(distance);

        }

        queue.add(new Distance(src, 0, -1));

        while (!queue.isEmpty()) {
            Distance node = queue.poll();
            int destination = node.dest;
            int distance = node.distance;
            int stops = node.stops;

            if (stops > k) {
                continue;
            }

            if (stops >= hopCounts[destination]) {
                continue;
            }
            hopCounts[destination] = stops;

            if (destination == dst) {
                return distance;
            }

            ArrayList<Integer> edges = edgeList[destination];

            for (int i = 0; i < edges.size(); i = i + 2) {
                queue.add(new Distance(edges.get(i), distance + edges.get(i + 1), stops + 1));
            }
        }

        return -1;
    }

    static class Distance implements Comparable<Distance> {
        int dest;
        int distance;

        int stops;

        public Distance(int dest, int distance, int stops) {
            this.dest = dest;
            this.distance = distance;
            this.stops = stops;
        }


        @Override
        public int compareTo(Distance o) {
            return this.distance - o.distance;
        }
    }


}
