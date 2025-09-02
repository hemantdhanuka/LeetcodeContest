package PracticeQuestions;

import java.util.Arrays;

public class Q0072 {
    public static void main(String[] args) {
        Q0072 obj = new Q0072();
        System.out.println(obj.minDistance("intention", "execution"));
    }

    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()][word2.length()];

        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        return recursion(0, 0, word1, word2, dp);
    }

    public int recursion(int i, int j, String word1, String word2, int[][] dp) {
        if (i == word1.length()) {
            return word2.length() - j;
        }

        if (j == word2.length()) {
            return word1.length() - i;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int ans;
        if (word1.charAt(i) == word2.charAt(j)) {
            ans = recursion(i + 1, j + 1, word1, word2, dp);
        } else {
            ans = 1 + Math.min(recursion(i, j + 1, word1, word2, dp),
                    Math.min(recursion(i + 1, j, word1, word2, dp), recursion(i + 1, j + 1, word1, word2, dp)));
        }

        dp[i][j] = ans;
        return ans;
    }

}

