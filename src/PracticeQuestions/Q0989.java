package PracticeQuestions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q0989 {
    public static void main(String[] args) {
        Q0989 obj = new Q0989();
        System.out.println(obj.addToArrayForm(new int[]{1, 2, 0, 9}, 9134));
        System.out.println(obj.addBinary("0", "0"));
    }

    public List<Integer> addToArrayForm(int[] num, int k) {
        String num2 = k + "";

        int carry = 0;
        int base = 10;
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < num.length || i < num2.length(); i++) {
            int digit1 = i < num.length ? num[num.length - 1 - i] : 0;
            int digit2 = i < num2.length() ? Integer.parseInt(num2.charAt(num2.length() - 1 - i) + "") : 0;

            int reminder = (digit1 + digit2 + carry) % base;
            carry = (digit1 + digit2 + carry) / base;
            result.add(reminder);
        }

        if (carry != 0) {
            result.add(carry);
        }

        Collections.reverse(result);
        return result;
    }

    public String addBinary(String a, String b) {
        int carry = 0;
        int base = 2;
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < a.length() || i < b.length(); i++) {
            int digit1 = i < a.length() ? Integer.parseInt(a.charAt(a.length() - 1 - i) + "") : 0;
            int digit2 = i < b.length() ? Integer.parseInt(b.charAt(b.length() - 1 - i) + "") : 0;

            int reminder = (digit1 + digit2 + carry) % base;
            carry = (digit1 + digit2 + carry) / base;
            result.add(reminder);
        }

        if (carry != 0) {
            result.add(carry);
        }

        Collections.reverse(result);
        StringBuilder sb = new StringBuilder();
        for (int i : result) {
            sb.append(i);
        }

        return sb.toString();
    }

}
