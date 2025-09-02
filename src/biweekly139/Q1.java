package biweekly139;

import java.util.ArrayList;
import java.util.List;

public class Q1 {
    public static void main(String[] args) {


    }

    public List<Integer> stableMountains(int[] height, int threshold) {
        ArrayList<Integer> stableMountainIndexes = new ArrayList<>();
        for (int i = 1; i < height.length; i++) {
            if (height[i - 1] > threshold) {
                stableMountainIndexes.add(i);
            }
        }

        return stableMountainIndexes;
    }

}
