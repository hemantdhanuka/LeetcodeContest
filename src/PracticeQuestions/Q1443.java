package PracticeQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q1443 {
    public static void main(String[] args) {
        Q1443 obj = new Q1443();
        System.out.println(obj.minTime(7, new int[][]{{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}}, Arrays.asList(new Boolean[]{false, false, false, false, false, false, false})));
    }


    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        List<Integer> undirectedEdgeList[] = new ArrayList[n];
        for (int i = 0; i < undirectedEdgeList.length; i++) {
            undirectedEdgeList[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            undirectedEdgeList[u].add(v);
            undirectedEdgeList[v].add(u);
        }

        System.out.println(Arrays.toString(undirectedEdgeList));


        boolean selfOrSubTreeHasApple[] = new boolean[n];
        boolean visited[] = new boolean[n];
        getSubTreeAppleMap(0, undirectedEdgeList, selfOrSubTreeHasApple, visited, hasApple);

        System.out.println(Arrays.toString(selfOrSubTreeHasApple));

        visited = new boolean[n];
        return getTime(0, undirectedEdgeList, selfOrSubTreeHasApple, visited);
    }

    private boolean getSubTreeAppleMap(int node, List<Integer>[] undirectedEdgeList, boolean selfOrSubTreeHasApple[], boolean visited[], List<Boolean> hasAppleList) {
        if (visited[node]) {
            return false;
        }
        visited[node] = true;

        boolean hasApple = false;
        List<Integer> edges = undirectedEdgeList[node];
        for (int i = 0; i < edges.size(); i++) {
            int edge = edges.get(i);
            hasApple = getSubTreeAppleMap(edge, undirectedEdgeList, selfOrSubTreeHasApple, visited, hasAppleList) || hasApple;
        }

        selfOrSubTreeHasApple[node] = hasApple || hasAppleList.get(node);
        return selfOrSubTreeHasApple[node];
    }

    private int getTime(int node, List<Integer>[] undirectedEdgeList, boolean selfOrSubTreeHasApple[], boolean visited[]) {
        visited[node] = true;

        int time = 0;
        List<Integer> edges = undirectedEdgeList[node];
        for (int i = 0; i < edges.size(); i++) {
            int edge = edges.get(i);

            if (!visited[edge] && selfOrSubTreeHasApple[edge]) {
                time = time + 2 + getTime(edge, undirectedEdgeList, selfOrSubTreeHasApple, visited);
            }
        }

        return time;

    }

}
