package PracticeQuestions;

public class Q0567 {
    public static void main(String[] args) {
        Q0567 obj = new Q0567();
        System.out.println(obj.checkInclusion("ab", "cab"));
    }

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] s1Freq = getFrequency(s1);

        int[] s2Freq = new int[26];
        int i = 0;
        while (i < s1.length()) {
            char ch = s2.charAt(i);
            int chIndex = ch - 'a';
            s2Freq[chIndex]++;
            i++;
        }

        if (isSame(s1Freq, s2Freq)) {
            return true;
        }


        while (i < s2.length()) {
            s2Freq[s2.charAt(i) - 'a']++;

            int start = i - s1.length();
            s2Freq[s2.charAt(start) - 'a']--;

            if (isSame(s1Freq, s2Freq)) {
                return true;
            }

            i++;
        }

        return false;

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
