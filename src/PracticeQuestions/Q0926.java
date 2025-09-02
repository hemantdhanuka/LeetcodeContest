package PracticeQuestions;

public class Q0926 {

    public static void main(String[] args) {
        Q0926 obj = new Q0926();
        System.out.println(obj.minFlipsMonoIncr("00011000"));
        System.out.println(obj.minFlipsMonoIncr("010110"));

    }

    public int minFlipsMonoIncr(String s) {
        int prefixSum1s[] = new int[s.length()];
        int sum = 0;
        for (int i = 0; i < prefixSum1s.length; i++) {
            prefixSum1s[i] = sum + s.charAt(i) - '0';
            sum = prefixSum1s[i];
        }

        int r = s.length() - 1;
        int ans = Math.min(findNumberOf1s(0, r, prefixSum1s), findNumberOf0s(0, r, prefixSum1s));

        for (int i = 0; i < s.length(); i++) {
            ans = Math.min(ans, findNumberOf1s(0, i, prefixSum1s) + findNumberOf0s(i + 1, r, prefixSum1s));
        }

        return ans;

    }

    private int findNumberOf1s(int l, int r, int prefixSum1s[]) {
        if (l == 0) {
            return prefixSum1s[r];
        }

        return prefixSum1s[r] - prefixSum1s[l - 1];
    }

    private int findNumberOf0s(int l, int r, int prefixSum1s[]) {
        int numOf1s = findNumberOf1s(l, r, prefixSum1s);
        return (r - l + 1) - numOf1s;
    }

}
