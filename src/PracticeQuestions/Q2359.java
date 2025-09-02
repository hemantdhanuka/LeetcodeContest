package PracticeQuestions;

import java.util.Arrays;

public class Q2359 {
    public static void main(String[] args) {
        Q2359 obj = new Q2359();
        System.out.println(obj.closestMeetingNode(new int[]{2, 2, 3, -1}, 0, 1));
    }

    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int[] distance1 = getDistanceArray(edges, node1);
        int[] distance2 = getDistanceArray(edges, node2);

        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < edges.length; i++) {
            if (distance1[i] != -1 && distance2[i] != -1) {
                int maxDistance = Math.max(distance1[i], distance2[i]);
                if (maxDistance < min) {
                    min = maxDistance;
                    index = i;
                }
            }
        }

        return index;
    }

    private int[] getDistanceArray(int[] edges, int node) {
        int[] distance = new int[edges.length];
        Arrays.fill(distance, -1);

        distance[node] = 0;
        int count = 1;
        while (true) {
            if (edges[node] == -1 || distance[edges[node]] != -1) {
                break;
            }
            distance[edges[node]] = count;
            node = edges[node];
            count++;
        }

        return distance;
    }

}
