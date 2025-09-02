package PracticeQuestions;

import java.util.*;

public class Q1129 {
    public static void main(String[] args) {
        Q1129 obj = new Q1129();
        System.out.println(Arrays.toString(obj.shortestAlternatingPaths(3, new int[][]{{0, 1}, {1, 2}}, new int[][]{})));
    }

    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<Integer>[] redEdgeList = new ArrayList[n];
        for (int i = 0; i < redEdgeList.length; i++) {
            redEdgeList[i] = new ArrayList<>();
        }

        for (int i = 0; i < redEdges.length; i++) {
            int[] redEdge = redEdges[i];
            redEdgeList[redEdge[0]].add(redEdge[1]);
        }

        List<Integer>[] blueEdgeList = new ArrayList[n];
        for (int i = 0; i < blueEdgeList.length; i++) {
            blueEdgeList[i] = new ArrayList<>();
        }

        for (int i = 0; i < blueEdges.length; i++) {
            int[] blueEdge = blueEdges[i];
            blueEdgeList[blueEdge[0]].add(blueEdge[1]);
        }

        int[] endingAtBlueShortedDistance = new int[n];
        int[] endingAtRedShortedDistance = new int[n];
        Arrays.fill(endingAtBlueShortedDistance, Integer.MAX_VALUE);
        Arrays.fill(endingAtRedShortedDistance, Integer.MAX_VALUE);


        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, Color.RED, 0));
        queue.add(new Node(0, Color.BLUE, 0));
        endingAtBlueShortedDistance[0] = 0;
        endingAtRedShortedDistance[0] = 0;

        while (!queue.isEmpty()) {
            Node node = queue.remove();

            if (node.color == Color.RED) {
                List<Integer> edgeList = blueEdgeList[node.id];

                for (int i = 0; i < edgeList.size(); i++) {
                    int edge = edgeList.get(i);
                    if (endingAtBlueShortedDistance[edge] == Integer.MAX_VALUE) {
                        endingAtBlueShortedDistance[edge] = node.distance + 1;
                        queue.add(new Node(edge, Color.BLUE, node.distance + 1));
                    }
                }
            } else {

                List<Integer> edgeList = redEdgeList[node.id];

                for (int i = 0; i < edgeList.size(); i++) {
                    int edge = edgeList.get(i);
                    if (endingAtRedShortedDistance[edge] == Integer.MAX_VALUE) {
                        endingAtRedShortedDistance[edge] = node.distance + 1;
                        queue.add(new Node(edge, Color.RED, node.distance + 1));
                    }
                }
            }
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int min = Math.min(endingAtBlueShortedDistance[i], endingAtRedShortedDistance[i]);
            if (min == Integer.MAX_VALUE) {
                min = -1;
            }

            ans[i] = min;
        }
        return ans;
    }


    enum Color {
        RED, BLUE

    }

    class Node {
        int id;
        Color color;
        int distance;

        public Node(int id, Color color, int distance) {
            this.id = id;
            this.color = color;
            this.distance = distance;
        }
    }
}
