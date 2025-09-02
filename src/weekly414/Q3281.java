package weekly414;

import java.util.Arrays;

public class Q3281 {
    public static void main(String[] args) {
        int[] start = {6, 0, 3};
        Q3281 obj = new Q3281();
        System.out.println(obj.maxPossibleScore(start, 2));
    }

    public int maxPossibleScore(int[] start, int d) {
        Arrays.sort(start);

        int minDifference = start[start.length - 1];
        for (int i = 1; i < start.length; i++) {
            minDifference = Math.min(minDifference, start[i] - start[i - 1]);
        }


        // binary search on d
        int ansD = 0;
        int l = 0;
        int r = d;

        while (l <= r) {
            int mid = (l + r) / 2;

            if (this.checkCorrect(start, mid, minDifference, d)) {
                ansD = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return ansD + minDifference;
    }

    public boolean checkCorrect(int[] start, long possibleD, int minDifference, int d) {
        long last = start[0];
        for (int i = 1; i < start.length; i++) {
            if (last + possibleD + minDifference > start[i] + d) {
                return false;
            } else {
                last = Math.max(last + possibleD + minDifference, start[i]);
            }
        }

        return true;
    }


}
