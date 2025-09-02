package biweekly139;

import java.util.HashMap;
import java.util.Map;

public class Q3287 {
    public static void main(String[] args) {
        Q3287 obj = new Q3287();
        System.out.println(obj.maxValue(new int[]{2, 42}, 1));
    }

    public int maxValue(int[] nums, int k) {
        boolean[][][] lhsOr = new boolean[nums.length][k + 1][128];
        boolean[][][] rhsOr = new boolean[nums.length][k + 1][128];
        fillAllOrValueLHS(nums.length - 1, k, nums, lhsOr, new HashMap<>());

        int[] reverseArray = new int[nums.length];
        for (int i = 0; i < reverseArray.length; i++) {
            reverseArray[i] = nums[nums.length - 1 - i];
            reverseArray[i] = nums[nums.length - 1 - i];
        }

        fillAllOrValueLHS(reverseArray.length - 1, k, reverseArray, rhsOr, new HashMap<>());

        int ans = 0;
        for (int i = k - 1; i < nums.length - k; i++) { // all positions.
            for (int j = 0; j < 128; j++) {  // all possible or values of lhs
                for (int l = 0; l < 128; l++) { // all possible value of rhs
                    if (lhsOr[i][k][j] & rhsOr[nums.length - 1 - i - 1][k][l]) {
                        ans = Math.max(ans, j ^ l);
                    }
                }
            }
        }
        return ans;
    }


    public void fillAllOrValueLHS(int position, int remaining, int[] nums, boolean[][][] dp, Map<String, Boolean> subTaskStatusMap) {
        if (remaining == 0) {
            dp[position][remaining][0] = true;
            subTaskStatusMap.put(position + "," + remaining, true);
        }

        if (position == 0 && remaining == 1) {
            dp[position][remaining][nums[0]] = true;
            subTaskStatusMap.put(position + "," + remaining, true);
        }

        if (position == 0) {
            subTaskStatusMap.put(position + "," + remaining, true);
        }

        if (subTaskStatusMap.get(position + "," + remaining) != null) {
            return;
        }


        // not picked
        fillAllOrValueLHS(position - 1, remaining, nums, dp, subTaskStatusMap);
        System.arraycopy(dp[position - 1][remaining], 1, dp[position][remaining], 1, 127);


        fillAllOrValueLHS(position - 1, remaining - 1, nums, dp, subTaskStatusMap);
        int number = nums[position];
        for (int i = 0; i < 128; i++) {
            boolean isOrValue = dp[position - 1][remaining - 1][i];
            if (isOrValue) {
                int or = i | number;
                dp[position][remaining][or] = true;
            }
        }


        subTaskStatusMap.put(position + "," + remaining, true);
    }


}
