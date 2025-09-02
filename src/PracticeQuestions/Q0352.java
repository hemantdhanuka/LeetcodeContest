package PracticeQuestions;

import java.util.ArrayList;

public class Q0352 {
    public static void main(String[] args) {

    }

    class SummaryRanges {
        boolean[] numbersAddedFlag;

        public SummaryRanges() {
            numbersAddedFlag = new boolean[10001];
        }

        public void addNum(int value) {
            numbersAddedFlag[value] = true;
        }

        public int[][] getIntervals() {
            ArrayList<Integer> addedNumberList = new ArrayList<>();
            for (int i = 0; i < numbersAddedFlag.length; i++) {
                if (numbersAddedFlag[i]) {
                    addedNumberList.add(i);
                }
            }

            ArrayList<int[]> intervals = new ArrayList<>();
            if (addedNumberList.size() == 0) {
                return new int[][]{};
            }


            int i = 1;
            intervals.add(new int[]{addedNumberList.get(0), addedNumberList.get(0)});
            while (i < addedNumberList.size()) {

                if (addedNumberList.get(i) - intervals.get(intervals.size() - 1)[1] == 1) {
                    intervals.get(intervals.size() - 1)[1]++;
                } else {
                    intervals.add(new int[]{addedNumberList.get(i), addedNumberList.get(i)});
                }

                i++;
            }

            int[][] ansIntervals = new int[intervals.size()][2];
            return intervals.toArray(ansIntervals);
        }
    }

}
