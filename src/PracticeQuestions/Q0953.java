package PracticeQuestions;

import java.util.HashMap;
import java.util.Map;

public class Q0953 {

    public static void main(String[] args) {
        Q0953 obj = new Q0953();
        System.out.println(obj.isAlienSorted(new String[]{"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
    }

    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> precedence = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            precedence.put(order.charAt(i), i);
        }

        for (int i = 1; i < words.length; i++) {
            if (!isSmaller(words[i - 1], words[i], precedence)) {
                return false;
            }
        }

        return true;

    }

    private boolean isSmaller(String s1, String s2, Map<Character, Integer> precedence) {
        int i = 0;
        while (i < s1.length()) {
            if (i == s2.length()) {
                return false;
            }
            if (precedence.get(s1.charAt(i)) > precedence.get(s2.charAt(i))) {
                return false;
            }
            
            if (precedence.get(s1.charAt(i)) < precedence.get(s2.charAt(i))) {
                return true;
            }

            i++;
        }

        return true;

    }
}
