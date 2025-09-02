package PracticeQuestions;

import java.util.HashSet;
import java.util.Set;

public class Q0904 {
    public static void main(String[] args) {
        Q0904 obj = new Q0904();
        System.out.println(obj.totalFruit(new int[]{1, 1, 1, 5, 5, 6, 6, 5, 6, 7, 6, 5}));
    }

    public int totalFruit(int[] fruits) {
        Set<Integer> set = new HashSet<>();
        set.add(-1);
        set.add(fruits[0]);

        int last = 0;
        int ans = 1;
        int max = 1;
        for (int i = 1; i < fruits.length; i++) {
            if (set.contains(fruits[i])) {
                if (fruits[i] != fruits[i - 1]) {
                    last = i;
                }
                ans++;
                max = Math.max(max, ans);

            } else {
                ans = i - last + 1;
                max = Math.max(max, ans);
                set = new HashSet<>();
                set.add(fruits[i]);
                set.add(fruits[i - 1]);
                last = i;
            }
        }

        return max;

    }

}
