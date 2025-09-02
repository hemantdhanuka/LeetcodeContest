package PracticeQuestions;

import java.util.Arrays;

public class Q0452 {
    public static void main(String[] args) {
        Q0452 obj = new Q0452();
        System.out.println(obj.findMinArrowShots(new int[][]{{-2147483646, -2147483645}, {2147483646, 2147483647}}));
    }

    public int findMinArrowShots(int[][] points) {
        Balloon balloons[] = new Balloon[points.length];

        for (int i = 0; i < points.length; i++) {
            Balloon balloon = new Balloon(points[i][0], points[i][1]);
            balloons[i] = balloon;
        }
        Arrays.sort(balloons);

        int count = 1;
        int rangeX1 = balloons[0].positionX1;
        int rangeX2 = balloons[0].positionX2;

        for (int i = 1; i < balloons.length; i++) {
            Balloon balloon = balloons[i];

            if (rangeX2 < balloon.positionX1) {
                count++;
                rangeX1 = balloon.positionX1;
                rangeX2 = balloon.positionX2;
            } else {
                rangeX1 = Math.max(rangeX1, balloon.positionX1);
                rangeX2 = Math.min(rangeX2, balloon.positionX2);
            }
        }

        return count;
    }


}

class Balloon implements Comparable<Balloon> {
    int positionX1;
    int positionX2;

    public Balloon(int positionX1, int positionX2) {
        this.positionX1 = positionX1;
        this.positionX2 = positionX2;
    }


    @Override
    public int compareTo(Balloon balloon) {
        return this.positionX1 <= balloon.positionX1 ? -1 : 1;
    }
}
