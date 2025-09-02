package PracticeQuestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Q2421 {
    static int numberOfGoodPathsCount = 0;

    public static void main(String[] args) {
        Q2421 obj = new Q2421();
        System.out.println(obj.numberOfGoodPaths(new int[]{1, 3, 2, 1, 3}, new int[][]{{0, 1}, {0, 2}, {2, 3}, {2, 4}}));
    }

    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        numberOfGoodPathsCount = 0;
        List<Integer> undirectedEdgeList[] = new ArrayList[vals.length];
        for (int i = 0; i < undirectedEdgeList.length; i++) {
            undirectedEdgeList[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            undirectedEdgeList[u].add(v);
            undirectedEdgeList[v].add(u);
        }

        boolean visited[] = new boolean[vals.length];
        dfs(0, vals, undirectedEdgeList, visited);
        return numberOfGoodPathsCount;
    }

    private Map<Integer, Integer> dfs(int node, int[] vals, List<Integer> undirectedEdgeList[], boolean visited[]) {
        if (visited[node]) {
            return new HashMap<>();
        }

        visited[node] = true;

        List<Integer> edgeListForNode = undirectedEdgeList[node];
        Map<Integer, Integer> mergeMap = new HashMap<>();
        int count = 0;
        for (int i = 0; i < edgeListForNode.size(); i++) {
            int childNode = edgeListForNode.get(i);
            Map<Integer, Integer> goodPathChildMap = dfs(childNode, vals, undirectedEdgeList, visited);
            Map<Integer, Integer> filteredMap = goodPathChildMap.entrySet()
                    .stream().filter(x -> x.getKey() >= vals[node])
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            count += findCount(mergeMap, filteredMap);
            mergeMap = mergeMap(mergeMap, filteredMap);
        }

        mergeMap.put(vals[node], mergeMap.getOrDefault(vals[node], 0) + 1);
        count += mergeMap.get(vals[node]);
        numberOfGoodPathsCount += count;
        return mergeMap;
    }


    private int findCount(Map<Integer, Integer> existingMap, Map<Integer, Integer> newMap) {
        int count = 0;
        if (existingMap.size() == 0) {
            return 0;
        }

        for (Map.Entry<Integer, Integer> entry : newMap.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();

            count += value * existingMap.getOrDefault(key, 0);
        }
        return count;
    }

    private Map<Integer, Integer> mergeMap(Map<Integer, Integer> existingMap, Map<Integer, Integer> newMap) {
        int count = 0;
        Map<Integer, Integer> mergeMap = new HashMap<>(existingMap);
        for (Map.Entry<Integer, Integer> entry : newMap.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();

            mergeMap.put(key, value + mergeMap.getOrDefault(key, 0));
        }
        return mergeMap;
    }


}

