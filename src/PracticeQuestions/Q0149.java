package PracticeQuestions;

import java.util.HashMap;
import java.util.Map;

public class Q0149 {
    public static void main(String[] args) {
        Q0149 obj = new Q0149();

        System.out.println(obj.maxPoints1(new int[][]{{2, 3}, {3, 3}, {-5, 3}}));
        ;
//        System.out.println(obj.maxPoints1(new int[][]{{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}}));
    }


    public int maxPoints1(int[][] points) {
        if (points.length <= 2) {
            return points.length;
        }

        int max = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Double, Integer> map = new HashMap<>();
            int x1 = points[i][0];
            int y1 = points[i][1];
            for (int j = i + 1; j < points.length; j++) {

                int x2 = points[j][0];
                int y2 = points[j][1];

                double slope = Double.MAX_VALUE;
                if (y2 - y1 == 0) {
                    slope = 0.0;
                } else if (x2 - x1 != 0) {
                    slope = (y2 - y1) / (double) (x2 - x1);
                }

                map.put(slope, map.getOrDefault(slope, 1) + 1);

                int count = map.get(slope);
                if (max < count) {
                    max = count;
                }

            }


        }

        return max;
    }


    public int maxPoints(int[][] points) {
        if (points.length <= 2) {
            return points.length;
        }

        int max = 0;
        for (int i = 0; i < points.length; i++) {
            int[] point1 = points[i];
            for (int j = i + 1; j < points.length; j++) {
                int[] point2 = points[j];
                int count = 2;
                int c = findValueOfC(point1[0], point1[1], point1[0], point1[1], point2[0], point2[1]);
                for (int k = j + 1; k < points.length; k++) {
                    int[] point3 = points[k];

                    if (isOnSameLine(point3[0], point3[1], point1[0], point1[1], point2[0], point2[1], c)) {
                        count++;
                    }
                }

                if (count > max) {
                    max = count;
                }
            }
        }

        return max;
    }

    private int findValueOfC(int x, int y, int x1, int y1, int x2, int y2) {
        return (x2 - x1) * y - (y2 - y1) * x;
    }

    private boolean isOnSameLine(int x, int y, int x1, int y1, int x2, int y2, int c) {
        return ((x2 - x1) * y - (y2 - y1) * x - c) == 0;

    }


}
