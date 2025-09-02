package PracticeQuestions;

import java.util.ArrayList;
import java.util.List;

public class Q0438 {
    public static void main(String[] args) {
        Q0438 obj = new Q0438();
        System.out.println(obj.findAnagrams("cbaebabacd", "abc"));
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> indexes = new ArrayList<>();

        if (p.length() > s.length()) {
            return indexes;
        }

        int[] s1Freq = getFrequency(p);
        int[] s2Freq = new int[26];
        int i = 0;
        while (i < p.length()) {
            char ch = s.charAt(i);
            int chIndex = ch - 'a';
            s2Freq[chIndex]++;
            i++;
        }

        if (isSame(s1Freq, s2Freq)) {
            indexes.add(0);
        }


        while (i < s.length()) {
            s2Freq[s.charAt(i) - 'a']++;

            int start = i - p.length();
            s2Freq[s.charAt(start) - 'a']--;

            if (isSame(s1Freq, s2Freq)) {
                indexes.add(i + 1 - p.length());
            }

            i++;
        }

        return indexes;

    }


    private int[] getFrequency(String s1) {
        int[] frequency = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            char ch = s1.charAt(i);
            frequency[ch - 'a']++;
        }

        return frequency;
    }

    private boolean isSame(int[] freq1, int[] freq2) {
        if (freq1.length != freq2.length) {
            return false;
        }

        int i = 0;
        while (i < freq1.length) {
            if (freq1[i] != freq2[i]) {
                return false;
            }
            i++;
        }

        return true;
    }

}
