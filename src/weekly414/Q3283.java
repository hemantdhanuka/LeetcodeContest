package weekly414;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Q3283 {
    public static void main(String[] args) {
        Q3283 obj = new Q3283();
        System.out.println(obj.maxMoves(0, 0, new int[][]{{6, 9}, {2, 8}, {0, 10}}));
    }

    public int maxMoves(int kx, int ky, int[][] positions) {
        int[][] newPositions = new int[positions.length + 1][2];
        newPositions[0] = new int[]{kx, ky};
        System.arraycopy(positions, 0, newPositions, 1, positions.length);

        int[][] distance = new int[newPositions.length][newPositions.length];

        for (int i = 0; i < newPositions.length; i++) {
            int[][] visited = new int[50][50];
            traverseGrid(newPositions[i][0], newPositions[i][1], visited);

            for (int j = 0; j < newPositions.length; j++) {
                distance[i][j] = visited[newPositions[j][0]][newPositions[j][1]];
            }
        }

        int[][][] dp = new int[newPositions.length][1 << newPositions.length][2];

        for (int[][] rows : dp) {
            for (int[] row : rows) {
                Arrays.fill(row, -1);
            }
        }

        boolean[] visitedNode = new boolean[newPositions.length];
        visitedNode[0] = true;

        return calculateMoves(0, 1, visitedNode, distance, dp);

    }

    public int calculateMoves(int nodePosition, int selectedCount, boolean[] visitedNodes, int[][] distance, int[][][] dp) {
        if (selectedCount == visitedNodes.length) {
            return 0;
        }

        int visitedNodeNumber = convertIntoNo(visitedNodes);
        int remainderOf2 = (selectedCount + 1) % 2;

        try {
            if (dp[nodePosition][visitedNodeNumber][remainderOf2] != -1) {
                return dp[nodePosition][visitedNodeNumber][remainderOf2];
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }

        int ans = 0;
        if (remainderOf2 == 0) {
            ans = 0;
            for (int i = 0; i < visitedNodes.length; i++) {
                if (!visitedNodes[i]) {
                    int moves = distance[nodePosition][i];
                    visitedNodes[i] = true;
                    ans = Math.max(ans, moves + calculateMoves(i, selectedCount + 1, visitedNodes, distance, dp));
                    visitedNodes[i] = false;
                }
            }
        } else {
            ans = Integer.MAX_VALUE;
            for (int i = 0; i < visitedNodes.length; i++) {
                if (!visitedNodes[i]) {
                    int moves = distance[nodePosition][i];
                    visitedNodes[i] = true;
                    ans = Math.min(ans, moves + calculateMoves(i, selectedCount + 1, visitedNodes, distance, dp));
                    visitedNodes[i] = false;
                }
            }

        }

        if (ans == Integer.MAX_VALUE) {
            ans = 0;
        }

        dp[nodePosition][visitedNodeNumber][remainderOf2] = ans;
        return ans;

    }

    public int convertIntoNo(boolean[] visitedNodes) {
        int number = 0;
        for (int i = 0; i < visitedNodes.length; i++) {
            if (visitedNodes[i]) {
                number = number + (1 << i);
            }
        }

        return number;
    }


    public void traverseGrid(int x, int y, int[][] visited) {
        Queue<Position> queue = new LinkedList<>();

        for (int[] row : visited) {
            Arrays.fill(row, -1);
        }

        queue.add(new Position(x, y));
        visited[x][y] = 0;
        int[][] moves = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
        while (!queue.isEmpty()) {
            Position position = queue.poll();
            int oldX = position.x;
            int oldY = position.y;

            for (int[] move : moves) {
                int a = move[0];
                int b = move[1];

                int newX = oldX + a;
                int newY = oldY + b;

                if (newX < 0 || newX >= 50 || newY < 0 || newY >= 50 || visited[newX][newY] != -1) {
                    continue;
                }

                visited[newX][newY] = visited[oldX][oldY] + 1;
                queue.add(new Position(newX, newY));
            }
        }
    }

    class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


}
