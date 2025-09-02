package PracticeQuestions;

public class Q0006 {
    public static void main(String[] args) {
        Q0006 obj = new Q0006();
        System.out.println(obj.convert("PAYPALISHIRING", 4));
    }

    public String convert(String s, int numRows) {
        StringBuilder[] stringBuilders = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            stringBuilders[i] = new StringBuilder();
        }

        int position = 0;
        while (position < s.length()) {
            fillVertically(position, numRows, s, stringBuilders);
            position = position + numRows;

            fillDiagonally(position, numRows, s, stringBuilders);
            position = position + Math.max(0, numRows - 2);
        }

        StringBuilder finalStringBuilder = new StringBuilder();
        for (int i = 0; i < stringBuilders.length; i++) {
            finalStringBuilder.append(stringBuilders[i]);
        }

        return finalStringBuilder.toString();
    }


    private void fillVertically(int position, int rows, String word, StringBuilder[] stringBuilders) {
        int i = 0;
        while (i + position < word.length() && i < rows) {
            stringBuilders[i].append(word.charAt(i + position));
            i++;
        }
    }

    private void fillDiagonally(int position, int rows, String word, StringBuilder[] stringBuilders) {
        int i = rows - 2;
        int count = 0;
        while (count + position < word.length() && i > 0) {
            stringBuilders[i].append(word.charAt(count + position));
            count++;
            i--;
        }
    }
}
