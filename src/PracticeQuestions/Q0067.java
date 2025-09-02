package PracticeQuestions;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Q0067 {
    public static void main(String[] args) {
        Q0067 obj = new Q0067();
        int[][] ans = obj.insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5});
        for (int[] answer :
                ans) {
            System.out.println(Arrays.toString(answer));

        }
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int[][] newIntervals = new int[intervals.length + 1][2];

        int i = 0;
        int newIntervalPosition = 0;
        boolean insertedFlag = false;
        while (i < intervals.length) {
            int[] interval = intervals[i];
            int start = interval[0];

            if (newInterval[0] <= start && !insertedFlag) {
                newIntervals[newIntervalPosition] = newInterval;
                newIntervalPosition++;
                insertedFlag = true;
            }

            newIntervals[newIntervalPosition] = interval;
            newIntervalPosition++;
            i++;
        }

        if (!insertedFlag) {
            newIntervals[newIntervalPosition] = newInterval;
        }

        ArrayDeque<int[]> stack = new ArrayDeque();
        for (int j = 0; j < newIntervals.length; j++) {
            int[] interval = newIntervals[j];
            if (stack.isEmpty()) {
                stack.addLast(interval);
                continue;
            }

            int[] top = stack.peekLast();

            if (top[1] < interval[0]) {
                stack.addLast(interval);
            } else {
                stack.removeLast();
                top[1] = Math.max(top[1], interval[1]);
                stack.addLast(top);
            }
        }

        int[][] ans = new int[stack.size()][2];
        int k = 0;
        while (!stack.isEmpty()) {
            ans[k] = stack.pollFirst();
            k++;
        }
        return ans;
    }

}
