package PracticeQuestions;

import java.util.Arrays;

public class Q1011 {
    public static void main(String[] args) {
        Q1011 obj = new Q1011();

        System.out.println(obj.shipWithinDays(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5));
    }

    public int shipWithinDays(int[] weights, int days) {
        int r = Arrays.stream(weights).sum();
        int l = r / days;

        int ans = r;
        while (l <= r) {
            int mid = (l + r) / 2;

            if (checkCanTake(weights, days, mid)) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return ans;

    }

    public boolean checkCanTake(int[] weights, int days, int maxCapacity) {
        int usedDays = 1;
        int capacity = 0;
        for (int i = 0; i < weights.length; i++) {
            if (usedDays > days) {
                return false;
            }

            if (weights[i] > maxCapacity) {
                return false;
            }

            if (capacity + weights[i] > maxCapacity) {
                capacity = 0;
                usedDays++;
            }

            capacity = capacity + weights[i];
        }

        return usedDays <= days;
    }
}
