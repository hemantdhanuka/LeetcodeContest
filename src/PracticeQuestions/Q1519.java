package PracticeQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q1519 {
    public static void main(String[] args) {
        Q1519 obj = new Q1519();
        System.out.println(Arrays.toString(obj.countSubTrees(7, new int[][]{{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}}, "abaedcd")));
    }

    public int[] countSubTrees(int n, int[][] edges, String labels) {
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

        int count[] = new int[n];
        boolean visited[] = new boolean[n];
        getCount(0, labels, visited, undirectedEdgeList, count);
        return count;
    }

    private int[] getCount(int node, String labels, boolean[] visited, List<Integer> undirectedEdgeList[], int count[]) {
        int frequency[] = new int[26];
        if (visited[node]) {
            return frequency;
        }
        visited[node] = true;
        List<Integer> edges = undirectedEdgeList[node];
        for (int i = 0; i < edges.size(); i++) {
            int edge = edges.get(i);

            int edgeFrequency[] = getCount(edge, labels, visited, undirectedEdgeList, count);

            for (int j = 0; j < 26; j++) {
                frequency[j] = frequency[j] + edgeFrequency[j];
            }
        }

        frequency[labels.charAt(node) - 'a'] += 1;
        count[node] = frequency[labels.charAt(node) - 'a'];

        return frequency;
    }

}
