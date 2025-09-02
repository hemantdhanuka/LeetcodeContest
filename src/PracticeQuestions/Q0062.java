package PracticeQuestions;

public class Q0062 {
    public static void main(String[] args) {
        int output = Q0062.uniquePaths(3, 7);
        System.out.println(output);
    }

    public static int uniquePaths(int m, int n) {
        int dp[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        return uniquePathUsingRecursion(0, 0, m, n, dp);
    }

    public static int uniquePathUsingRecursion(int x, int y, int m, int n, int dp[][]) {
        if (x == m || y == n) {
            return 0;
        }

        if (x == m - 1 && y == n - 1) {
            return 1;
        }

        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        dp[x][y] = uniquePathUsingRecursion(x + 1, y, m, n, dp) + uniquePathUsingRecursion(x, y + 1, m, n, dp);
        return dp[x][y];
    }

}
