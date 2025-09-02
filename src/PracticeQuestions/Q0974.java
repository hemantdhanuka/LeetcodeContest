package PracticeQuestions;

public class Q0974 {
    public static void main(String[] args) {
        Q0974 obj = new Q0974();
        System.out.println(obj.subarraysDivByK(new int[]{4, 5, 0, -2, -3, 1}, 5));
    }

    public int subarraysDivByK(int[] nums, int k) {
        int[] prefixReminder = new int[k];

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            int reminder = getPositiveReminder(sum, k);
            prefixReminder[reminder]++;
        }

        sum = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int extra = getPositiveReminder(sum, k);
            count += prefixReminder[extra];
            sum = sum + nums[i];

            prefixReminder[getPositiveReminder(sum, k)]--;
        }

        return count;
    }

    private int getPositiveReminder(int num, int k) {
        return ((num % k) + k) % k;
    }

}
