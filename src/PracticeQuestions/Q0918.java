package PracticeQuestions;

import java.util.PriorityQueue;

public class Q0918 {
    public static void main(String[] args) {
        Q0918 obj = new Q0918();
        System.out.println(obj.maxSubarraySumCircular(new int[]{-3, -2, -3}));
    }

    public int maxSubarraySumCircular(int[] nums) {
        int[] prefixSum = getPrefixSum(nums);

        PriorityQueue<Value> maxHeap = new PriorityQueue<>();
        for (int i = 0; i < prefixSum.length; i++) {
            maxHeap.add(new Value(i, prefixSum[i]));
        }

        int prefixMax[] = getPrefixMax(nums);

        int ans = getMaxSubArrayForSuffixArray(0, maxHeap);
        for (int i = 1; i < nums.length; i++) {
            int value1 = getMaxSubArrayForSuffixArray(i, maxHeap) - prefixSum[i - 1];
            int value2 = prefixSum[nums.length - 1] - prefixSum[i - 1] + prefixMax[i - 1];

            ans = Math.max(ans, Math.max(value1, value2));
        }

        return ans;

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

    private int getMaxSubArrayForSuffixArray(int i, PriorityQueue<Value> maxHeap) {
        while (maxHeap.peek().position < i) {
            maxHeap.poll();
        }
        return maxHeap.peek().prefixSum;
    }


    class Value implements Comparable<Value> {
        int position;
        int prefixSum;

        public Value(int position, int prefixSum) {
            this.position = position;
            this.prefixSum = prefixSum;
        }

        @Override
        public int compareTo(Value o) {
            return o.prefixSum - this.prefixSum;
        }
    }
}
