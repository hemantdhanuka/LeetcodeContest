package biweekly139;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Q2 {
    public static void main(String[] args) {
        Q2 obj = new Q2();
        List<List<Integer>> listOfLists = new ArrayList<>();

        // Add inner lists to the outer list
        listOfLists.add(new ArrayList<>(List.of(0, 1, 0, 0, 0)));
        listOfLists.add(new ArrayList<>(List.of(0, 1, 0, 1, 0)));
        listOfLists.add(new ArrayList<>(List.of(0, 0, 0, 1, 0)));

        System.out.println(obj.findSafeWalk(listOfLists, 1));
    }


    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();

        int[][] visited = new int[m][n];
        Queue<Node> pq = new PriorityQueue<Node>();

        if (grid.get(0).get(0) == 1) {
            health--;
        }
        visited[0][0] = health;

        pq.add(new Node(0, 0, health));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (node.health <= 0) {
                continue;
            }

            int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

            for (int i = 0; i < directions.length; i++) {
                int newX = node.x + directions[i][0];
                int newY = node.y + directions[i][1];

                if (newX < 0 || newY < 0 || newX >= m || newY >= n) {
                    continue;
                }
                int newHealth = node.health;
                if (grid.get(newX).get(newY) == 1) {
                    newHealth--;
                }

                if (visited[newX][newY] < newHealth) {
                    pq.add(new Node(newX, newY, newHealth));
                    visited[newX][newY] = newHealth;
                }
            }
        }

        return visited[m - 1][n - 1] > 0;
    }

    class Node implements Comparable<Node> {
        int x;
        int y;
        int health;

        public Node(int x, int y, int health) {
            this.x = x;
            this.y = y;
            this.health = health;
        }

        @Override
        public int compareTo(Node o) {
            return o.health - this.health;
        }
    }


}
