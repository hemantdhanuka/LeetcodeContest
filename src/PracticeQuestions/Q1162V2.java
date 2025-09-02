package PracticeQuestions;

import java.util.Arrays;

public class Q1162V2 {
    public static void main(String[] args) {
        Q1162V2 obj = new Q1162V2();
        System.out.println(obj.maxDistance(new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}}));
    }

    public int maxDistance(int[][] grid) {
        int n = grid.length;

        int defaultValue = 2 * n;
        // top left to bottom right
        int[][] array1 = getPrefilledArray(n, n, defaultValue);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 1) {
                    array1[i][j] = 0;
                } else {
                    int min = 1 + Math.min(i > 0 ? array1[i - 1][j] : defaultValue, j > 0 ? array1[i][j - 1] : defaultValue);
                    array1[i][j] = Math.min(defaultValue, min);
                }
            }
        }

        // bottom left to top right
        int[][] array2 = getPrefilledArray(n, n, defaultValue);
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 1) {
                    array2[i][j] = 0;
                } else {
                    int min = 1 + Math.min(i <= n - 2 ? array2[i + 1][j] : defaultValue, j > 0 ? array2[i][j - 1] : defaultValue);
                    array2[i][j] = Math.min(defaultValue, min);
                }
            }
        }

        // top right to bottom left
        int[][] array3 = getPrefilledArray(n, n, defaultValue);
        for (int i = 0; i < grid.length; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 1) {
                    array3[i][j] = 0;
                } else {
                    int min = 1 + Math.min(i > 0 ? array3[i - 1][j] : defaultValue, j <= n - 2 ? array3[i][j + 1] : defaultValue);
                    array3[i][j] = Math.min(defaultValue, min);
                }
            }
        }

        // bottom up to top down
        int[][] array4 = getPrefilledArray(n, n, defaultValue);
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 1) {
                    array4[i][j] = 0;
                } else {
                    int min = 1 + Math.min(i <= n - 2 ? array4[i + 1][j] : defaultValue, j <= n - 2 ? array4[i][j + 1] : defaultValue);
                    array4[i][j] = Math.min(defaultValue, min);
                }
            }
        }

        int ans = -1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 1) {
                    continue;
                }

                ans = Math.max(ans, Math.min(Math.min(array1[i][j], array2[i][j]), Math.min(array3[i][j], array4[i][j])));
            }
        }

        if (ans == defaultValue) {
            return -1;
        }
        return ans;
    }

    private int[][] getPrefilledArray(int rows, int column, int defaultValue) {
        int[][] prefilledArray = new int[rows][column];

        for (int[] row : prefilledArray) {
            Arrays.fill(row, defaultValue);
        }

        return prefilledArray;
    }


}
