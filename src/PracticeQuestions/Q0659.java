package PracticeQuestions;

public class Q0659 {
    public static void main(String[] args) {
        Q0659 obj = new Q0659();

        System.out.println(obj.isPossible(new int[]{1, 2, 3, 4, 4, 5}));
    }

    public boolean isPossible(int[] nums) {
        int freq[] = new int[20002];

        for (int i = 0; i < nums.length; i++) {
            freq[nums[i] + 1000]++;
        }

        int count1 = 0;
        int count2 = 0;
        int countGreaterOrEqual3 = 0;

        for (int i = 0; i < freq.length; i++) {
            int next = freq[i];

            if (next < count1 + count2) {
                return false;
            }
            int newCount2 = count1;
            int newCountGreaterOrEqualTo3 = count2 + Math.min(countGreaterOrEqual3, next - (count1 + count2));
            int newCount1 = Math.max(0, next - (count1 + count2 + countGreaterOrEqual3));

            count1 = newCount1;
            count2 = newCount2;
            countGreaterOrEqual3 = newCountGreaterOrEqualTo3;
        }

        return true;
    }

}
