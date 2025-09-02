package PracticeQuestions;

import java.util.Arrays;

public class Q1833 {
    public static void main(String[] args) {
        Q1833 obj = new Q1833();
    }

    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);

        long sum = 0;
        int count = 0;
        for (int cost : costs) {
            sum += cost;

            if (sum <= coins) {
                count++;
            } else {
                break;
            }
        }

        return count;
    }
}
