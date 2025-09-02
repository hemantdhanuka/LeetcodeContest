package PracticeQuestions;

import java.util.Arrays;

public class Q0045 {
    public static void main(String[] args) {
        Q0045 obj = new Q0045();
        System.out.println(obj.jump(new int[]{2, 3, 1, 1, 4}));
    }

    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);

        return answer(0, n, nums, dp);
    }

    public int answer(int i, int n, int[] nums, int[] dp) {
        if (i == n - 1) {
            return 0;
        }

        if (dp[i] != -1) {
            return dp[i];
        }

        if (nums[i] == 0) {
            return 100000;
        }


        int min = 100000;
        int value = nums[i];
        for (int j = 1; j <= value && i + j <= n - 1; j++) {
            min = Math.min(min, answer(i + j, n, nums, dp));
        }

        min = min + 1;

        if (min >= 100000) {
            min = 100000;
        }

        dp[i] = min;
        return dp[i];
    }
}
