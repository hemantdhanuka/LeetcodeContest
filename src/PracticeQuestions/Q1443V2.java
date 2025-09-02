package PracticeQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q1443V2 {
    public static void main(String[] args) {
        Q1443V2 obj = new Q1443V2();
        System.out.println(obj.minTime(7, new int[][]{{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}}, Arrays.asList(new Boolean[]{false, false, true, false, true, true, false})));
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


        boolean visited[] = new boolean[n];
        return getTime(0, undirectedEdgeList, visited, hasApple).time;
    }

    private Result getTime(int node, List<Integer>[] undirectedEdgeList, boolean visited[], List<Boolean> hasApple) {
        if (visited[node]) {
            return new Result(false, 0);
        }
        visited[node] = true;

        List<Integer> edges = undirectedEdgeList[node];
        int count = 0;
        int time = 0;
        for (int i = 0; i < edges.size(); i++) {
            int edge = edges.get(i);
            Result result = getTime(edge, undirectedEdgeList, visited, hasApple);

            if (result.found) {
                count++;
                time += result.time;
            }
        }

        boolean found = count > 0 || hasApple.get(node);

        return new Result(found, 2 * count + time);
    }

}

class Result {
    boolean found = false;
    int time = 0;

    public Result(boolean found, int time) {
        this.found = found;
        this.time = time;
    }
}
