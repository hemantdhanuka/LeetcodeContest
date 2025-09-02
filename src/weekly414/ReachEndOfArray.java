package weekly414;

import java.util.Arrays;
import java.util.List;

public class ReachEndOfArray {
    public static void main(String[] args) {
        ReachEndOfArray obj = new ReachEndOfArray();
        List<Integer> nums = Arrays.asList(1, 3, 1, 5);
        System.out.println(obj.findMaximumScore(nums));
    }

    private static long calculateScore(List<Integer> nums, int lastPickedItemIndex, int currentPicketItemIndex) {
        return nums.get(lastPickedItemIndex) * (long) (currentPicketItemIndex - lastPickedItemIndex);
    }

    public long findMaximumScore(List<Integer> nums) {
        long ans = 0;
        int lastPickedItemIndex = 0;

        int length = nums.size();

        for (int i = 1; i < length; i++) {
            if (nums.get(i) > nums.get(lastPickedItemIndex)) {
                ans = ans + calculateScore(nums, lastPickedItemIndex, i);
                lastPickedItemIndex = i;
            }

            if (i == nums.size() - 1) {
                if (lastPickedItemIndex != length - 1) {
                    ans = ans + calculateScore(nums, lastPickedItemIndex, i);
                    lastPickedItemIndex = i;
                }
            }
        }

        return ans;


    }


}
