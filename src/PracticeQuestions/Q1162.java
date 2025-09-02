package PracticeQuestions;

import java.util.LinkedList;
import java.util.Queue;

public class Q1162 {
    public static void main(String[] args) {
        Q1162 obj = new Q1162();
        System.out.println(obj.maxDistance(new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}}));
    }

    public int maxDistance(int[][] grid) {
        int n = grid.length;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int value = grid[i][j];

                if (value == 1) {
                    queue.add(new int[]{i, j, 0});
                    visited[i][j] = true;
                }
            }
        }


        if (queue.size() == 0 || queue.size() == n * n) {
            return -1;
        }


        int maxDistance = 0;
        while (!queue.isEmpty()) {

            int[] head = queue.remove();
            int x = head[0];
            int y = head[1];
            int distance = head[2];

            maxDistance = Math.max(maxDistance, distance);

            int[][] difference = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for (int i = 0; i < difference.length; i++) {
                int newX = x + difference[i][0];
                int newY = y + difference[i][1];
                if (validate(newX, newY, n, visited)) {
                    visited[newX][newY] = true;
                    queue.add(new int[]{newX, newY, distance + 1});
                }
            }
        }

        return maxDistance;

    }

    private boolean validate(int x, int y, int n, boolean[][] visited) {
        return x >= 0 && y >= 0 && x < n && y < n && !visited[x][y];
    }


}
