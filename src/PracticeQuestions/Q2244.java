package PracticeQuestions;

import java.util.HashMap;
import java.util.Map;

public class Q2244 {
    public static void main(String[] args) {
        Q2244 obj = new Q2244();
        System.out.println(obj.minimumRounds(new int[]{2, 2, 3, 3, 2, 4, 4, 4, 4, 4}));
    }

    public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> countMap = new HashMap<>();

        for (int i = 0; i < tasks.length; i++) {
            int task = tasks[i];
            int freq = countMap.getOrDefault(task, 0);
            countMap.put(task, freq + 1);
        }

        int rounds = 0;
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int freq = entry.getValue();

            if (freq == 1) {
                return -1;
            } else {
                rounds += ((freq - 1) / 3) + 1;
            }
        }

        return rounds;
    }

}
