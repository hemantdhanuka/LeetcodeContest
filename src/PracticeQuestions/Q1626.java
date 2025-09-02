package PracticeQuestions;

import java.util.Arrays;

public class Q1626 {


    static int[][] teamScoreDp;

    public static void main(String[] args) {
        Q1626 obj = new Q1626();
//        System.out.println(obj.bestTeamScore(new int[]{1, 3, 5, 10, 15}, new int[]{1, 2, 3, 4, 5}));
        System.out.println(obj.bestTeamScore(new int[]{4, 5, 6, 5}, new int[]{2, 1, 2, 1}));
    }

    public int bestTeamScore(int[] scores, int[] ages) {
        teamScoreDp = new int[scores.length + 1][scores.length + 1];
        for (int i = 0; i < teamScoreDp.length; i++) {
            Arrays.fill(teamScoreDp[i], -1);
        }

        Player[] players = new Player[scores.length + 1];
        players[0] = new Player(0, 0);

        for (int i = 0; i < scores.length; i++) {
            players[i + 1] = new Player(ages[i], scores[i]);
        }

        Arrays.sort(players);

        return getTeamScore(0, 0, players);
    }

    private int getTeamScore(int index, int lastSelectedIndex, Player[] players) {
        if (index == players.length) {
            return 0;
        }

        if (teamScoreDp[index][lastSelectedIndex] != -1) {
            return teamScoreDp[index][lastSelectedIndex];
        }


        int sum1 = getTeamScore(index + 1, lastSelectedIndex, players);
        int sum2 = players[index].score >= players[lastSelectedIndex].score ? players[index].score + getTeamScore(index + 1, index, players) : 0;

        int maxSum = Math.max(sum1, sum2);

        teamScoreDp[index][lastSelectedIndex] = maxSum;
        return maxSum;
    }

    class Player implements Comparable<Player> {
        int age;
        int score;

        public Player(int age, int score) {
            this.age = age;
            this.score = score;
        }

        @Override
        public int compareTo(Player o) {
            if (this.age == o.age) {
                return this.score - o.score;
            } else {
                return this.age - o.age;
            }
        }
    }
}
