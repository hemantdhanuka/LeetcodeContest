package PracticeQuestions;

public class Q1071 {
    public static void main(String[] args) {
        Q1071 obj = new Q1071();
        System.out.println(obj.gcdOfStrings("REQAGELREQAGELREQAGELREQAGELREQAGEL", "REQAGELREQAGELREQAGELREQAGELREQAGELREQAGELREQAGELREQAGELREQAGEL"));
    }

    public String gcdOfStrings(String str1, String str2) {
        int l1 = str1.length();
        int l2 = str2.length();

        int min = Math.min(l1, l2);

        int i = min;
        String ans = "";
        while (i > 0) {

            if (l1 % i == 0 && l2 % i == 0) {
                boolean isMatched = checkCanConstruct(str2.substring(0, i), str1) && checkCanConstruct(str2.substring(0, i), str2);
                if (isMatched) {
                    ans = str2.substring(0, i);
                    break;
                }
            }


            i--;
        }

        return ans;
    }


    private boolean checkCanConstruct(String subString, String finalString) {
        if (finalString.length() % subString.length() != 0) {
            return false;
        }

        for (int i = 0; i < finalString.length(); i++) {
            if (finalString.charAt(i) != subString.charAt(i % subString.length())) {
                return false;
            }
        }

        return true;
    }

}
