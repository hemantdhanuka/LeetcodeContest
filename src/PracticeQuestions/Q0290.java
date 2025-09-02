package PracticeQuestions;

import java.util.HashMap;
import java.util.Map;

public class Q0290 {
    public static void main(String[] args) {
        Q0290 obj = new Q0290();
        boolean answer = obj.wordPattern("abba", "dog dog dog dog");
        System.out.println(answer);
    }

    public boolean wordPattern(String pattern, String s) {
        String sWordsArray[] = s.split(" ");

        if (pattern.length() != sWordsArray.length) {
            return false;
        }

        Map<Character, String> mapping1 = new HashMap<>();
        Map<String, Character> mapping2 = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {

            char patternChar = pattern.charAt(i);
            if (mapping1.containsKey(patternChar)) {
                String value = mapping1.get(patternChar);
                if (!value.equals(sWordsArray[i])) {
                    return false;
                }
            } else {
                mapping1.put(patternChar, sWordsArray[i]);
            }
        }

        for (int i = 0; i < sWordsArray.length; i++) {

            char patternChar = pattern.charAt(i);
            if (mapping2.containsKey(sWordsArray[i])) {
                char value = mapping2.get(sWordsArray[i]);
                if (value != patternChar) {
                    return false;
                }
            } else {
                mapping2.put(sWordsArray[i], patternChar);
            }
        }

        return true;
    }

}
