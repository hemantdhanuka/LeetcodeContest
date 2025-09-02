package PracticeQuestions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Q1220 {
    static int mod = 1000000007;

    static HashMap<Character, List<Character>> rulesMap = new HashMap<Character, List<Character>>();
    static char vowels[] = {'a', 'e', 'i', 'o', 'u'};

    static int findIndex(char ch) {
        for (int i = 0; i < vowels.length; i++) {
            if (vowels[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    static void initRulesMap() {
        rulesMap.put('a', Arrays.asList('e'));
        rulesMap.put('e', Arrays.asList('a', 'i'));
        rulesMap.put('i', Arrays.asList('a', 'e', 'o', 'u'));
        rulesMap.put('o', Arrays.asList('i', 'u'));
        rulesMap.put('u', Arrays.asList(('a')));
    }

    public static void main(String[] args) {
        Q1220 obj = new Q1220();
        int ans = obj.countVowelPermutation(5);
        System.out.println(ans);
    }

    public int countVowelPermutation1(int n) {
        initRulesMap();
        int dp[][] = new int[5][n];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        int answer = 0;
        for (int i = 0; i < vowels.length; i++) {
            answer = (answer + count(vowels[i], 1, dp, n)) % mod;
        }

        return answer;

    }

    public int count(char c, int i, int dp[][], int length) {
        if (i == length) {
            return 1;
        }

        if (dp[findIndex(c)][i] != -1) {
            return dp[findIndex(c)][i];
        }

        List<Character> rules = rulesMap.get(c);

        int ans = 0;
        for (int j = 0; j < rules.size(); j++) {
            char nextChar = rules.get(j);

            ans = (ans + count(nextChar, i + 1, dp, length)) % mod;
        }
        dp[findIndex(c)][i] = ans;
        return ans;

    }


    public int countVowelPermutation(int n) {
        long dp[][] = new long[n][vowels.length];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[i][0] = 1;
                dp[i][1] = 1;
                dp[i][2] = 1;
                dp[i][3] = 1;
                dp[i][4] = 1;
            } else {
                dp[i][0] = dp[i - 1][1];
                dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % mod;
                dp[i][2] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][3] + dp[i - 1][4]) % mod;
                dp[i][3] = (dp[i - 1][2] + dp[i - 1][4]) % mod;
                dp[i][4] = dp[i - 1][0];
            }
        }

        return (int) ((dp[n - 1][0] + dp[n - 1][1] + dp[n - 1][2] + dp[n - 1][3] + dp[n - 1][4]) % mod);
    }
}
