package PracticeQuestions;

import java.util.Arrays;
import java.util.HashSet;

public class Q0997 {

    public static void main(String[] args) {
        Q0997 obj = new Q0997();
        System.out.println(obj.findJudge(2, new int[][]{{1, 2}}));
    }

    public int findJudge(int n, int[][] trust) {
        boolean[] isJudges = new boolean[n + 1];
        Arrays.fill(isJudges, true);

        HashSet<Integer>[] trustees = new HashSet[n + 1];
        for (int i = 0; i < trustees.length; i++) {
            trustees[i] = new HashSet<>();
        }

        for (int i = 0; i < trust.length; i++) {
            int truster = trust[i][0];
            int trustee = trust[i][1];

            isJudges[truster] = false;
            trustees[trustee].add(truster);
        }


        for (int i = 1; i < isJudges.length; i++) {
            if (isJudges[i] && trustees[i].size() == n - 1) {
                return i;
            }
        }

        return -1;

    }


}
