package PracticeQuestions;

public class Q0918V2 {
    public static void main(String[] args) {
        Q0918V2 obj = new Q0918V2();
        System.out.println(obj.maxSubarraySumCircular(new int[]{3, -2, 2, -3}));
    }


    // using kadane ( solve for subarray instead of circular sub array ), and making array twice
    public int maxSubarraySumCircular(int[] nums) {
        int prefixSum[] = getPrefixSum(nums);
        int prefixMax[] = getPrefixMax(nums);

        int n = nums.length;
        int max = prefixSum[n - 1];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum = sum + nums[i];
            if (sum < nums[i]) {
                sum = nums[i];
            }

            max = Math.max(max, sum);
        }

        for (int i = 1; i < n; i++) {
            max = Math.max(max, (prefixSum[n - 1] - prefixSum[i - 1] + prefixMax[i - 1]));
        }
        return max;
    }

    private int[] getPrefixSum(int[] nums) {
        int prefixSum[] = new int[nums.length];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            prefixSum[i] = sum;
        }

        return prefixSum;
    }

    private int[] getPrefixMax(int[] nums) {
        int prefixMax[] = new int[nums.length];
        int max = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            max = Math.max(max, sum);
            prefixMax[i] = max;
        }

        return prefixMax;
    }

}
