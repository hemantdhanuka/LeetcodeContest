package PracticeQuestions;

import java.util.*;

class Q0207CourseSchedule {
    public static void main(String[] args) {
        Q0207CourseSchedule obj = new Q0207CourseSchedule();
        System.out.println(obj.canFinish(2, new int[][]{{1, 1}}));
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        Graph graph = new Graph(numCourses, prerequisites);
        int visited = 0;

        Queue<Integer> courseToStartQueue = graph.getCountListWithNoPreQ();
        while (!courseToStartQueue.isEmpty()) {
            int course = courseToStartQueue.poll();
            visited++;

            List<Integer> edges = graph.getEdges(course);

            for (int i = 0; i < edges.size(); i++) {
                int edge = edges.get(i);
                graph.decreaseCountMap(edge);

                if (graph.getCount(edge) == 0) {
                    courseToStartQueue.add(edge);
                }
            }
        }

        return visited == numCourses;
    }

}


class Graph {
    int vertex;
    List<Integer>[] edges;

    Map<Integer, Integer> preReqCountMap = new HashMap<>();

    public Graph(int vertex, int[][] prerequisites) {
        this.vertex = vertex;
        this.edges = new ArrayList[vertex];

        for (int i = 0; i < vertex; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int[] edge = prerequisites[i];
            int x = edge[0];
            int y = edge[1];

            edges[y].add(x);

            int count = preReqCountMap.getOrDefault(x, 0);
            preReqCountMap.put(x, count + 1);
        }
    }

    public Queue<Integer> getCountListWithNoPreQ() {
        Queue<Integer> list = new ArrayDeque<>();
        for (int i = 0; i < vertex; i++) {
            if (preReqCountMap.getOrDefault(i, 0) == 0) {
                list.add(i);
            }
        }
        return list;
    }

    public List<Integer> getEdges(int vertex) {
        return edges[vertex];
    }

    public void decreaseCountMap(int vertex) {
        int already = getCount(vertex);
        preReqCountMap.put(vertex, already - 1);
    }

    public int getCount(int vertex) {
        return preReqCountMap.getOrDefault(vertex, 0);
    }
}
