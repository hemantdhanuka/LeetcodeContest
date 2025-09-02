package PracticeQuestions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Q0491 {
    public static void main(String[] args) {
        Q0491 obj = new Q0491();
        System.out.println(obj.findSubsequences(new int[]{4, 4, 3, 2, 1}).size());
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> ansList = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        fun(0, "", new LinkedList<Integer>(), nums, ansList, set);
        return ansList;
    }

    public void fun(int index, String addedNumberHash, LinkedList<Integer> addedNumberList, int[] nums, List<List<Integer>> ansList, HashSet<String> set) {
        if (index == nums.length) {
            if (addedNumberList.size() >= 2 && !set.contains(addedNumberHash)) {
                ansList.add((List<Integer>) addedNumberList.clone());
                set.add(addedNumberHash);
                return;
            }
            return;
        }

        if (addedNumberList.size() == 0 || nums[index] >= addedNumberList.getLast()) {
            addedNumberList.addLast(nums[index]);
            fun(index + 1, addedNumberHash + "-" + nums[index], addedNumberList, nums, ansList, set);
            addedNumberList.removeLast();
        }

        fun(index + 1, addedNumberHash, addedNumberList, nums, ansList, set);
    }

}
