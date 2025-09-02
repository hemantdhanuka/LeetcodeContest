package PracticeQuestions;

import java.util.ArrayList;
import java.util.List;

public class Q2246 {

    static int finalAnswer = 0;

    public static void main(String[] args) {
        Q2246 obj = new Q2246();

        System.out.println(obj.longestPath(new int[]{-1, 0, 0, 1, 1, 2}, "abacbe"));
    }


    public int longestPath(int[] parent, String s) {
        finalAnswer = 0;
        List<Integer> undirectedEdgeList[] = new ArrayList[parent.length];
        for (int i = 0; i < undirectedEdgeList.length; i++) {
            undirectedEdgeList[i] = new ArrayList<>();
        }
        for (int i = 1; i < parent.length; i++) {
            int u = i;
            int v = parent[i];

            undirectedEdgeList[u].add(v);
            undirectedEdgeList[v].add(u);
        }

        boolean visited[] = new boolean[parent.length];
        getSubTreeLongestPath(0, undirectedEdgeList, s, visited);
        return finalAnswer;
    }


    public int getSubTreeLongestPath(int node, List<Integer> undirectedEdgeList[], String s, boolean visited[]) {
        if (visited[node]) {
            return 0;
        }

        visited[node] = true;

        List<Integer> edges = undirectedEdgeList[node];
        int max1 = 0;
        int max2 = 0;

        for (int i = 0; i < edges.size(); i++) {
            int edge = edges.get(i);

            int answer = getSubTreeLongestPath(edge, undirectedEdgeList, s, visited);
            if (answer > 0 && s.charAt(edge) == s.charAt(node)) {
                if (answer > max1) {
                    max2 = max1;
                    max1 = answer;
                } else if (answer > max2) {
                    max2 = answer;
                }
            }

        }
        finalAnswer = Math.max(finalAnswer, max1 + max2 + 1);

        return max1 + 1;
    }

}
