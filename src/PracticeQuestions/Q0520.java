package PracticeQuestions;

public class Q0520 {
    public static void main(String[] args) {
        Q0520 obj = new Q0520();
        System.out.println(obj.detectCapitalUse("USA"));
        System.out.println(obj.detectCapitalUse("Usa"));
        System.out.println(obj.detectCapitalUse("usa"));
        System.out.println(obj.detectCapitalUse("uSa"));
    }


    public boolean detectCapitalUse(String word) {
        return isAllCapitals(word) || isAllSmall(word) || isFirstCapitalRestIsSmall(word);
    }

    public boolean isAllCapitals(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (!isCapitalLetter(word.charAt(i))) {
                return false;
            }

        }
        return true;
    }

    public boolean isAllSmall(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (isCapitalLetter(word.charAt(i))) {
                return false;
            }

        }

        return true;

    }

    public boolean isFirstCapitalRestIsSmall(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (i == 0 && !isCapitalLetter(word.charAt(i))) {
                return false;
            }
            if (i != 0 && isCapitalLetter(word.charAt(i))) {
                return false;
            }

        }
        return true;

    }

    public boolean isCapitalLetter(char ch) {
        return ch - 'A' >= 0 && ch - 'Z' <= 0;
    }
}
