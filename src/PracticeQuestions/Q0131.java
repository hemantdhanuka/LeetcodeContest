package PracticeQuestions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Q0131 {
    List<List<String>> ansList = new ArrayList<>();

    public static void main(String[] args) {
        Q0131 obj = new Q0131();
        System.out.println(obj.partition("a"));
    }

    public List<List<String>> partition(String s) {
        fun(0, s, new LinkedList<>());
        return ansList;
    }


    public void fun(int index, String s, LinkedList<String> partitionList) {
        if (index >= s.length()) {
            ansList.add(new ArrayList<>(partitionList));
            return;
        }

        for (int i = index; i < s.length(); i++) {
            String subStr = s.substring(index, i + 1);
            boolean isPalindrome = checkPalindrome(subStr);

            if (isPalindrome) {
                partitionList.addLast(subStr);
                fun(i + 1, s, partitionList);
                partitionList.removeLast();
            }
        }

    }

    public boolean checkPalindrome(String s) {
        for (int i = 0; i < (s.length() + 1) / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }

        return true;
    }


}
